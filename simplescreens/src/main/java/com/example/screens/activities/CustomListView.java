package com.example.screens.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.screens.R;
import com.example.screens.models.User;
import com.example.screens.views.adapters.CustomAdapterViewHolder1;
import com.example.screens.views.adapters.CustomBaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomListView extends AppCompatActivity {
    //    private ArrayAdapter<User> mArrayAdapter;
    private BaseAdapter mArrayAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);
        mListView = (ListView) findViewById(R.id.listview_custom);
        mArrayAdapter = new CustomBaseAdapter(this, init());
        mListView.setAdapter(mArrayAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom_list_view, menu);
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

    private List<User> init() {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 100; i++) {
            list.add(new User("用户" + i, i, i));
        }
        return list;
    }
}
