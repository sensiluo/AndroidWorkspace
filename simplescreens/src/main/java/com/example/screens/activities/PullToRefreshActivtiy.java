package com.example.screens.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.screens.R;

import java.util.ArrayList;
import java.util.List;

public class PullToRefreshActivtiy extends AppCompatActivity {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListview;
    private ArrayAdapter<String> mArrayAdapter;
    private List<String> mInitData = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh_activtiy);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        mListview = (ListView) findViewById(R.id.listview_pull_to_refresh);
        init();
        mArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mInitData);
        mListview.setAdapter(mArrayAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mArrayAdapter.clear();
//                mArrayAdapter.notifyDataSetChanged();
                for(int i=0;i<10;i++){

                    mInitData.add(i+"--");
                }
                mArrayAdapter.addAll(mInitData);
//                mArrayAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pull_to_refresh_activtiy, menu);
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

    private void init(){
        for(int i=0;i<100;i++){
            mInitData.add("USER----->"+i);
    }
}}
