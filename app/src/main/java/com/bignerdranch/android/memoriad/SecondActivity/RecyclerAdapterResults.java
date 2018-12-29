package com.bignerdranch.android.memoriad.SecondActivity;

import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.android.memoriad.R;

import java.util.ArrayList;

public class RecyclerAdapterResults extends RecyclerView.Adapter<RecyclerAdapterResults.SecondViewHolder>{

    ArrayList<result> list;
    static moveResultData mMoveResultData;

    public RecyclerAdapterResults(ArrayList<result> list){
        this.list = list;
    }

    @Override
    public SecondViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.times_row, parent, false);

        return new SecondViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SecondViewHolder holder, int position) {
        holder.dateView.setText(list.get(position).date);
        holder.resultView.setText(list.get(position).memoriseTime + "/" + list.get(position).answerTime + "/" + list.get(position).compoundTime);
;    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class SecondViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView dateView, resultView;
        public SecondViewHolder(View itemView) {
            super(itemView);
            dateView = (TextView)itemView.findViewById(R.id.dataView);
            resultView = (TextView)itemView.findViewById(R.id.resultView);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, final View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(0, v.getId(), 0, "Delete");
            menu.add(0, v.getId(), 0, "Back");
            menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    mMoveResultData.moveData(getAdapterPosition(), v);
                    return true;
                }
            });
        }
    }

    interface moveResultData{
        void moveData(int position, View view);
    }

    public void setmMoveResultData(moveResultData m){
        mMoveResultData = m;
    }
}
