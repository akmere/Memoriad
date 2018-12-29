package com.bignerdranch.android.memoriad.SecondActivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bignerdranch.android.memoriad.Database.DatabaseAccess;
import com.bignerdranch.android.memoriad.R;

import java.util.ArrayList;
import java.util.Collections;

public class timesFragment extends Fragment implements View.OnClickListener, RecyclerAdapterResults.moveResultData{

    DatabaseAccess db;
    int quantity;
    ArrayList<result> mResultList;
    Button backButton, timeButton, dateButton;
    RecyclerView recycler;
    RecyclerAdapterResults adapter;
    int iData = 2;

    public timesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_times, container, false);
        db = DatabaseAccess.getInstance(getContext());
        backButton = (Button)view.findViewById(R.id.timesBack);
        backButton.setOnClickListener(this);
        dateButton = (Button)view.findViewById(R.id.dateButton);
        dateButton.setOnClickListener(this);
        timeButton = (Button)view.findViewById(R.id.timeButton);
        timeButton.setOnClickListener(this);

        recycler = (RecyclerView)view.findViewById(R.id.timeRecyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mResultList = db.getList(quantity);
        adapter = new RecyclerAdapterResults(mResultList);
        adapter.setmMoveResultData(this);
        recycler.setAdapter(adapter);

        return view;
    }

    public void setQuantity(int q){
        quantity = q;
    }

    @Override
    public void onClick(View v) {
        if (v==backButton){
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.competitionFragment, new CNumbersFragment());
            ft.commitNow();
        }
        if(v==timeButton){
            mResultList = db.getList(quantity);
            adapter = new RecyclerAdapterResults(mResultList);
            adapter.setmMoveResultData(this);
            recycler.setAdapter(adapter);
            iData = 2;
        }
        if(v==dateButton){
            if(iData%2==0){
                mResultList = db.getDateList(quantity);
                adapter = new RecyclerAdapterResults(mResultList);
                adapter.setmMoveResultData(this);
                recycler.setAdapter(adapter);
            } else{
                Collections.reverse(mResultList);
                adapter = new RecyclerAdapterResults(mResultList);
                adapter.setmMoveResultData(this);
                recycler.setAdapter(adapter);
            }
            iData++;
        }
    }

    @Override
    public void moveData(int position, View view) {
        db.remove(mResultList.get(position).getId());
        mResultList.remove(position);
        //recycler.removeViewAt(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, mResultList.size());
    }
}
