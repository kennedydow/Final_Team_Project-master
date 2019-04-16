package com.course.example.sqlitedemopro;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class homepage extends Activity implements View.OnClickListener {

    private Button button;
    private Button button1;
    private Button button2;
    private Button button3;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        setTitle("Homework Application");
        button = findViewById(R.id.button); button.setOnClickListener(this);
        button1 = findViewById(R.id.button1); button1.setOnClickListener(this);
        button2 = findViewById(R.id.button2); button2.setOnClickListener(this);
        button3 = findViewById(R.id.button3); button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        try {

            if (v.getId() == R.id.button) {
                Log.i("Button Clicked: ", "Course List");
                Intent i1 = new Intent(this, CourseList.class);
                startActivityForResult(i1,30);
            } else if (v.getId() == R.id.button1) {
                Log.i("Button Clicked: ", "Grade Calculator");
                Intent i2 = new Intent(this, GradeCalculator.class);
                startActivityForResult(i2,30);
            } else if (v.getId() == R.id.button2) {
                Log.i("Button Clicked: ", "To do List");
                Intent i3 = new Intent(this, ToDoList.class);
                startActivityForResult(i3,30);
            } else if (v.getId() == R.id.button3) {
                Log.i("Button Clicked: ", "Help Center");
                Intent i4 = new Intent(this, HelpCenterLookup.class);
                startActivityForResult(i4,30);
            }


        } catch (Exception e) {

        }


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }
}