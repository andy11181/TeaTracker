package com.tea.teatracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class toAddActivity extends AppCompatActivity
{
    String input;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        hideSystemUI();
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.add_activity);
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
    public void milkTea(View view)
    {
        input = "Milk Tea";
        Intent myIntent = new Intent(toAddActivity.this, infoActivity.class);
        myIntent.putExtra("key", input);
        startActivity(myIntent);
    }
    public void taroTea(View view)
    {
        input = "Taro Milk Tea";
        Intent myIntent = new Intent(toAddActivity.this, infoActivity.class);
        myIntent.putExtra("key", input);
        startActivity(myIntent);
    }
    public void matchaTea(View view)
    {
        input = "Matcha Milk Tea";
        Intent myIntent = new Intent(toAddActivity.this, infoActivity.class);
        myIntent.putExtra("key", input);
        startActivity(myIntent);
    }
    public void otherTea(View view)
    {
        Intent myIntent = new Intent(toAddActivity.this, infoActivity.class);
        myIntent.putExtra("key", "");
        startActivity(myIntent);
    }
}
