package com.course.example.sqlitedemopro;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class HelpCenterLookup extends Activity implements OnClickListener {

    private EditText searchbar;
    private Button search;
    private TextView helpCenter;
    private static final String tag = "TermProject";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.lookup);

        searchbar = (EditText) findViewById(R.id.searchbar);
        helpCenter = (TextView) findViewById(R.id.helpinfo);
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(this);
    }

    public void onClick(View v) {
        Log.i(tag, "onClick invoked.");
        String courseID = searchbar.getText().toString();
        String[] subject = courseID.split("-");
        String subjectItem = subject[0];

        switch (subjectItem) {
            case "CS":
                helpCenter.setText(" CIS SANDBOX \n LOCATION: SMITH 201 \n HOURS: 11AM-11PM");
                Log.i(tag, "onClick complete.");
                break;

            case "AC":
                helpCenter.setText("ACELAB \nLOCATION: LINDSAY 21 \nHOURS: 11AM-11PM");
                Log.i(tag, "onClick complete.");
                break;

            case "MK":
                helpCenter.setText("CMT \nLOCATION: MORISON 220 \nHOURS: 11AM-11PM \nPHONE: 781-891-3115");
                Log.i(tag, "onClick complete.");
                break;


            case "MA":
                helpCenter.setText("MATH LEARNING CENTER \nLOCATION: JEN 218 \nHOURS: 9:30AM-9PM \nPHONE: 781-891-2084 \n\nECO-FI-STAT LEARNING CENTER \nLOCATION: AAC 122 \nHOURS: 1PM-9PM ");
                Log.i(tag, "onClick complete.");
                break;

            case "EMS":
            case "CIN":
            case "MC":
            case "IDCC":
                helpCenter.setText("MEDIA AND CULTURE LAB \nLOCATION: LIN 10 \nHOURS: 9:30AM-9PM \nPHONE: 781-891-2084 \n\nWRITING CENTER \nLOCATION: LIB 023 \nHOURS: 9:30AM-9PM \nPHONE 781-891-3173 \n\nESOL Center \nLOCATION: LIB 016 \nPHONE: 781-891-2021");
                Log.i(tag, "onClick complete.");
                break;

            case "EXPOS":
            case "LIT":
                helpCenter.setText("WRITING CENTER \nLOCATION: LIB 023 \nHOURS: 9:30AM-9PM \nPHONE 781-891-3173 \n\nESOL Center \nLOCATION: LIB 016 \nPHONE: 781-891-2021");
                Log.i(tag, "onClick complete.");
                break;

            case "FI":
            case "EC":
                helpCenter.setText("ECO-FI-STAT LEARNING CENTER \nLOCATION: AAC 122 \nHOURS: 1PM-9PM");
                Log.i(tag, "onClick complete.");
                break;

            case "SL":
                helpCenter.setText("BSLCE \nLOCATION: MOR 101 \nPHONE: 781.891.2170");
                Log.i(tag, "onClick complete.");
                break;

            case "CDI" :
                helpCenter.setText("Undergraduate Career Services \nLOCATION: LAC 225 \nHOURS: 8:30AM-4:30PM \nPHONE: 781-891-2165");
                Log.i(tag, "onClick complete.");
                break;


            case "" :
            default:
                helpCenter.setText("Undergraduate Academic Services \nLOCATION: JEN 336 \nPHONE: 781-891-2803");
                Log.i(tag, "onClick complete.");
                break;




        }


    }
}

