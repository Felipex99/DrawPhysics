package com.example.physics;
import static com.example.physics.Screen.color_brush;
import static com.example.physics.Screen.path_list;
import static com.example.physics.Screen.color;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class MainActivity extends AppCompatActivity {
    private int cor_pincel = Color.BLACK;
    private ConstraintLayout toolbar;
    private ImageView config, play, color, pencil, hide, eraser;
    public static Path path = new Path();
    public static Paint pencil_brush = new Paint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        declaracaoComponentes();
        acaoComponentes();
    }

    public void corAtual(int cor){
        color_brush = cor;
        path = new Path();
    }
    public void declaracaoComponentes(){
        config = findViewById(R.id.setting);
        play = findViewById(R.id.play);
        color = findViewById(R.id.color);
        pencil = findViewById(R.id.pencil);
        hide = findViewById(R.id.hide);
        toolbar = findViewById(R.id.toolbar);
        eraser = findViewById(R.id.eraser);
    }
    public void acaoComponentes(){
        pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pincel(view);
                Toast.makeText(MainActivity.this, "Quantidades de Path:"+path_list.size(), Toast.LENGTH_SHORT).show();
            }
        });
        eraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eraser(view);
            }
        });
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
                                        color.setColorFilter(envelope.getColor());
                                        pincel(view);
                                        corAtual(cor_pincel);
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

    public void pincel(View view){
        pencil_brush.setColor(cor_pincel);
        corAtual(cor_pincel);
    }
    public void eraser(View view){
        path_list.clear();
        Screen.color.clear();
        path.reset();
    }
}