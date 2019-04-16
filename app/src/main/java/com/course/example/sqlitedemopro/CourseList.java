package com.course.example.sqlitedemopro;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.content.ContentValues;
import android.text.method.ScrollingMovementMethod;
import android.widget.Toast;

public class CourseList extends Activity {

    private TextView text;
    private SQLiteDatabase db;
    int dbsize;
    private ContentValues values;
    private Button delete_button;
    private Cursor cursor;
    private SQLHelper helper;
    private TableLayout tableLayout;
    private ArrayList<Course> courseList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        text = (TextView) findViewById(R.id.Text);
        delete_button = findViewById(R.id.delete_button);

        //let textview widget scroll
        text.setMovementMethod(new ScrollingMovementMethod());

//        db = openOrCreateDatabase(helper.DATABASE_NAME,
//                Context.MODE_PRIVATE, null);
//        db.execSQL("DROP TABLE IF EXISTS " + helper.TABLE_NAME);
//        db.execSQL(helper.CREATE_TABLE);

        helper = new SQLHelper(this);

        //create database
        try {
            db = helper.getWritableDatabase();
        } catch(SQLException e) {
            Log.d("SQLiteDemo", "Create database failed");
        }

        //insert records
        //   helper.addCourse(new Course("CS 480", "PEPE","3:30"));



        //query database
        courseList = helper.getCourseList();

        tableLayout=(TableLayout)findViewById(R.id.tableLayout);
        View tableRow;

        for (Course item : courseList) {
            final Course temp_item = item;
            tableRow = LayoutInflater.from(this).inflate(R.layout.main, null, false);
            TextView num = tableRow.findViewById(R.id.num);
            TextView name = (TextView) tableRow.findViewById(R.id.name);
            TextView title = (TextView) tableRow.findViewById(R.id.title);
            TextView time = tableRow.findViewById(R.id.time);
            Button delete_button = (Button) tableRow.findViewById(R.id.delete_button);

            num.setText(""+ item.getclass_id());
            name.setText(item.getName());
            title.setText(item.getTeacher());
            time.setText(item.getTime());

            delete_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Log.i("ID:  ", view.getId()+"");
                    TableRow a = (TableRow) view.getParent();
                    TextView b = (TextView) a.getChildAt(1);
                    String c = b.getText().toString();

                    Log.i("ID:  ", c );

                    helper.deleteCourse(temp_item);
                    onCreate(new Bundle());
                }
            });

            tableLayout.addView(tableRow);
        }

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        //Takes text in the edit text box


        switch (item.getItemId()) {

            case R.id.add:

                try {
                    Intent i1 = new Intent(this, AddCourse.class);
                    startActivityForResult(i1,30);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error, nothing selected", Toast.LENGTH_SHORT).show();
                    return true;
                }


            default:
                return super.onOptionsItemSelected(item);
        }

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //close database
    @Override
    protected void onPause() {
        super.onPause();
        if(db != null)
            db.close();
    }
 public int getDbsize(){
     return courseList.size();
 }

}