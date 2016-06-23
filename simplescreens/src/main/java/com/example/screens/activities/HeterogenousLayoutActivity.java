package com.example.screens.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.screens.R;
import com.example.screens.models.User;
import com.example.screens.views.adapters.viewgroups.HeterogenousRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HeterogenousLayoutActivity extends AppCompatActivity {
    private List<Object> mItems;
    private RecyclerView mRecyclerView;
    private HeterogenousRecyclerAdapter mHeterogenousRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        dataInit();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mHeterogenousRecyclerAdapter = new HeterogenousRecyclerAdapter(mItems);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mHeterogenousRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_heterogenous_layout, menu);
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

    private void dataInit() {
        this.mItems = new ArrayList<Object>();
        for (int i = 0; i < 10; i++) {
            if (i < 5)
                mItems.add(new User("USER" + i, i, i));
            else
                mItems.add("IMAGE" + i);
        }
    }
}
