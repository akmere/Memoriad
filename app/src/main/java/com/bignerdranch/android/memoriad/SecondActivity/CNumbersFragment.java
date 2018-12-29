package com.bignerdranch.android.memoriad.SecondActivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.android.memoriad.Database.DatabaseAccess;
import com.bignerdranch.android.memoriad.R;
import com.bignerdranch.android.memoriad.SecondActivity.timesFragment;

import java.util.Random;


public class CNumbersFragment extends Fragment implements View.OnClickListener{

    Button startButton, backButton, resultButton;
    TextView ohText, additionalText;
    String numberList, answerList;
    long startTime, timeToMemorise, timeToAnswer, compoundTime;
    int maxi;
    int j = 0;
    DatabaseAccess db;

    public CNumbersFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);

        maxi = getActivity().getIntent().getIntExtra("QUANTITY", 50);

        db = DatabaseAccess.getInstance(getActivity());
        db.open();

        numberList = ""; answerList = "";
        startButton = (Button)view.findViewById(R.id.startButton);
        startButton.setOnClickListener(this);
        resultButton = (Button)view.findViewById(R.id.resultButton);
        resultButton.setOnClickListener(this);
        backButton = (Button)view.findViewById(R.id.backButton);
        backButton.setOnClickListener(this);
        ohText = (TextView)view.findViewById(R.id.ohtext);
        additionalText = (TextView)view.findViewById(R.id.additionalText);

        return view;
    }

    @Override
    public void onClick(View v) {

        if(v==startButton && startButton.getText().equals("Stop!")){
            timeToMemorise = (System.currentTimeMillis() - startTime)/1000;
            startTime = System.currentTimeMillis();
            ohText.setText("");
            ohText.setGravity(0x03);
            startButton.setText("Check number");
        }

        else if(v==startButton && startButton.getText().equals("Start!")){
            Random rand = new Random();
            for(int i = 1; i<=maxi; i++){
                numberList+= Integer.toString(rand.nextInt(10));
            }
            ohText.setText(numberList);
            startTime = System.currentTimeMillis();
            startButton.setText("Stop!");
        }

        else if(v==startButton && startButton.getText().equals("Check number")){
            if(j==maxi){
                timeToAnswer = (System.currentTimeMillis() - startTime)/1000;
                compoundTime = timeToAnswer + timeToMemorise;
                ohText.setGravity(0x11);
                ohText.setText("Time to memorise: " + timeToMemorise + "s\nTime to answer: " + timeToAnswer + "s\nCompound time: " + compoundTime +"s");
                startButton.setText("Confirm");
            } else{
                answerList += numberList.charAt(j);
                ohText.setText(answerList);
                j++;
            }
        }

        else if(v==startButton && startButton.getText().equals("Confirm")){
            additionalText.setText("Result confirmed!");
            db.insertNumberResult(maxi, (int)compoundTime, (int)timeToAnswer, (int)timeToMemorise);
            startButton.setEnabled(false);
        }

        if (v==backButton && !startButton.getText().equals("Start!")){
            ohText.setText("click Start to begin\ncountdown and memorise digits!");
            startButton.setText("Start!");
            j=0;
            numberList="";
            answerList="";
            startButton.setEnabled(true);
            additionalText.setText("");
        } else if(v==backButton && !startButton.getText().equals("Start")){
            getActivity().onBackPressed();
        }

        if(v==resultButton){
            timesFragment fragment = new timesFragment();
            fragment.setQuantity(maxi);
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.competitionFragment, fragment);
            ft.commit();
        }

    }

}
