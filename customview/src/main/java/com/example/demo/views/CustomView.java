package com.example.demo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H_P on 2016/4/11.
 *
 * @author luo
 * @version 1.0
 */
public class CustomView extends View {
    private final int mPaintColor = Color.BLACK;
    private Paint mDrawPaint;
//    private List<Point> mCirclePoints;
    private Path mPath = new Path();
    public CustomView(Context context) {
        super(context);
    }

//    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
//        this.mCirclePoints = new ArrayList<Point>();
    }

//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.drawCircle(50, 50, 20, mDrawPaint);
//        mDrawPaint.setColor(Color.GREEN);
//        canvas.drawCircle(50, 150, 30, mDrawPaint);
//        mDrawPaint.setColor(Color.YELLOW);
//        canvas.drawCircle(50, 250, 40, mDrawPaint);
//    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        for(Point p:mCirclePoints){
//            canvas.drawCircle(p.x,p.y,5,mDrawPaint);
//        }
        canvas.drawPath(mPath,mDrawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
//        mCirclePoints.add(new Point(Math.round(touchX), Math.round(touchY)));
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(touchX,touchY);
                break;
            default:return false;
        }
        postInvalidate();
        return true;
    }

    private void setupPaint() {
        this.mDrawPaint = new Paint();
        this.mDrawPaint.setColor(mPaintColor);
        this.mDrawPaint.setAntiAlias(true);
        this.mDrawPaint.setStrokeWidth(5);
        this.mDrawPaint.setStyle(Paint.Style.STROKE);
        this.mDrawPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mDrawPaint.setStrokeCap(Paint.Cap.ROUND);
    }
}
