package com.example.physics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;

import com.skydoves.colorpickerview.ColorPickerDialog;

public class MainActivity extends AppCompatActivity {
    private AppCompatButton config, play, color, pencil, hide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        declaracaoComponentes();
        acaoComponentes();
    }
    public void declaracaoComponentes(){
        config = findViewById(R.id.setting);
        play = findViewById(R.id.play);
        color = findViewById(R.id.color);
        pencil = findViewById(R.id.pencil);
        hide = findViewById(R.id.hide);
    }
    public void acaoComponentes(){
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorPickerDialog.Builder(MainActivity.this)
                        .setTitle("Cor do Pincel")
                        .setPositiveButton()
                        .setNegativeButton()
                        .attachAlphaSlideBar(true)
                        .attachBrightnessSlideBar(true)
                        .setBottomSpace(12)
                        .show();
            }
        });
    }
}