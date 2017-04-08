package abc.moodanalysis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class SingleEventActivity extends AppCompatActivity {
    private String post_key;
    DatabaseReference mdatabaseref;
    private ImageView imagesingle;
    private TextView titleSingle;
    private TextView descSingle;
    private TextView nameSingle;
    private FirebaseAuth mAuth;
    private Button remButt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_event);
        mdatabaseref= FirebaseDatabase.getInstance().getReference().child("EVENTS");
        mAuth=FirebaseAuth.getInstance();
        imagesingle=(ImageView)findViewById(R.id.singleimg);
        titleSingle=(TextView)findViewById(R.id.single_event_title);
        descSingle=(TextView)findViewById(R.id.single_event_desc);
        post_key=getIntent().getExtras().getString("keys");
        mdatabaseref.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String post_title=(String) dataSnapshot.child("Title").getValue();
                String post_desc=(String) dataSnapshot.child("Description").getValue();
                String post_image=(String) dataSnapshot.child("Image").getValue();
             //   String post_uid=(String) dataSnapshot.child("UID").getValue();
           //     String post_name=(String) dataSnapshot.child("Name").getValue();
                titleSingle.setText(post_title);
              //  nameSingle.setText(post_name);
                descSingle.setText(post_desc);
                Picasso.with(SingleEventActivity.this).load(post_image).into(imagesingle);
/*
                remButt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mdatabaseref.child(post_key).removeValue();
                        startActivity(new Intent(SingleEventActivity.this,MainActivity.class));
                    }
                });
                */
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
