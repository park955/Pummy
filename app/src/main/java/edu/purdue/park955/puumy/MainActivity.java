package edu.purdue.park955.puumy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Declaring Views as fields so we can use it later
    TextView introText;
    TextView statusText;
    Button sampleButton;
    Button saveButton;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaring the fields onCreate so we can use it later
        this.getViews();

        //How to load a saved Data
        int loadValue = this.loadData("SeekBarValue");

        //Setting some initial values
        saveButton.setText("Save");
        if(loadValue == -1) {
            statusText.setText("No values to load");
        } else {
            statusText.setText("Loaded Value: "+loadValue);
        }
    }

    //How to get View components into backend Java code
    private void getViews(){
        this.introText = findViewById(R.id.introText);
        this.statusText = findViewById(R.id.statusText);
        this.sampleButton = findViewById(R.id.sampleButton);
        this.saveButton = findViewById(R.id.saveButton);
        this.seekBar = findViewById(R.id.seekBar);
    }

    //How to create a simple Button function
    public void pressedSampleButton(View view){
        Context context = getApplicationContext();
        Toast.makeText(context, "Hi, there! I'm Pummy!", Toast.LENGTH_SHORT).show();
    }

    //How to save a data
    public void pressedSaveButton(View view){

        //Get values from the seekBar
        int value = this.seekBar.getProgress();

        //Saving the value using sharedPreference
        SharedPreferences sp = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        sp.edit().putInt("SeekBarValue", value).apply();

        //Toast to show the user that it is saved
        Context context = getApplicationContext();
        Toast.makeText(context, "The value "+value+" is saved",Toast.LENGTH_SHORT).show();
    }

    //How to load a data
    public int loadData(String key){

        //Loading the value using sharedPreference
        SharedPreferences sp = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        return sp.getInt(key,-1);

    }
}
