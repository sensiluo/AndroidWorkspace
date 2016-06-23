package com.example.luo.interaction.activities;

import android.annotation.SuppressLint;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;

import com.example.luo.interaction.R;

public class InteractiveChartActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener{
    private static final String DEBUG_TAG="InteractiveChartActivity";
    private GestureDetectorCompat mGestureDetector;
    private VelocityTracker mVelocityTracker = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive_chart);
        mGestureDetector = new GestureDetectorCompat(this,this);
        mGestureDetector.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_interactive_chart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("LongLogTag")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

//        int action = MotionEventCompat.getActionMasked(event);
//        switch (action){
//            case MotionEvent.ACTION_DOWN:
//                Log.d(DEBUG_TAG,"action -> down");
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                Log.d(DEBUG_TAG,"action -> move");
//                return true;
//            case MotionEvent.ACTION_UP:
//                Log.d(DEBUG_TAG,"action -> up");
//                return true;
//            case MotionEvent.ACTION_CANCEL:
//                Log.d(DEBUG_TAG,"action -> cancel");
//                return true;
//            case MotionEvent.ACTION_OUTSIDE:
//                Log.d(DEBUG_TAG,"action -> outside of screen");
//                return true;
//            default:
//                return super.onTouchEvent(event);
//        }
        this.mGestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);

    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {

        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
