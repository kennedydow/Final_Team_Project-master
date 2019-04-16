package com.course.example.sqlitedemopro;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GradeCalculator extends Activity implements View.OnClickListener {

    private EditText grade1,grade2,grade3,grade4,grade5,grade6,grade7,grade8;
    private EditText weight1,weight2,weight3,weight4,weight5,weight6,weight7,weight8;
    private Button gof;
    private double output1;
    private String grade;
    private TextView raw;
    private TextView convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gradecalculator);
        grade1 = (EditText) findViewById(R.id.grade1);
        grade2 = (EditText) findViewById(R.id.grade2);
        grade3 = (EditText) findViewById(R.id.grade3);
        grade4 = (EditText) findViewById(R.id.grade4);
        grade5 = (EditText) findViewById(R.id.grade5);
        grade6 = (EditText) findViewById(R.id.grade6);
        grade7 = (EditText) findViewById(R.id.grade7);
        grade8 = (EditText) findViewById(R.id.grade8);
        weight1 = (EditText) findViewById(R.id.weight1);
        weight2 = (EditText) findViewById(R.id.weight2);
        weight3 = (EditText) findViewById(R.id.weight3);
        weight4 = (EditText) findViewById(R.id.weight4);
        weight5 = (EditText) findViewById(R.id.weight5);
        weight6 = (EditText) findViewById(R.id.weight6);
        weight7 = (EditText) findViewById(R.id.weight7);
        weight8 = (EditText) findViewById(R.id.weight8);
        raw = (TextView)findViewById(R.id.raw);
        convert = (TextView)findViewById(R.id.convert);
        gof = (Button) findViewById(R.id.go);
        gof.setOnClickListener(this);

    }

    // Perform action on click
    public void onClick(View v) {
        try {


            output1 = Float.parseFloat(grade1.getText().toString()) * (Float.parseFloat(weight1.getText().toString())/100)
                    + Float.parseFloat(grade2.getText().toString()) * (Float.parseFloat(weight2.getText().toString())/100)
                    + Float.parseFloat(grade3.getText().toString()) * (Float.parseFloat(weight3.getText().toString())/100)
                    + Float.parseFloat(grade4.getText().toString()) * (Float.parseFloat(weight4.getText().toString())/100)
                    + Float.parseFloat(grade5.getText().toString()) * (Float.parseFloat(weight5.getText().toString())/100)
                    + Float.parseFloat(grade6.getText().toString()) * (Float.parseFloat(weight6.getText().toString())/100)
                    + Float.parseFloat(grade7.getText().toString()) * (Float.parseFloat(weight7.getText().toString())/100)
                    + Float.parseFloat(grade8.getText().toString()) * (Float.parseFloat(weight8.getText().toString())/100);
            raw.setText(String.format("%.2f", output1));

            if (output1 >= 95) {
                grade = "4.0";
            } else if (output1 >= 90) {
                grade = "3.7";
            } else if (output1 >= 87) {
                grade = "3.3";
            } else if (output1 >= 83) {
                grade = "3.0";
            } else if (output1>=80) {
                grade = "2.7";
            } else if (output1>=77) {
                grade = "2.3";
            } else if (output1>=73) {
                grade = "2.0";
            } else if (output1>=70) {
                grade = "1.7";
            } else if(output1>=67) {
                grade = "1.3";
            } else if(output1>=63) {
                grade = "1.0";
            } else if(output1>=60){
                grade = "0.7";
            } else {
                grade = "F";
            }

            convert.setText(grade);



        } catch (Exception e) {


        }


    }}
