package com.tea.teatracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        hideSystemUI();
        monthlyReset();
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }
    protected void onResume()
    {
        super.onResume();
        monthlyReset();
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
    public void toAdd(View view)
    {
        Intent myIntent = new Intent(MainActivity.this, toAddActivity.class);
        startActivity(myIntent);
    }
    public void toTrack(View view)
    {
        Intent myIntent = new Intent(MainActivity.this, toTrackActivity.class);
        startActivity(myIntent);
    }
    public void deleteData()
    {
        try {
            FileOutputStream fOut = openFileOutput("data.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);

            osw.append("");
            osw.flush();
            osw.close();
        }
        catch (Exception e)
        {

        }
    }
    public void replaceData(String monthName, String yearName)
    {
        try {
            FileOutputStream fOut = openFileOutput("reset.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write(monthName + "-" + yearName);
            osw.flush();
            osw.close();
            deleteData();
        }
        catch (Exception a)
        {

        }
    }
    public void monthlyReset()
    {
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String monthName = month_date.format(cal.getTime());
        SimpleDateFormat year_date = new SimpleDateFormat("yyyy");
        String yearName = year_date.format(cal.getTime());
        try {
            FileInputStream fIn = openFileInput("reset.txt");
            InputStreamReader isr = new InputStreamReader(fIn);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            String[] holder;
            holder = line.split("-");
            String dataMonth = holder[0];
            String dataYear = holder[1];
            if (!dataMonth.equals(monthName) || !dataYear.equals(yearName))
            {
                replaceData(monthName, yearName);
                deleteData();
            }
        }
        catch (Exception e)
        {
            replaceData(monthName, yearName);
            deleteData();
        }
    }
}
