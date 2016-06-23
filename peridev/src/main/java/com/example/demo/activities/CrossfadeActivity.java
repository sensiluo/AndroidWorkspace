package com.example.demo.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.demo.R;

public class CrossfadeActivity extends AppCompatActivity {
    private static final String TAG = "CrossfadeActivity";
    private Toolbar mToolbar;
    private View mContentView;
    private View mLoadingView;
    private boolean mContentLoaded;
    private int mShortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crossfade);
        mToolbar = (Toolbar) findViewById(R.id.cross_fade_toolbar);
        mToolbar.setTitle("");
        mContentView = findViewById(R.id.content);
        mLoadingView = findViewById(R.id.loading_spinner);
        mContentView.setVisibility(View.GONE);
        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_crossfade, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
            case R.id.action_ok:
                mContentLoaded = !mContentLoaded;
                showContentOrLoadingIndicator(mContentLoaded);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * @param contentLoaded set different animation for differnt views
     */
    private void showContentOrLoadingIndicator(boolean contentLoaded) {
        final View showView = mContentLoaded ? mContentView : mLoadingView;
        final View hideView = mContentLoaded ? mLoadingView : mContentView;
        showView.setAlpha(0f);
        showView.setVisibility(View.VISIBLE);

        showView.animate().alpha(1f).setDuration(mShortAnimationDuration).setListener(null);
        hideView.animate().alpha(0f).setDuration(mShortAnimationDuration).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                hideView.setVisibility(View.GONE);
            }
        });

    }
}
