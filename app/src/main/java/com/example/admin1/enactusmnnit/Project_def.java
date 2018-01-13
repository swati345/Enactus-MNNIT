package com.example.admin1.enactusmnnit;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Project_def extends AppCompatActivity {
    TextView about;
    TextView objective;
    TextView impact;
    TextView majord;
    TextView finalp;
    ImageView image;
    TextView abouticon;
    TextView objectiveicon;
    TextView impacticon;
    TextView majordicon;
    TextView finalpicon;
    private DatabaseReference mDatabaseRef;
    ProgressDialog mProgressDialog;
    Intent n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        //setContentView(R.layout.main);
        setContentView(R.layout.activity_project_def);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.mytitle);


        Intent g = getIntent();
        final String category = g.getExtras().getString("category");
       about=(TextView) findViewById(R.id.aboutp);
      objective=(TextView)findViewById(R.id.objectivep);
       impact=(TextView)findViewById(R.id.impactsp);
       majord=(TextView)findViewById(R.id.mdp);
       finalp=(TextView)findViewById(R.id.fpp);
        image=(ImageView)findViewById(R.id.imageView5) ;
        setTitle(category);
        abouticon=(TextView)findViewById(R.id.About);
        objectiveicon=(TextView)findViewById(R.id.objective);
        impacticon=(TextView)findViewById(R.id.impacts);
        majordicon=(TextView)findViewById(R.id.md);
        finalpicon=(TextView)findViewById(R.id.fp);
        //setTheme(R.style.PopupOverlay);
        //ActionBar bar = getActionBar();
//for color
       // bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00C4CD")));
        //getSupportActionBar().setTitle(category);
        Log.d("harry123", "Update onPreExecute start");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Projects").child(category).child("pic");
        Log.d("harry123", "Do In back 1");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String str = dataSnapshot.getValue().toString();
                Log.d("harry1234","str.."+str);
                Picasso.with(getApplicationContext()).setLoggingEnabled(true);
                Picasso.with(getApplicationContext())
                        .load(str)
                        //.resize(500,250)
                      //  .fit()
                        .priority(Picasso.Priority.HIGH)
                        .into(image);
                //mProgressDialog.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.d("harry123", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Projects").child(category).child("About");
        Log.d("harry123", "Do In back 1");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String str = dataSnapshot.getValue().toString();
                about.setText(str);
                //mProgressDialog.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.d("harry123", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Projects").child(category).child("Objective");
        Log.d("harry123", "Do In back 1");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String str = dataSnapshot.getValue().toString();
                objective.setText(str);
                //mProgressDialog.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.d("harry123", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
        abouticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                about.setVisibility(about.getVisibility() == View.GONE ? View.VISIBLE
                        : View.GONE);
            }
        });
        objectiveicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objective.setVisibility(objective.getVisibility() == View.GONE ? View.VISIBLE
                        : View.GONE);
            }
        });
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Projects").child(category).child("Major Deliverables");
        Log.d("harry123", "Do In back 1");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String str = dataSnapshot.getValue().toString();
                majord.setText(str);
                //mProgressDialog.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.d("harry123", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        }); majordicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                majord.setVisibility(majord.getVisibility() == View.GONE ? View.VISIBLE
                        : View.GONE);
            }
        });
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Projects").child(category).child("Impacts");
        Log.d("harry123", "Do In back 1");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String str = dataSnapshot.getValue().toString();
                impact.setText(str);
                //mProgressDialog.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.d("harry123", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
        impacticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                impact.setVisibility(impact.getVisibility() == View.GONE ? View.VISIBLE
                        : View.GONE);
            }
        });
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Projects").child(category).child("The Final Product");
        Log.d("harry123", "Do In back 1");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String str = dataSnapshot.getValue().toString();
                finalp.setText(str);
                //mProgressDialog.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.d("harry123", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
       finalpicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finalp.setVisibility(finalp.getVisibility() == View.GONE ? View.VISIBLE
                        : View.GONE);
            }
        });
    }


}
