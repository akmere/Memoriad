package com.bignerdranch.android.memoriad.FirstActivity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bignerdranch.android.memoriad.SecondActivity.HdCompetition;
import com.bignerdranch.android.memoriad.R;

public class DefaultChooseFragment extends Fragment {

    private Button confirmButton, backButton;
    private EditText customNumber;

    public DefaultChooseFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_default_choose, container, false);

        customNumber = (EditText)view.findViewById(R.id.customNumber);

        backButton = (Button)view.findViewById(R.id.backButton2);
        confirmButton = (Button)view.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!customNumber.getText().toString().equals("")){
                    Intent intent = new Intent(getActivity(), HdCompetition.class);
                    intent.putExtra("QUANTITY", Integer.parseInt(customNumber.getText().toString().trim()));
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "Wrong input", Toast.LENGTH_SHORT).show();
                }

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft  = fm.beginTransaction();
                ft.replace(R.id.MainFragment, new CompetitionsFragment());
                ft.commit();
            }
        });

        return view;
    }

}
