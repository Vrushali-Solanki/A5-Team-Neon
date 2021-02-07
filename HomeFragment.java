package com.example.childvaccine;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    ListView lvProgram;
    String[] programName = {"Polio","ChickenPox","Hepatitis-A","Hepatitis-B","Rotavirus","Influenza"};
    String[] programDescription = {"2/02/2019","2/02/2019","3/02/2019","5/02/2019","5/02/2019","12/02/2019"};
    int [] programImages = {R.drawable.polio,R.drawable.chickenpox,R.drawable.ha,R.drawable.hb,R.drawable.rv,R.drawable.influenza};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_home, container, false);
        lvProgram = v.findViewById(R.id.lvProgram);
        com.example.myapplication.ProgramAdapter programAdapter = new com.example.myapplication.ProgramAdapter(getActivity(), programName, programImages, programDescription);
        lvProgram.setAdapter(programAdapter);

        return v;

    }
}