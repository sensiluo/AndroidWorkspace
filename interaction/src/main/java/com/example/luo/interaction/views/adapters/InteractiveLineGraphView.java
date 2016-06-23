package com.example.luo.interaction.views.adapters;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.OverScroller;

/**
 * Created by H_P on 2016/4/21.
 * @author luo
 * @version 1.0
 */
public class InteractiveLineGraphView extends View{
    private static final float AXIS_X_MIN=-1f;
    private static final float AXIS_X_MAX=1f;
    private static final float AXIS_Y_MIN=-1f;
    private static final float AXIS_Y_MAX=1f;
    // current view
    private RectF mCurrentViewport = new RectF(AXIS_X_MIN,AXIS_Y_MIN,AXIS_X_MAX,AXIS_Y_MAX);
    // the place that the view is drawn at
    private Rect mContentRect;
    private OverScroller mOverScroller;
    private RectF mScrollerStartViewport;

    public InteractiveLineGraphView(Context context) {
        super(context);
    }

    public InteractiveLineGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InteractiveLineGraphView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public InteractiveLineGraphView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
}
