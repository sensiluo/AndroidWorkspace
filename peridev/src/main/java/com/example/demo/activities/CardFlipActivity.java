package com.example.demo.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo.R;

public class CardFlipActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {
    private static final String TAG = "CardFlipActivity";
    private boolean mShowingBack = false;
    private Toolbar mToolbar;
    private static final int RESOURCE_ID = 1;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_flip);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
//        toolbar.setNavigationIcon(R.drawable.ic_launcher);
        setSupportActionBar(mToolbar);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.card_flip_container, new CardFrontFragment())
                    .commit();
            Log.d(TAG,"onCreate...");
        } else
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        getFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_card_flip, menu);
        MenuItem menuItem = menu.add(Menu.NONE, RESOURCE_ID, Menu.NONE, mShowingBack ? R.string.action_photo : R.string.action_info);
        menuItem.setIcon(mShowingBack ? R.drawable.ic_action_photo : R.drawable.ic_action_info);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        Log.d(TAG, "onCreateOptionsMenu...");
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
            case RESOURCE_ID:
                flip();
                Log.d(TAG, "onOptionsItemSelected...");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackStackChanged() {
        mShowingBack = (getFragmentManager().getBackStackEntryCount()>0);
        invalidateOptionsMenu();
        Log.d(TAG, "onBackStackChanged...");
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG,getFragmentManager().getBackStackEntryCount()+"");
        super.onBackPressed();
    }

    private void flip() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            return;
        }
//        mShowingBack = true;
        getFragmentManager().beginTransaction().setCustomAnimations(R.animator.card_flip_right_in, R.animator.card_flip_right_out, R.animator.card_flip_left_in, R.animator.card_left_out)
                .replace(R.id.card_flip_container, new CardBackFragment())
                .addToBackStack(null)
                .commit();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                invalidateOptionsMenu();
            }
        });

    }



    public static class CardFrontFragment extends Fragment {
        public CardFrontFragment() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreateView(inflater,container,savedInstanceState);
            Log.d(TAG,"onCreateView...");
            return inflater.inflate(R.layout.fragment_card_front, container, false);
        }
    }

    public static class CardBackFragment extends Fragment {
        public CardBackFragment() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_card_back, container, false);
        }

        @Override
        public void onResume() {
            super.onResume();
        }
    }
}
