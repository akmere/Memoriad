package com.bignerdranch.android.memoriad.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.memoriad.CardsActivity.cardsResult;
import com.bignerdranch.android.memoriad.SecondActivity.result;

import java.util.ArrayList;

public class DatabaseAccess {
    private static SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void insertNumberResult(int Quantity, int compoundTime, int answerTime, int memoriseTime){
        database.execSQL("INSERT INTO [NUMBERS RESULTS](Numbers, CompoundTime, AnswerTime, MemoriseTime) VALUES (?, ?, ?, ?)", new String[]{Integer.toString(Quantity), Integer.toString(compoundTime), Integer.toString(answerTime), Integer.toString(memoriseTime)});
    }

    public ArrayList<result> getList(int quantity){
        ArrayList<result> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT MemoriseTime, AnswerTime, Date, id FROM [Numbers Results] WHERE Numbers = ? ORDER BY CompoundTime", new String[]{Integer.toString(quantity)});
        while (cursor.moveToNext()){
            int memoriseTime = cursor.getInt(0);
            int answerTime = cursor.getInt(1);
            String date = cursor.getString(2);
            int id = cursor.getInt(3);
            list.add(new result(memoriseTime, answerTime, date, id));
        }
        return list;
    }

    public ArrayList<result> getDateList(int quantity){
        ArrayList<result> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT MemoriseTime, AnswerTime, Date, id FROM [Numbers Results] WHERE Numbers = ? ORDER BY Date", new String[]{Integer.toString(quantity)});
        while (cursor.moveToNext()){
            int memoriseTime = cursor.getInt(0);
            int answerTime = cursor.getInt(1);
            String date = cursor.getString(2);
            int id = cursor.getInt(3);
            list.add(new result(memoriseTime, answerTime, date, id));
        }
        return list;
    }

    public ArrayList<cardsResult> getCardsList(int quantity){
        ArrayList<cardsResult> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT rememberTime, answerTime, Date, _id FROM [Cards Results] WHERE Number = ? ORDER BY compoundTime", new String[]{Integer.toString(quantity)});
        while (cursor.moveToNext()){
            int memoriseTime = cursor.getInt(0);
            int answerTime = cursor.getInt(1);
            String date = cursor.getString(2);
            int id = cursor.getInt(3);
            list.add(new cardsResult(memoriseTime, answerTime, date, id));
        }
        return list;
    }

    public ArrayList<cardsResult> getDateCardsList(int quantity){
        ArrayList<cardsResult> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT rememberTime, answerTime, Date, _id FROM [Cards Results] WHERE Number = ? ORDER BY Date", new String[]{Integer.toString(quantity)});
        while (cursor.moveToNext()){
            int memoriseTime = cursor.getInt(0);
            int answerTime = cursor.getInt(1);
            String date = cursor.getString(2);
            int id = cursor.getInt(3);
            list.add(new cardsResult(memoriseTime, answerTime, date, id));
        }
        return list;
    }

    public void insertCardResult(int Quantity, int answerTime, int memoriseTime){
        database.execSQL("INSERT INTO [CARDS RESULTS] (answerTime, rememberTime, compoundTime, Number) VALUES (?, ?, ?, ?)",
                new String[] {Integer.toString(answerTime), Integer.toString(memoriseTime), Integer.toString(answerTime + memoriseTime), Integer.toString(Quantity)});
    }

    public void remove(int id){
        database.execSQL("DELETE FROM [NUMBERS RESULTS] WHERE ID = ?", new String[] {Integer.toString(id)});
    }

    public void removeCards(int id){
        database.execSQL("DELETE FROM [Cards RESULTS] WHERE _ID = ?", new String[] {Integer.toString(id)});
    }

}