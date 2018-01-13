package com.example.admin1.enactusmnnit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Past_Projects.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Past_Projects#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Past_Projects extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView tv=null;
    private ListView listView;
    private picsAdapter picsadapter;
    private List<pics> piclist;
    private DatabaseReference mDatabaseRef;
    ProgressDialog mProgressDialog;
    Intent n;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Past_Projects() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Past_Projects.
     */
    // TODO: Rename and change types and number of parameters
    public static Past_Projects newInstance(String param1, String param2) {
        Past_Projects fragment = new Past_Projects();
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
                .setActionBarTitle("Projects");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_past__projects, container, false);
        Log.d("harry123", "start second Activity");
        listView = (ListView)rootView.findViewById(R.id.list_view_for_courses);

        piclist = new ArrayList();

        Log.d("harry123", "Update onPreExecute start");
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();

        try {

            mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Projects");

//                mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Political_Science/Semester_2/Political_Processes_In_India");
            Log.d("harry123", "Do In back 1");
            mDatabaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String str = dataSnapshot.getValue().toString();
                   // Log.d("harry","object------>"+dataSnapshot);
                    Log.d("harry12345","Retriving value....");
                    String str1 = str.replaceAll("\\[(.*?)\\]", "");
                    Log.d("harry12345","String--"+str1);
                    str1 = str1.replaceAll("=", "");
                    System.out.println(str1);
                    ArrayList<String> stm = new ArrayList<>();
                    str1 = str1.replaceAll("\\{[^{}]*\\}", "");
                    //str1 = str1.replaceAll("\\{[^{}]*\\}", "");
                    System.out.println(str1);
                    str1 = str1.substring(1, str1.length()-1);
                    System.out.println(str1);
                    String[] st = str1.split(",");
                    for(int i=0;i<st.length;i++)
                    {
                        Log.d("harry12345",st[i].trim());
                        piclist.add(new pics(st[i].trim()));
                        picsadapter = new picsAdapter(getActivity(), piclist);
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
        picsadapter = new picsAdapter(getActivity(),piclist);
        listView.setAdapter(picsadapter);

        n = new Intent(getActivity(), Project_def.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
               // Toast.makeText(getActivity(), "Clicked product id "+view.getTag(), Toast.LENGTH_SHORT).show();
//                Intent n = new Intent(this, Main3Activity.class);
                n.putExtra("category", view.getTag().toString());
                Log.d("harry123_audioFileName", view.getTag().toString());

                startActivity(n);
            }


        });
        return rootView;
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
