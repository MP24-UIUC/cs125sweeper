package com.cs125.cs125sweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cs125.cs125sweeper.R;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Game.getInstance().generateGrid(this);
    }
}