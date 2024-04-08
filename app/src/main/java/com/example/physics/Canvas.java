package com.example.physics;

import static com.example.physics.MainActivity.path;
import static com.example.physics.MainActivity.pencil_brush;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Canvas extends View {
    public static ArrayList<Path> path_list = new ArrayList<Path>();
    public static ArrayList<Integer> color = new ArrayList<Integer>();
    public ViewGroup.LayoutParams layoutParams;
    public static int color_brush = Color.BLACK;
    public static float largura = 10f;
    public Canvas(Context context) {
        super(context);
        init(context);
    }

    public Canvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Canvas(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        pencil_brush.setAntiAlias(true);
        pencil_brush.setColor(color_brush);
        pencil_brush.setStyle(Paint.Style.STROKE);
        pencil_brush.setStrokeCap(Paint.Cap.ROUND);
        pencil_brush.setStrokeJoin(Paint.Join.ROUND);
        pencil_brush.setStrokeWidth(largura);
        layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);//(x, wrapcontent)
    }

    public boolean onTouchEvent(@NonNull MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                path.lineTo(x, y);
                path_list.add(path);
                color.add(color_brush);
                invalidate();
                return true;
            default:
                return false;
        }
    }

    protected void onDraw(Canvas canvas){
        for(int i = 0; i< path_list.size(); i++){
            pencil_brush.setColor(color.get(i));
            canvas;
        }
    }
}
