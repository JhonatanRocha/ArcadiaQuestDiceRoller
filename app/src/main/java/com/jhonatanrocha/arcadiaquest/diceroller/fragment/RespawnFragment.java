package com.jhonatanrocha.arcadiaquest.diceroller.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.jhonatanrocha.arcadiaquest.diceroller.R;
import com.jhonatanrocha.arcadiaquest.diceroller.ResultRespawnActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RespawnFragment extends Fragment {

    private View view;
    private ImageButton imageButtonRespawn;

    public RespawnFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_respawn, container, false);

        this.imageButtonRespawn = (ImageButton) view.findViewById(R.id.graveyardImageButton);

        rollRespawn();

        return view;
    }

    private void rollRespawn() {
        this.imageButtonRespawn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ResultRespawnActivity.class);

                startActivity(intent);
            }
        });
    }

}
