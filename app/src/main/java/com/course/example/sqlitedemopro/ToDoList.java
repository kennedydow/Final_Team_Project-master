package com.course.example.sqlitedemopro;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ToDoList extends Activity implements AdapterView.OnItemClickListener, TextToSpeech.OnInitListener {
    //Define variables
    ListView listView;
    TextView textView;
    EditText note_input;


    File sdcard;
    File file_read;
    InputStream in;
    InputStreamReader isr;
    BufferedReader reader;

    private TextToSpeech speaker;
    int pid;
    private final String file = "list.txt";
    private OutputStreamWriter out;
    FileOutputStream fos = null;

    //Datastores for list items
    List<String> listItem;
    ArrayAdapter<String> adapter;

    private static final String tag = "Widgets";
    int current_pos; //When user clicks Item of list this points to that position in the ArrayList

    private LinearLayout layoutToAnimate1;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutToAnimate1 = (LinearLayout)findViewById(R.id.layout01);
        layoutToAnimate1.setVisibility(View.GONE);


        //Variables for XML components
        listView = (ListView) findViewById(R.id.list_view);
        listView.setOnItemClickListener(this);              //attach listener
        textView = (EditText) findViewById(R.id.textView);
        note_input = findViewById(R.id.editText);
        speaker = new TextToSpeech(this, this);
        listItem = new ArrayList<>();

        //Initializes and sets adapter
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

                /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.BLUE);
                textView.setBackgroundColor(Color.parseColor("#b6f442"));

                return view;
            }
        };

        listView.setAdapter(adapter);

        try {
            File file_obj = new File(file);
            Log.i(tag, "Looking for file");
            //  if(file_obj.exists()) {
            Log.i(tag, "File found");

            in = openFileInput(file);
            isr = new InputStreamReader(in);
            reader = new BufferedReader(isr);
            String str = null;

            int count = 0;
            Log.i(tag, "Starting loop to read list");

            while ((str = reader.readLine()) != null) {
                Log.i(tag, str);
                adapter.add(str);
            }

            reader.close();

        }   catch(IOException e){
            Log.i(tag,"list.txt file not found");
        }

    }




    public void speak(String output){
        // speaker.speak(output, TextToSpeech.QUEUE_FLUSH, null);  //for APIs before 21
        speaker.speak(output, TextToSpeech.QUEUE_FLUSH, null, "Id 0");
    }




    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



    public void onInit(int status) {
        // status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
        if (status == TextToSpeech.SUCCESS) {
            // Set preferred language to US english.
            // If a language is not be available, the result will indicate it.
            int result = speaker.setLanguage(Locale.US);

            //int result = speaker.setLanguage(Locale.FRANCE);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Language data is missing or the language is not supported.
                Log.e(tag, "Language is not available.");
            } else {
                // The TTS engine has been successfully initialized
                speak("Welcome");
                Log.i(tag, "TTS Initialization successful.");
            }
        } else {
            // Initialization failed.
            Log.e(tag, "Could not initialize TextToSpeech.");
        }
    }




    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        try {
            current_pos = position;
            String value = (String) parent.getItemAtPosition(position);

            //Sets edit textbox to the selected value
            note_input.setText(value.substring(3).trim());

            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        //Takes text in the edit text box
        String input = note_input.getText().toString();


        switch (item.getItemId()) {

            case R.id.add:

                try {
                    Log.i(tag, "ADD: " + input);

                    //Adds item to adapter
                    adapter.add(adapter.getCount() + 1 + ".    " + input);
                    adapter.notifyDataSetChanged();

                    //Every time after first adding new items. It appends the ArrayList too
                    if (adapter.getCount() != listItem.size()) {
                        Log.i(tag, "Added by list...");
                        listItem.add(listItem.size() + 1 + ".    " + input);
                    }

                    //Resets the EditText box
                    note_input.setText("");

                    for (int i = 0; i < listItem.size(); i++) {
                        Log.i(tag, i + ": " + listItem.get(i));
                    }

                    current_pos = -1; //Resets position
                    return speak_answer(input + " Was added");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error, nothing selected", Toast.LENGTH_SHORT).show();
                    return true;
                }

            case R.id.delete:

                try {
                    Log.i(tag, "Delete: " + input);
                    Log.i(tag, "Current pos: " + current_pos);


                    listItem.remove(current_pos); //Removes item from list
                    listItem = remake_array(listItem); //Remakes list so that number labels are in order

                    Log.i(tag, "After Remake");

                    for (int i = 0; i < listItem.size(); i++) {
                        Log.i(tag, i + ": " + listItem.get(i));
                    }

                    //Following three lines are to update the adapter
                    adapter.clear();
                    adapter.addAll(listItem);
                    adapter.notifyDataSetChanged();

                    //Resets the edit text box and current position
                    note_input.setText("");
                    current_pos = -1;
                    return speak_answer(input + " Was deleted");
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Nothing selected to delete", Toast.LENGTH_SHORT).show();
                    return true;
                }

            case R.id.update:

                try {

                    Log.i(tag, "Update: " + input);
                    Log.i(tag, "Current pos: " + current_pos);

                    String orig = listItem.get(current_pos);

                    //Updates the ArrayList
                    listItem.remove(current_pos); //Removes item from list
                    listItem.add(current_pos, current_pos+1 + ".    " + input);
                    listItem = remake_array(listItem); //Remakes list so that number labels are in order

                    adapter.clear();
                    adapter.addAll(listItem);
                    adapter.notifyDataSetChanged();

                    Log.i(tag, "After Update");

                    for (int i = 0; i < listItem.size(); i++) {
                        Log.i(tag, i + ": " + listItem.get(i));
                    }

                    note_input.setText("");
                    current_pos = -1;
                    return speak_answer(orig + " was changed to " + input);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Nothing selected to update", Toast.LENGTH_SHORT).show();
                    return true;
                }

            case R.id.complete:
                try {
                    Log.i(tag, "Delete: " + input);
                    Log.i(tag, "Current pos: " + current_pos);


                    listItem.remove(current_pos); //Removes item from list
                    listItem = remake_array(listItem); //Remakes list so that number labels are in order

                    Log.i(tag, "After Remake");

                    for (int i = 0; i < listItem.size(); i++) {
                        Log.i(tag, i + ": " + listItem.get(i));
                    }

                    //Following three lines are to update the adapter
                    adapter.clear();
                    adapter.addAll(listItem);
                    adapter.notifyDataSetChanged();

                    Animation an1 =  AnimationUtils.loadAnimation(this, R.anim.snazzytext);
                    an1.setAnimationListener(new MyAnimationListener());
                    layoutToAnimate1.setVisibility(View.VISIBLE);
                    layoutToAnimate1.startAnimation(an1);

                    //Resets the edit text box and current position
                    note_input.setText("");
                    current_pos = -1;
                    return speak_answer(input + " Was completed");

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Nothing selected to complete", Toast.LENGTH_SHORT).show();
                    return true;
                }

                //If the user presses the close or save button it closes the app
            case R.id.close:
                //Save contents of file to list.txt
                add_to_file(file);
                Log.i(tag, "Saved contents of list to file");

                Intent i1 = new Intent(this, homepage.class);
                startActivityForResult(i1,30);
                return true;

            case R.id.save:
                //Save contents of file to list.txt
                add_to_file(file);
                Log.i(tag, "Saved contents of list to file");

                Intent i2 = new Intent(this, homepage.class);
                startActivityForResult(i2,30);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //Function to remake the array so number labels are accurate
    public List<String> remake_array(List<String> lists){
        List<String> listItemtemp = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            listItemtemp.add(i + 1 + ".    " + lists.get(i).substring(3).trim());
        }
        return listItemtemp;
    }

    public ArrayAdapter<String> remake_adapter(ArrayAdapter<String> a) {
        ArrayAdapter<String> adapter_temp;
        adapter_temp = a;
        a.clear();
        for(int i = 0; i<adapter_temp.getCount();i++) {
            a.add(i + 1 + ".    " + adapter_temp.getItem(i));
            Log.i(tag, "remake array"  +i + 1 + ".    " + adapter_temp.getItem(i));
        }
        return a;
    }

    //On item add, update and delete speaks to user
    public boolean speak_answer(String text_speak){
        try {
            if (speaker.isSpeaking()) {
                Log.i(tag, "Speaker Speaking");
                speaker.stop();
                // else start speech
            } else {
                Log.i(tag, "Speaker Not Already Speaking");
                speak(text_speak);
            }
        }
        catch(Exception e){
            Log.e(tag, "Text to speech error");
        }
        return true;
    }


    //Method to add current items to a list.txt file
    public void add_to_file(String file_names){
        try {
            out = new OutputStreamWriter(openFileOutput(file_names, MODE_APPEND)); // also try MODE_APPEND
            fos = openFileOutput(file,MODE_PRIVATE);
            for(int i = 0; i<listItem.size(); i++){
                out.write(listItem.get(i));
                fos.write((listItem.get(i)+"\n").getBytes());
                Log.i(tag,"Wrote " + listItem.get(i));
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
class MyAnimationListener implements Animation.AnimationListener {

    @Override
    public void onAnimationStart(Animation animation) {

    }

    public void onAnimationEnd(Animation animation) {



    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}