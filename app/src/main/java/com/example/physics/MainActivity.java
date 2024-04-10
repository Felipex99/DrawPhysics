package com.example.physics;
import static com.example.physics.Screen.color_brush;
import static com.example.physics.Screen.path_list;
import static com.example.physics.Screen.width_list;
import static com.example.physics.Screen.color_list;
import static com.example.physics.Screen.width_brush;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
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

import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;
import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class MainActivity extends AppCompatActivity {
    private int cor_pincel = Color.BLACK;
    private ImageView largura_item;
    private Float nova_largura = width_brush;
    private ConstraintLayout toolbar;
    private ImageView config, play, color, pencil, hide, eraser;
    private AppCompatButton selecionar_largura, cancelar_largura;
    public static Path path = new Path();
    public static Paint pencil_brush = new Paint();
    private Dialog dialog_largura;
    private Slider largura;
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
        eraser = findViewById(R.id.eraser);
        dialog_largura = new Dialog(MainActivity.this);
        dialog_largura.setContentView(R.layout.largura_dialog);
        largura = dialog_largura.findViewById(R.id.largura);
        selecionar_largura = dialog_largura.findViewById(R.id.selecionar_largura);
        cancelar_largura = dialog_largura.findViewById(R.id.cancelar_largura);
        largura_item = dialog_largura.findViewById(R.id.largura_depois);
    }
    public void acaoComponentes(){
        pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destacarEraser(false,view);
                pincel(view);
                Toast.makeText(MainActivity.this, "Quantidades de Path:"+path_list.size(), Toast.LENGTH_SHORT).show();
            }
        });
        pencil.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialog_largura.show();
                largura.setValue(nova_largura/10);
                largura_item.setColorFilter(cor_pincel);
                largura.addOnChangeListener(new Slider.OnChangeListener() {
                    @Override
                    public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                        nova_largura = value*10;
                        largura_item.setScaleY(value/10);
                    }
                });
                return false;
            }
        });
        eraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destacarEraser(true,view);

            }
        });
        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                destacarEraser(false,view);
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
        selecionar_largura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                largurAtual(nova_largura);
                dialog_largura.dismiss();
                largura_brush(view);
            }
        });
        cancelar_largura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                largura.setValue(nova_largura/10);
                dialog_largura.dismiss();
            }
        });
    }

    public void pincel(View view){
        pencil_brush.setColor(cor_pincel);
        corAtual(cor_pincel);
    }
    public void corAtual(int cor){
        color_brush = cor;
        path = new Path();
    }
    public void largura_brush(View view){
        pencil_brush.setStrokeWidth(nova_largura);
        largurAtual(nova_largura);
    }
    public void largurAtual(float largura){
        width_brush = largura;
        path = new Path();
    }
    public void eraser(View view){
        path_list.clear();
        Screen.color_list.clear();
        path.reset();
    }
    public void destacarEraser(boolean ativo, View view){
        if(ativo){
            eraser(view);
            eraser.setScaleY(1.4f);
            eraser.setScaleX(1.4f);
        }else{
            eraser.setScaleY(1f);
            eraser.setScaleX(1f);
        }
    }
}