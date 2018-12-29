package com.bignerdranch.android.memoriad.SecondActivity;

/**
 * Created by Rafichi on 14.09.2016.
 */
public class result {
    String date;
    int memoriseTime, answerTime, compoundTime, id;
    public result(int memoriseTime, int answerTime, String date, int id){
        this.memoriseTime = memoriseTime;
        this.answerTime = answerTime;
        this.date = date;
        this.compoundTime = answerTime + memoriseTime;
        this.id = id;
    }

    int getId(){
        return id;
    }
}
