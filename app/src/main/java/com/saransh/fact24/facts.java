package com.saransh.fact24;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;


public class facts extends ActionBarActivity {


    SQLiteDatabase db;
    TextView title,detail;
    int rand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);
        title=(TextView)findViewById(R.id.title);
        detail=(TextView)findViewById(R.id.detail);
        rand= (int)(Math.random()%24);
        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(this);
        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDbHelper.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }
    }
    public void next(View view)
    {
        rand= (int)(Math.random()%24);
        Cursor c=db.rawQuery("Select * from facts where id='rand'",null);
        title.setText(c.getString(1));
        detail.setText(c.getString(2));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_facts, menu);
        return true;
    }


}
