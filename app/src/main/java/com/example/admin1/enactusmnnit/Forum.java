package com.example.admin1.enactusmnnit;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.firebase.client.Firebase;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Forum.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Forum#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Forum extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
   EditText editname;
    EditText edityear;
    EditText editidea;
    EditText branch;
    Button submit;
   RadioGroup current;
   int isflag=0;
    int is=0,isa=0;
    RadioButton radiobutton;
    RadioButton al;
    RadioButton others;
    String getyear;
    private OnFragmentInteractionListener mListener;

    public Forum() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Forum.
     */
    // TODO: Rename and change types and number of parameters
    public static Forum newInstance(String param1, String param2) {
        Forum fragment = new Forum();
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
                .setActionBarTitle("New ideas");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_forum, container, false);
        editname=(EditText)rootView.findViewById(R.id.editTextName);
        branch=(EditText)rootView.findViewById(R.id.branch);
        submit=(Button)rootView.findViewById(R.id.buttonSave);
        edityear=(EditText)rootView.findViewById(R.id.year);
        editidea=(EditText)rootView.findViewById(R.id.idea);
        current=(RadioGroup)rootView.findViewById(R.id.select);
        al=(RadioButton)rootView.findViewById(R.id.alumini);
        others=(RadioButton)rootView.findViewById(R.id.other);
        others.setChecked(true);
        int item=current.getCheckedRadioButtonId();
        radiobutton=(RadioButton)rootView.findViewById(R.id.yearbutton);
         current.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                    if(checkedId==R.id.yearbutton)
                                                    { isflag=1;
                                                        edityear.setVisibility(View.VISIBLE);
                                                        branch.setVisibility(View.VISIBLE);
                                                    }
                                                    else
                                                    { isflag=0;
                                                        edityear.setVisibility(View.INVISIBLE);
                                                        branch.setVisibility(View.INVISIBLE);
                                                    }
                                                }
         }
         );
        submit.setEnabled(false);
        editname.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    editname.setError( "Name is required!" );
                    submit.setEnabled(false);
                    is=0;
                } else {
                    is=1;
                    if(is==1&&isa==1)
                        submit.setEnabled(true);
                    //submit.setEnabled(true);
                }


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });
        editidea.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    editidea.setError( "Idea ?!" );
                    submit.setEnabled(false);
                    isa=0;
                } else {
                    isa=1;
                    if(is==1&&isa==1)
                        submit.setEnabled(true);
                    //submit.setEnabled(true);
                }


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Firebase ref=new Firebase("https://enactus-mnnit-10e9c.firebaseio.com/NEW IDEAS");
                String getname=editname.getText().toString();
                if(isflag==1)
                 getyear=edityear.getText().toString();
                else if(al.isChecked())
                getyear="Alumini";
                else
                getyear="Others";
                String getidea=editidea.getText().toString();
                idea newidea=new idea(getname,getyear,getidea);
                String key=ref.push().getKey();
                ref.child(key).setValue(newidea);
                Toast.makeText(getActivity(),"SUBMITTED",Toast.LENGTH_LONG).show();
                editname.setText("");
                editidea.setText("");
                others.setChecked(true);
                editname.setError(null);
                editidea.setError(null);
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
