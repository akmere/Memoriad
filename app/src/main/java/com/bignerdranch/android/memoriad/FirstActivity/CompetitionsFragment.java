package com.bignerdranch.android.memoriad.FirstActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bignerdranch.android.memoriad.CardsActivity.CardsActivity;
import com.bignerdranch.android.memoriad.SecondActivity.HdCompetition;
import com.bignerdranch.android.memoriad.R;

import java.util.ArrayList;
import java.util.List;

public class CompetitionsFragment extends Fragment implements RecyclerAdapterCompetitions.ListenerSetting{

    List<String> competitionsList = new ArrayList<>();
    String[] competitions;

    private RecyclerView recycler;
    private RecyclerAdapterCompetitions adapter;

    public CompetitionsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);

        competitionsList.add("100 digits");
        competitionsList.add("50 digits");
        competitionsList.add("default digits");
        competitionsList.add("52 cards");
        for(int i = 1; i<100; i++) competitionsList.add("Default competition");
        competitions = competitionsList.toArray(new String [competitionsList.size()]);

        recycler = (RecyclerView)view.findViewById(R.id.recycler);
        adapter = new RecyclerAdapterCompetitions(competitions);
        adapter.setListenerSetting(this);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);

        return view;
    }

    @Override
    public void moveData(View view, int position) {
        switch (position){
            case 0:
            {
                Intent intent = new Intent(getActivity(), HdCompetition.class);
                intent.putExtra("QUANTITY", 100);
                startActivity(intent);
                break;
            }
            case 1:
            {
                Intent intent = new Intent(getActivity(), HdCompetition.class);
                intent.putExtra("QUANTITY", 50);
                startActivity(intent);
                break;
            }
            case 2:
                DefaultChooseFragment fragment = new DefaultChooseFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.MainFragment, fragment);
                ft.commit();
                break;
            case 3:
                Intent intent = new Intent(getActivity(), CardsActivity.class);
                intent.putExtra("QUANTITY", 52);
                startActivity(intent);
                break;

            default: Toast.makeText(getContext(), "Competition not found", Toast.LENGTH_SHORT).show();
        }

    }

}
