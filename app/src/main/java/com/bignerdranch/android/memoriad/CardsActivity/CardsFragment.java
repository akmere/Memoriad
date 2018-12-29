package com.bignerdranch.android.memoriad.CardsActivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bignerdranch.android.memoriad.Database.DatabaseAccess;
import com.bignerdranch.android.memoriad.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardsFragment extends Fragment implements View.OnClickListener{

    EditText answerCards, rememberCards;
    Button backButton, confirmButton, resultsButton;
    TextView confirmText;
    DatabaseAccess db;
    int quantity;

    public CardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cards, container, false);

        answerCards = (EditText)view.findViewById(R.id.answerCards);
        rememberCards = (EditText)view.findViewById(R.id.rememberCards);
        backButton = (Button)view.findViewById(R.id.cardsBackButton);
        confirmButton = (Button)view.findViewById(R.id.cardsConfirmButton);
        resultsButton = (Button) view.findViewById(R.id.cardsResultsButton);
        confirmText = (TextView)view.findViewById(R.id.cardsConfirmText);
        confirmText.setText("");
        quantity = getActivity().getIntent().getIntExtra("QUANTITY", 66);

        db = DatabaseAccess.getInstance(getContext());
        db.open();

        backButton.setOnClickListener(this);
        confirmButton.setOnClickListener(this);
        resultsButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == backButton){
            getActivity().onBackPressed();
        }

        if(v == confirmButton){
            if(!answerCards.getText().toString().equals("") && !rememberCards.getText().toString().equals("")){
                db.insertCardResult(quantity, Integer.parseInt(answerCards.getText().toString()), Integer.parseInt(rememberCards.getText().toString()));
                answerCards.setText(""); rememberCards.setText("");
                confirmText.setText("result confirmed");
            }else confirmText.setText("wrong input");
        }

        if(v == resultsButton){
            FragmentTransaction ft = (getActivity().getSupportFragmentManager()).beginTransaction();
            CardsResultsFragment cardsResultsFragment = new CardsResultsFragment();
            cardsResultsFragment.setQuantity(quantity);
            ft.replace(R.id.cardsMainFragment, cardsResultsFragment);
            ft.commit();
        }
    }
}
