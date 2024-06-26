package com.example.physics;

import static com.example.physics.MainActivity.path;
import static com.example.physics.MainActivity.pencil_brush;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Screen extends View {
    public static ArrayList<Path> path_list = new ArrayList<Path>();
    public static ArrayList<Integer> color_list = new ArrayList<Integer>();
    public static ArrayList<Float> width_list = new ArrayList<Float>();
    public ViewGroup.LayoutParams layoutParams;
    public static int color_brush = Color.BLACK;
    public static float width_brush = 10f;
    private float x, y; // Position of the ball
    private float vx, vy; // Velocity of the ball
    private float ax, ay; // Acceleration of the ball
    private float dt = 1f; // Time step (adjust as needed)
    public Screen(Context context) {
        super(context);
        init(context);
    }

    public Screen(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Screen(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        x = getWidth()/2;//
        y = getHeight()/2;//
        vx = 0;
        vy = 0;
        ax = 0.1f;
        ay = 0.1f;
        pencil_brush.setAntiAlias(true);
        pencil_brush.setColor(color_brush);
        pencil_brush.setStyle(Paint.Style.STROKE);
        pencil_brush.setStrokeCap(Paint.Cap.ROUND);
        pencil_brush.setStrokeJoin(Paint.Join.ROUND);
        pencil_brush.setStrokeWidth(width_brush);
        layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                if(!path_list.contains(path)){
                    path_list.add(path);
                    color_list.add(color_brush);
                    width_list.add(width_brush);
                    invalidate();
                }
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0; i<path_list.size(); i++){
            pencil_brush.setColor(color_list.get(i));
            pencil_brush.setStrokeWidth(width_list.get(i));
            canvas.drawPath(path_list.get(i), pencil_brush);
            fisica(canvas);
            invalidate();
        }

    }
    public void fisica(Canvas canvas){
        // Update position based on velocity
        x += vx * dt;
        y += vy * dt;

        // Update velocity based on acceleration
        vx += ax * dt;
        vy += ay * dt;

        // Handle collisions with walls
        if (x < 0 || x > getWidth()) {
            vx *= -1; // Reverse velocity
        }
        if (y < 0 || y > getHeight()) {
            vy *= -1; // Reverse velocity
        }

        // Draw the ball at its current position
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(x, y, 50, paint);

        // Invalidate the View to trigger redraw
        invalidate();
    }
}
