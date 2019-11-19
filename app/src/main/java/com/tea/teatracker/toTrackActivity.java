package com.tea.teatracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class toTrackActivity extends AppCompatActivity
{
    List<String> fullList = new ArrayList<>();
    List<Float> costList = new ArrayList<>();
    List<Float> calList = new ArrayList<>();
    ListView myList;
    ArrayAdapter myAdapter;
    TextView poor;
    TextView fat;
    TextView month;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        hideSystemUI();
        mathFunc();
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.track_activity);

        myList = findViewById(R.id.listView);
        myAdapter = new CustomAdapter(this, R.layout.mylayout, fullList);
        myList.setAdapter(myAdapter);

        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String monthName = month_date.format(cal.getTime());

        fat = findViewById(R.id.monthCal);
        poor = findViewById(R.id.monthPay);
        month = findViewById(R.id.month);
        fat.setText("Calories:\n" + String.format ("%.0f", total(calList)));
        poor.setText("Spent:\n$" + String.format ("%.2f", total(costList)));
        month.setText(monthName);
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
    public void mathFunc()
    {
        try {
            FileInputStream fIn = openFileInput("data.txt");
            InputStreamReader isr = new InputStreamReader(fIn);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            float x;
            float y;
            while (line != null) {
                String[] holder = new String[3];
                holder = line.split("-");
                x = Float.parseFloat(holder[1]);
                y = Float.parseFloat(holder[2]);
                fullList.add("Name: "+ holder[0] + "\n" + "Price:"+ " $"+ String.format("%.2f", x) + "\n" + "Calories:" + " " + String.format("%.0f", y) + "cal");
                costList.add(x);
                calList.add(y);
                line = br.readLine();
            }
            isr.close();
        }
        catch (Exception e)
        {

        }
    }
    private double total(List <Float> marks) {
        double sum = 0;
        if(!marks.isEmpty()) {
            for (double mark : marks) {
                sum += mark;
            }
            return sum;
        }
        return sum;
    }
    public void back(View view)
    {
        Intent myIntent = new Intent(toTrackActivity.this, MainActivity.class);
        startActivity(myIntent);
    }
}