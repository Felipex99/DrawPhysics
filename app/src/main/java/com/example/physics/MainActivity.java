package com.example.physics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private AppCompatButton config, play, color, pencil, hide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        declaracaoComponentes();
    }
    public void declaracaoComponentes(){
        config = findViewById(R.id.setting);
        play = findViewById(R.id.play);
        color = findViewById(R.id.color);
        pencil = findViewById(R.id.pencil);
        hide = findViewById(R.id.hide);
    }
}