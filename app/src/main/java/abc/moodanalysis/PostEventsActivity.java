package abc.moodanalysis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;



    public class PostEventsActivity extends AppCompatActivity {

        private ImageButton imgbut;
        private int galReq=111;
        private EditText mposttitle;
        private EditText mpostdesc;
        private Button mpostBut;
        private DatabaseReference mdbref;
        private DatabaseReference mdbref2;
        private Uri muri;
        private StorageReference msore;
        private ProgressDialog mprogress;
        private FirebaseAuth mAuth;
        private FirebaseUser mCurrentUser;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_post_events);
            imgbut=(ImageButton)findViewById(R.id.imageSelect);
            mpostBut=(Button)findViewById(R.id.submitButton);
            mpostdesc=(EditText)findViewById(R.id.descField);
            mAuth=FirebaseAuth.getInstance();
            mCurrentUser= mAuth.getCurrentUser();
            msore= FirebaseStorage.getInstance().getReference();
            mposttitle=(EditText)findViewById(R.id.titleField);
            mdbref= FirebaseDatabase.getInstance().getReference().child("BLOGS");
//        mdbref2= FirebaseDatabase.getInstance().getReference().child("users").child(mCurrentUser.getUid());
            mprogress= new ProgressDialog(this);
            mpostBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    statPosting();
                }
            });
            imgbut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent galleryintent= new Intent(Intent.ACTION_GET_CONTENT);
                    galleryintent.setType("image/+");
                    startActivityForResult(galleryintent,galReq);

                }
            });
        }

        private void statPosting() {

            final String title_val=mposttitle.getText().toString().trim();
            final String desc_val=mpostdesc.getText().toString().trim();
            if(!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(desc_val) && muri!=null){
                mprogress.setMessage("Posting ...");
                mprogress.show();
                StorageReference sref = msore.child("BLOGS").child(muri.getLastPathSegment());
                sref.putFile(muri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        final Uri downloadUri=taskSnapshot.getDownloadUrl();
                        final DatabaseReference newpost=mdbref.push();
                        mdbref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                newpost.child("Title").setValue(title_val);

                                newpost.child("Description").setValue(desc_val);
                                newpost.child("Image").setValue(downloadUri.toString());
                                //startActivity(new Intent(PostEventActivity.this,EventsActivity.class));
                            /*
                            newpost.child("UID").setValue(mCurrentUser.getUid());
                            */

                                newpost.child("Description").setValue(desc_val).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {


                                        if(task.isSuccessful()){

                                            //startActivity(new Intent(PostEventActivity.this,EventsActivity.class));

                                        }else{

                                        }
                                    }
                                });

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        mprogress.dismiss();

                        //Toast.makeText(account.this, "uoloaded", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }

        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == galReq && resultCode == RESULT_OK) {
                muri = data.getData();
           /* StorageReference sref = msore.child("Photos").child(uri.getLastPathSegment());
            sref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(account.this, "uoloaded", Toast.LENGTH_SHORT).show();
                }
            });*/
                imgbut.setImageURI(muri);

            }
        }



    }

