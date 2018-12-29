package com.bignerdranch.android.memoriad.FirstActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.android.memoriad.R;

/**
 * Created by Rafichi on 11.09.2016.
 */
public class RecyclerAdapterCompetitions extends RecyclerView.Adapter<RecyclerAdapterCompetitions.MyViewHolder> {

    String[] competitions;
    static ListenerSetting listenerSetting;

    public RecyclerAdapterCompetitions(String[] competitions){
        this.competitions = competitions;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.competitionView.setText(competitions[position]);
    }

    @Override
    public int getItemCount() {
        return competitions.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView competitionView;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            competitionView = (TextView) itemView.findViewById(R.id.competitionView);
        }

        @Override
        public void onClick(View v) {
            if(listenerSetting!= null)
            listenerSetting.moveData(v, getAdapterPosition());
        }
    }

    public interface ListenerSetting{
        void moveData(View view, int position);
    }

    public void setListenerSetting(ListenerSetting listenerSetting){
        this.listenerSetting = listenerSetting;
    }

}
