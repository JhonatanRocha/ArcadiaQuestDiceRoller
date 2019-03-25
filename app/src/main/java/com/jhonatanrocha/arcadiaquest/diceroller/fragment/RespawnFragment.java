package com.jhonatanrocha.arcadiaquest.diceroller.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jhonatanrocha.arcadiaquest.diceroller.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RespawnFragment extends Fragment {


    public RespawnFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_respawn, container, false);
    }

}
