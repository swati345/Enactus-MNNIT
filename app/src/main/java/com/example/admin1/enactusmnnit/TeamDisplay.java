package com.example.admin1.enactusmnnit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TeamDisplay extends AppCompatActivity {
  String str;
    private ListView listView;
    private picsAdapter picsadapter;
    private List<pics> piclist;
    private DatabaseReference mDatabaseRef;
    ProgressDialog mProgressDialog;
    String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_display);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent g = getIntent();
         category = g.getExtras().getString("category");
        String s=category.charAt(0)+" year";
        setTitle(s);
        Log.d("harry123", "start second Activity");
        listView = (ListView)findViewById(R.id.list_view_for_courses);

        piclist = new ArrayList();

        Log.d("harry123", "Update onPreExecute start");
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();

        try {

            mDatabaseRef = FirebaseDatabase.getInstance().getReference("Team").child(category);
//                mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Political_Science/Semester_2/Political_Processes_In_India");
            Log.d("harry123", "Do In back 1");
            mDatabaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String str = dataSnapshot.getValue().toString();

                    Log.d("harry12345","Retriving value....");
                    String str1 = str;//.replaceAll("\\[(.*?)\\]", "");
                    str1 = str1.replaceAll("=", "");
                    System.out.println(str1);
                    ArrayList<String> stm = new ArrayList<>();
                    //str1 = str1.replaceAll("\\{[^{}]*\\}", "");
                    //str1 = str1.replaceAll("\\{[^{}]*\\}", "");
                    System.out.println(str1);
                    str1 = str1.substring(6, str1.length()-1);
                    System.out.println(str1);
                    String[] st = str1.split(",");
                    for(int i=0;i<st.length;i++)
                    {
                        Log.d("harry12345",st[i].trim());
                        piclist.add(new pics(st[i].trim()));
                        picsadapter = new picsAdapter(getApplicationContext(),piclist);
                        listView.setAdapter(picsadapter);
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
        picsadapter = new picsAdapter(getApplicationContext(),piclist);
        listView.setAdapter(picsadapter);



    }
}
