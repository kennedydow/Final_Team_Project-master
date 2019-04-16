package com.course.example.sqlitedemopro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;


public class AddCourse extends Activity {
    private EditText name_course;
    private EditText teacher;
    private EditText time;
    private Button add_course_button;
    private SQLHelper helper;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);
        setTitle("Add Course");

        // Get a handle to all user interface elements
        name_course = (EditText) findViewById(R.id.class_name);
        teacher = (EditText) findViewById(R.id.teacher);
        time = (EditText) findViewById(R.id.time);
        add_course_button = (Button) findViewById(R.id.add_course_button);
        helper = new SQLHelper(this);

        add_course_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                   int a = helper.getCourseList().size();
                Log.i("Added Number: ",""+a );
                helper.addCourse(new Course(a,name_course.getText().toString(), teacher.getText().toString(),time.getText().toString()));
                   name_course.setText("");
                   teacher.setText("");
                   time.setText("");
            }
        });

    }


}
