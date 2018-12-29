package com.bignerdranch.android.memoriad.CardsActivity;

public class cardsResult {
    String date;
    int memoriseTime, answerTime, compoundTime, id;
    public cardsResult(int memoriseTime, int answerTime, String date, int id){
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
