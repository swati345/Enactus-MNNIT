package com.example.admin1.enactusmnnit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Team.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Team#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Team extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView  pres;
    Intent g;
    String image1;
    String image11;
    private DatabaseReference mDatabaseRef;
    ProgressDialog mProgressDialog;
    ImageView vpres;
    Button year1;
    Button year2;
    Button year3;
    Button year4;
    TextView presname;
    TextView vpresname;
    private OnFragmentInteractionListener mListener;

    public Team() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Team.
     */
    // TODO: Rename and change types and number of parameters
    public static Team newInstance(String param1, String param2) {
        Team fragment = new Team();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        ((Main2Activity)getActivity())
                .setActionBarTitle("Team");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview;

        rootview= inflater.inflate(R.layout.fragment_team, container, false);
        pres=(ImageView)rootview.findViewById(R.id.president);
        vpres=(ImageView)rootview.findViewById(R.id.vpresident);
        presname=(TextView)rootview.findViewById(R.id.prest);
        vpresname=(TextView)rootview.findViewById(R.id.vprest);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Team").child("president");
        Log.d("harry123", "Do In back 1");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String str = dataSnapshot.getValue().toString();
               Log.d("harry1234","value---"+str);
                int index=str.indexOf("=");
                String name=str.substring(1,index);
                Log.d("harry123","name--"+name);
                name=name.replace('_',' ');
                Log.d("harry123","name--"+name);
                presname.setText(name);
                 image1=str.substring(index+1);
                int s=image1.length();
                image1=image1.substring(0,s-1);
                Log.d("harry123","name--"+image1);
                //Picasso.with(getActivity()).setLoggingEnabl
                Picasso.with(getActivity().getApplicationContext()).load(image1).fit().into(pres);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.d("harry123", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Team").child("vpresident");
        Log.d("harry123", "Do In back 1");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String str = dataSnapshot.getValue().toString();
                Log.d("harry1234","value---"+str);
                int index=str.indexOf("=");
                String name=str.substring(1,index);
                Log.d("harry123","name--"+name);
                name=name.replace('_',' ');
                Log.d("harry123","name--"+name);
                vpresname.setText(name);
               image11=str.substring(index+1);
                int s=image11.length();
                image11=image11.substring(0,s-1);
                Log.d("harry123","url--"+image11);

                Picasso.with(getActivity()).load(image11).fit().into(vpres);

                //mProgressDialog.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.d("harry123", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
         g=new Intent(getActivity(),TeamDisplay.class);
        year1=(Button)rootview.findViewById(R.id.one);
        year2=(Button)rootview.findViewById(R.id.two);
        year3=(Button)rootview.findViewById(R.id.three);
        year4=(Button)rootview.findViewById(R.id.four);
        year1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                g.putExtra("category", "1year");
                startActivity(g);
            }
        });
        year2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                g.putExtra("category", "2year");
                startActivity(g);
            }
        });
        year3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                g.putExtra("category", "3year");
                startActivity(g);
            }
        });
        year4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                g.putExtra("category", "4year");
                startActivity(g);
            }
        });
        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
