package com.example.demo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.R;

import java.util.zip.Inflater;

public class LayoutChangesActivity extends AppCompatActivity {
    private ViewGroup mContainer;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_changes);
        mToolbar = (Toolbar) findViewById(R.id.layout_changes_toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mContainer = (ViewGroup) findViewById(R.id.container);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_layout_changes, menu);
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
            case R.id.lay_changes_add:
                findViewById(android.R.id.empty).setVisibility(View.GONE);
                addItem();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addItem() {
       final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.item_layout_changes,mContainer,false);
        ((TextView)newView.findViewById(android.R.id.text1)).setText("hello");
        newView.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remove the row from its parent (the container view).
                // Because mContainerView has android:animateLayoutChanges set to true,
                // this removal is automatically animated.
                mContainer.removeView(newView);

                // If there are no rows remaining, show the empty view.
                if (mContainer.getChildCount() == 0) {
                    findViewById(android.R.id.empty).setVisibility(View.VISIBLE);
                }
            }
        });
        mContainer.addView(newView);
    }
}
