package com.tea.teatracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class infoActivity extends AppCompatActivity {
    EditText myTextView1;
    EditText myTextView2;
    EditText myTextView3;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        hideSystemUI();
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.info_activity);
        myTextView1 = findViewById(R.id.type);
        myTextView1.setText(getIntent().getExtras().getString("key"));
        myTextView2 = findViewById(R.id.cost);
        myTextView3 = findViewById(R.id.calories);
    }
    //documentation from android immersive mode
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }
    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
    //------------------------------
    public void submit(View view)
    {
        String name = myTextView1.getText().toString();
        float cost = 5;
        float cal = 300;

        try
        {
            cost = Float.parseFloat(myTextView2.getText().toString());
        }
        catch (Exception e)
        {
            cost = 5;
        }
        try
        {
            cal = Float.parseFloat(myTextView3.getText().toString());
        }
        catch (Exception e)
        {
            cal = 300;
        }

        try {
            String inputString = name + "-" + cost + "-" + cal;

            FileOutputStream fOut = openFileOutput("data.txt", MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);

            osw.append(inputString + "\n");
            osw.flush();
            osw.close();
        }
        catch (Exception e)
        {

        }
        Intent myIntent = new Intent(infoActivity.this, toTrackActivity.class);
        startActivity(myIntent);
    }
}
