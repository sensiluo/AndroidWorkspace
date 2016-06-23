package com.example.luo.interaction.activities;

import android.os.Bundle;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;

import com.example.luo.interaction.R;

/**
 * Created by H_P on 2016/4/20.
 * @author luo
 * @version 1.0
 */
public class VelocityTrackerActivity extends AppCompatActivity{
    private static final String DEBUG_TAG = "Velocity";
    private VelocityTracker mVeloctiyTracker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive_chart);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerId = event.getPointerId(index);
        switch (action){
            case MotionEvent.ACTION_DOWN:
                if(mVeloctiyTracker==null){
                    mVeloctiyTracker = VelocityTracker.obtain();
                }
                else{
                    mVeloctiyTracker.clear();
                }
                mVeloctiyTracker.addMovement(event);
                return true;
            case MotionEvent.ACTION_MOVE:
                mVeloctiyTracker.addMovement(event);
                mVeloctiyTracker.computeCurrentVelocity(1000);
                Log.d(DEBUG_TAG, "X velocity->" + VelocityTrackerCompat.getXVelocity(mVeloctiyTracker,pointerId));
                Log.d(DEBUG_TAG,"Y velocity->"+ VelocityTrackerCompat.getYVelocity(mVeloctiyTracker,pointerId));
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mVeloctiyTracker.recycle();
                mVeloctiyTracker=null;
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
