package com.example.demo.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.demo.R;

/**
 * Created by H_P on 2016/4/12.
 *
 * @author luo
 * @version 1.0
 *          a normal custom view
 */
public class ShapeSelectorView extends View {
    private int mShapeColor;
    private boolean mDisplayShapeName;
    private int mShapeWidth;
    private int mShapeHeight;
    private int mTextXOffset;
    private int mTextYOffset;
    private Paint mPaintShape;

    public ShapeSelectorView(Context context) {
        super(context);
    }

    public ShapeSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupAttributes(attrs);
        setupPaint();
    }

    public ShapeSelectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupAttributes(attrs);
        setupPaint();
    }

//    public ShapeSelectorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,mShapeWidth,mShapeHeight,mPaintShape);
        if(mDisplayShapeName){
            canvas.drawText("Square",mShapeWidth+mTextXOffset,mShapeHeight+mTextYOffset,mPaintShape);
        }
    }

    public void setmShapeColor(int mShapeColor) {
        this.mShapeColor = mShapeColor;
        invalidate();
        requestLayout();
    }

    public void setmDisplayShapeName(boolean mDisplayShapeName) {
        this.mDisplayShapeName = mDisplayShapeName;
        invalidate();
        requestLayout();
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray t = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ShapeSelectorView, 0, 0);
        if (t != null) {
            this.mShapeColor = t.getColor(R.styleable.ShapeSelectorView_shapeColor, Color.BLACK);
            this.mDisplayShapeName = t.getBoolean(R.styleable.ShapeSelectorView_displayShapeName, false);
        }
        t.recycle();
    }

    private void setupPaint(){
        this.mPaintShape = new Paint();
        this.mPaintShape.setStyle(Paint.Style.FILL);
        this.mPaintShape.setColor(mShapeColor);
        this.mPaintShape.setTextSize(30);
    }
}
