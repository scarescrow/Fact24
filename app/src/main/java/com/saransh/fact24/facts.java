package com.saransh.fact24;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;


public class facts extends ActionBarActivity {


    DataBaseHelper myDbHelper;
    TextView title,detail;
    int rand;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);
        title=(TextView)findViewById(R.id.title);
        detail=(TextView)findViewById(R.id.detail);
        b = (Button) findViewById(R.id.next);
        rand= (int)(Math.random()%24);

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

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                next(v);
            }

        });
    }
    public void next(View view)
    {
        rand= (int)(Math.random()%24) + 1; //+1 because in the databse, _id starts from 1

        String[] values = myDbHelper.getNewFact(rand);

        if(values == null) {
            Toast.makeText(getApplicationContext(),
                    "Some Error Occured. Please Try Again Later.",
                    Toast.LENGTH_LONG).show();

            return;
        }

        title.setText(values[0]);
        detail.setText(values[1]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_facts, menu);
        return true;
    }


}
