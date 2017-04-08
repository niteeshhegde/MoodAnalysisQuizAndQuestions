package abc.moodanalysis;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
public class EventsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView mrecycle;
    private FirebaseAuth mAuth;
    public String TAG="ff";
    private FirebaseAuth.AuthStateListener mauthStateListener;
    private DatabaseReference mdbreference;
    private DatabaseReference mdbreferences;
    private DatabaseReference mdbreferencelikes;
    private DatabaseReference mdbreferencelikescount;
    //  private Boolean mProcessLike=false;
    private TextView likecount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        mAuth=FirebaseAuth.getInstance();
        Picasso.Builder builder=new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this, Integer.MAX_VALUE));
        Picasso built=builder.build();
        built.setIndicatorsEnabled(false);
        built.setLoggingEnabled(true);
        try {
            Picasso.setSingletonInstance(built);
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        }catch (Exception e){

        }




        mrecycle=(RecyclerView)findViewById(R.id.eventslist);
        // goologouts=(Button) findViewById(goologout);
        mauthStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null){
                    //  Toast.makeText(EventsActivity.this, "LOGGING OUT", Toast.LENGTH_SHORT).show();
                    // startActivity(new Intent(EventsActivity.this,SignActivity.class));
                }
            }
        };

        mdbreference= FirebaseDatabase.getInstance().getReference().child("EVENTS");
        //    mdbreferences= FirebaseDatabase.getInstance().getReference().child("users");
        //  mdbreferencelikes= FirebaseDatabase.getInstance().getReference().child("likes");
        //   mdbreferencelikescount= FirebaseDatabase.getInstance().getReference().child("likes");
        mdbreference.keepSynced(true);
        //   mdbreferences.keepSynced(true);
//        mdbreferencelikes.keepSynced(true);
        mrecycle.setHasFixedSize(true);
        mrecycle.setLayoutManager(new LinearLayoutManager(this));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mauthStateListener);
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // do your stuff
        } else {
            signInAnonymously();
        }
        FirebaseRecyclerAdapter<blog,blogViewHolder> adapter=new FirebaseRecyclerAdapter<blog, blogViewHolder>(
                blog.class,
                R.layout.single_row_event,
                blogViewHolder.class,
                mdbreference

        ) {
            @Override
            protected void populateViewHolder(blogViewHolder viewHolder, blog model, int position) {
                final String post_key=getRef(position).getKey();
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDescription());
                viewHolder.setImage(getApplicationContext(),model.getImage());
                //  viewHolder.setLikeButton(post_key);
                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(EventsActivity.this,SingleEventActivity.class);
                        intent.putExtra("keys",post_key);
                       startActivity(intent);



                    }
                });


            }
        };
        mrecycle.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.events, menu);
        return true;
    }
    private void signInAnonymously() {
        mAuth.signInAnonymously().addOnSuccessListener(this, new  OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                // do your stuff
            }
        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.e(TAG, "signInAnonymously:FAILURE", exception);
                    }
                });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            startActivity(new Intent(EventsActivity.this,PostEventsActivity.class));
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public static class blogViewHolder extends RecyclerView.ViewHolder{

        View mview;
        ImageButton mlikeButt;
        TextView likecou;
        DatabaseReference mlikesdb;
        FirebaseAuth mAuth;
        public blogViewHolder(View itemView) {
            super(itemView);
            mview=itemView;
            mAuth=FirebaseAuth.getInstance();
            //    mlikesdb.keepSynced(true);
        }



        public void setTitle(String title){
            TextView post_title=(TextView)mview.findViewById(R.id.postTitle);
            post_title.setText(title);
        }

        public void setDesc(String desc){
            TextView post_desc=(TextView)mview.findViewById(R.id.postDesc);
            post_desc.setText(desc);
        }
        public void setImage(final Context ctx,final String image){
            final ImageView post_image=(ImageView)mview.findViewById(R.id.postImage);
            //
            Picasso.with(ctx).load(image).networkPolicy(NetworkPolicy.OFFLINE).into(post_image, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Picasso.with(ctx).load(image).into(post_image);
                }
            });
        }
    }
}
