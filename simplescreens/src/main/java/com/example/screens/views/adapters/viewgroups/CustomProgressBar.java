package com.example.screens.views.adapters.viewgroups;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.example.screens.R;

/**
 * Created by H_P on 2016/4/13.
 *
 * @author luo
 * @version 1.0
 */
public class CustomProgressBar extends View {
    private Paint mProgressPaint;
    private int mGoal;
    private int mProgress;
    private ValueAnimator mValueAnimator;
    private float mGoalIndicatorHeight;
    private float mGoalIndicatorThickness;
    private int mGoalReachedColor;
    private int mGoalNotReachedColor;
    private int mUnfilledSectionColor;
    private IndicatorType mIndicatorType;
    private int mBarThickness;

    public enum IndicatorType {
        line, circle, square
    }

    public CustomProgressBar(Context context) {
        super(context);
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(AttributeSet attrs) {
        mProgressPaint = new Paint();
        mProgressPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        TypedArray t = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.CustomProgressBar, 0, 0);
        if (t != null) {
            setmGoalIndicatorHeight(t.getDimensionPixelSize(R.styleable.CustomProgressBar_goalIndicatorHeight, 10));
            setmGoalIndicatorThickness(t.getDimensionPixelSize(R.styleable.CustomProgressBar_goalIndicatorThickness, 5));
            setmGoalReachedColor(t.getColor(R.styleable.CustomProgressBar_goalReachedColor, Color.RED));
            setmGoalNotReachedColor(t.getColor(R.styleable.CustomProgressBar_goalNotReachedColor, Color.BLUE));
            setmUnfilledSectionColor(t.getColor(R.styleable.CustomProgressBar_unfilledSectionColor, Color.BLACK));
            int index = t.getInt(R.styleable.CustomProgressBar_indicatorType, 0);
            setmIndicatorType(IndicatorType.values()[index]);
            setmBarThickness(t.getDimensionPixelOffset(R.styleable.CustomProgressBar_barThickness, 4));
        }
        t.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int specHeight = MeasureSpec.getSize(heightMeasureSpec);
        int height;
        switch (MeasureSpec.getMode(heightMeasureSpec)) {
            case MeasureSpec.EXACTLY:
                height = specHeight;
                break;
            case MeasureSpec.AT_MOST:
                height = Math.min((int) mGoalIndicatorHeight, specHeight);
                break;
            case MeasureSpec.UNSPECIFIED:
            default:
                height = specHeight;
                break;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int halfHeight = getHeight() / 2;
        int progressEndX = (int) (getWidth() * mProgress / 100f);
        //draw the filled portion of the bar
        mProgressPaint.setStrokeWidth(mBarThickness);
        int color = (mProgress > mGoal) ? mGoalReachedColor : mGoalNotReachedColor;
        mProgressPaint.setColor(color);
        canvas.drawLine(0, halfHeight, progressEndX, halfHeight, mProgressPaint);
        //draw the unfilled portion of the bar
        mProgressPaint.setColor(mUnfilledSectionColor);
        canvas.drawLine(progressEndX, halfHeight, getWidth(), halfHeight, mProgressPaint);
        //draw the goal indicator
        int indicatorPosition = (int) (getWidth() * mGoal / 100f);
        mProgressPaint.setStrokeWidth(mGoalIndicatorThickness);
        mProgressPaint.setColor(mGoalReachedColor);
        switch (mIndicatorType) {
            case line:
                canvas.drawLine(indicatorPosition, halfHeight - mGoalIndicatorHeight / 2, indicatorPosition, halfHeight + mGoalIndicatorHeight / 2, mProgressPaint);
                break;
            case square:
                canvas.drawRect(
                        indicatorPosition - (mGoalIndicatorHeight / 2),
                        0,
                        indicatorPosition + (mGoalIndicatorHeight / 2),
                        mGoalIndicatorHeight,
                        mProgressPaint);
                break;
            case circle:
                canvas.drawCircle(indicatorPosition, halfHeight, halfHeight, mProgressPaint);
                break;
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putInt("progress",mProgress);
        bundle.putInt("goal",mGoal);
        bundle.putParcelable("superState",super.onSaveInstanceState());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if(state instanceof Bundle){
            Bundle bundle = (Bundle) state;
            mProgress = bundle.getInt("progress");
            mGoal= bundle.getInt("goal");
            state = bundle.getParcelable("superState");

        }
        super.onRestoreInstanceState(state);
    }

    public void setmGoal(int mGoal) {
        this.mGoal = mGoal;
        postInvalidate();
    }

    public void setmProgress(int mProgress) {
        setmProgress(mProgress,true);
    }

    public void setmProgress(final int mProgress,boolean animate){
        if(animate){
            mValueAnimator=ValueAnimator.ofFloat(0,1);
            mValueAnimator.setDuration(700);
            setmProgress(0, false);
            mValueAnimator.setInterpolator(new DecelerateInterpolator());
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float interPolation = (float) animation.getAnimatedValue();
                    setmProgress((int) (interPolation*mProgress),false);
                }
            });
            if(!mValueAnimator.isStarted()){
                mValueAnimator.start();
            }
        }
        else
        {
            this.mProgress = mProgress;
            postInvalidate();
        }
    }

    public void setmGoalIndicatorHeight(float mGoalIndicatorHeight) {
        this.mGoalIndicatorHeight = mGoalIndicatorHeight;
        postInvalidate();
    }

    public void setmGoalIndicatorThickness(float mGoalIndicatorThickness) {
        this.mGoalIndicatorThickness = mGoalIndicatorThickness;
        postInvalidate();
    }

    public void setmGoalReachedColor(int mGoalReachedColor) {
        this.mGoalReachedColor = mGoalReachedColor;
        postInvalidate();
    }

    public void setmGoalNotReachedColor(int mGoalNotReachedColor) {
        this.mGoalNotReachedColor = mGoalNotReachedColor;
        postInvalidate();
    }

    public void setmUnfilledSectionColor(int mUnfilledSectionColor) {
        this.mUnfilledSectionColor = mUnfilledSectionColor;
        postInvalidate();
    }

    public void setmBarThickness(int mBarThickness) {
        this.mBarThickness = mBarThickness;
        postInvalidate();
    }

    public void setmIndicatorType(IndicatorType mIndicatorType) {
        this.mIndicatorType = mIndicatorType;
        postInvalidate();
    }
}
