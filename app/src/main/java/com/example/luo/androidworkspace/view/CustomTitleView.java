package com.example.luo.androidworkspace.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.example.luo.androidworkspace.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by H_P on 2016/3/28.
 *
 * @author luo
 *         custom a title view
 * @version 1.0
 */
public class CustomTitleView extends View {
    private String mTitleText;
    private int mTitleTextColor;
    private int mTitleTextSize;
    private Rect mBound;
    private Paint mPaint;

    public CustomTitleView(Context context) {
        this(context, null);
    }

    public CustomTitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleText = randomText();
                postInvalidate();
            }
        });
        TypedArray attr = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomTitleView, defStyleAttr, 0);
        if (attr != null) {
            int n = attr.getIndexCount();
            for (int i = 0; i < n; i++) {
                int attribute = attr.getIndex(i);
                switch (attribute) {
                    case R.styleable.CustomTitleView_titleText:
                        mTitleText = attr.getString(attribute);
                        break;
                    case R.styleable.CustomTitleView_titleTextColor_:
                        mTitleTextColor = attr.getColor(attribute, Color.BLUE);
                        break;
                    case R.styleable.CustomTitleView_titleTextSize:
                        mTitleTextSize = attr.getDimensionPixelSize(attribute, (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                        break;
                    default:
                        break;
                }
            }
        }
        attr.recycle();
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);


    }

//    public CustomTitleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//
//    }


    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        Log.d("size", "width" + getMeasuredWidth() + "height" + getMeasuredHeight());
        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
        Log.d("size", "width" + getWidth() + "height" + getHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = getPaddingLeft() + mBound.width() + getPaddingRight();
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = getPaddingTop() + mBound.height() + getPaddingBottom();
        }
        setMeasuredDimension(width, height);
    }

    public String randomText() {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4) {
            set.add(random.nextInt(10));
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set) {
            sb.append("" + i);
        }
        return sb.toString();
    }
}