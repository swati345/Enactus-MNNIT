package com.example.admin1.enactusmnnit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.ImageFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class picDisplay extends AppCompatActivity {
    TextView tv = null;

    private GridView listView;
    private picgridAdapter picgridadapterr;
    private List<picgrid> adLink;
    private DatabaseReference mDatabaseRef;


    ProgressDialog mProgressDialog;
    Intent n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_display);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Log.d("harry123", "start second Activity");
        listView = (GridView)findViewById(R.id.list_view_for_subjects);
       adLink = new ArrayList();

        Intent g = getIntent();
        final String category = g.getExtras().getString("category");
        //getSupportActionBar().setTitle(category);
        Log.d("harry123", "Update onPreExecute start");
        mProgressDialog = new ProgressDialog(picDisplay.this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();
        try {


            mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("PICS").child(category);
//                mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Political_Science/Semester_2/Political_Processes_In_India");
            Log.d("harry123", "Do In back 1");
            mDatabaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("harry123","VAlue--->"+dataSnapshot);
                    String str =  dataSnapshot.getValue().toString();

                    Log.d("harry12345","Retriving value...."+str);
                    String str1 = str;//.replaceAll("\\[(.*?)\\]", "");
                    //Log.d("harry123","String.."+str1);
                   // str1 = str1.replaceAll("=", "");
                    //Log.d("harry12345","String.."+str1);
                    System.out.println(str1);
                    ArrayList<String> stm = new ArrayList<>();
//                    str1 = str1.replaceAll("\\{[^{}]*\\}", "");
                    System.out.println(str1);
                    str1 = str1.substring(6, str1.length()-1);
                    Log.d("harry12345","String.."+str1);
                    System.out.println(str1);
                    String[] st = str1.split(",");
                    for(int i=0;i<st.length;i++)
                    {
                        Log.d("harry12345",st[i].trim());
                        adLink.add(new picgrid(st[i].trim()));
                //        Toast.makeText(getApplicationContext(), st[i], Toast.LENGTH_SHORT).show();
                        Log.d("harry12345","Retriving value for database"+dataSnapshot.getValue().toString());
                       picgridadapterr = new picgridAdapter(getApplicationContext(),adLink);
                        listView.setAdapter(picgridadapterr);
                        stm.add(st[i].trim());
                    }
                    mProgressDialog.dismiss();
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    Log.d("harry123", "loadPost:onCancelled", databaseError.toException());
                    // ...
                }
            });



        } catch (Exception e) {
//             	b.setText("title");
            System.out.println("title2 : ");
            e.printStackTrace();
        }
        picgridadapterr = new picgridAdapter(getApplicationContext(), adLink);
        listView.setAdapter(picgridadapterr);

        n = new Intent(this, sample.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
               // Toast.makeText(getApplicationContext(), "Clicked product id "+view.getTag(), Toast.LENGTH_SHORT).show();
//                Intent n = new Intent(this, Main3Activity.class);
                n.putExtra("picselect", view.getTag().toString());

                Log.d("harry123_audioFileName", view.getTag().toString());

                startActivity(n);
            }


        });
    }
}
