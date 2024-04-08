package com.example.physics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class MainActivity extends AppCompatActivity {
    private int cor_pincel;
    private ConstraintLayout toolbar;
    private ImageView config, play, color, pencil, hide;
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
        toolbar = findViewById(R.id.toolbar);
    }
    public void acaoComponentes(){
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorPickerDialog.Builder(MainActivity.this)
                        .setTitle("Cor do Pincel")
                        .setPositiveButton(getString(R.string.select),
                                new ColorEnvelopeListener() {
                                    @Override
                                    public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                                        cor_pincel = envelope.getColor();
                                        pencil.setColorFilter(envelope.getColor());
                                        color.setColorFilter(envelope.getColor());
                                    }
                                })
                        .setNegativeButton(getString(R.string.cancel),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                })
                        .attachAlphaSlideBar(true)
                        .attachBrightnessSlideBar(true)
                        .setBottomSpace(12)
                        .show();
            }
        });
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(toolbar.getHeight()>0){
                    toolbar.setMaxHeight(0);
                    hide.setImageResource(R.drawable.up);
                }else{
                    hide.setImageResource(R.drawable.down);
                    int dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, MainActivity.this.getResources().getDisplayMetrics());
                    toolbar.setMaxHeight(dp);
                }
            }
        });
    }
}