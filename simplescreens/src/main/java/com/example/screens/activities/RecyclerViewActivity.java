package com.example.screens.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.screens.R;
import com.example.screens.models.User;
import com.example.screens.views.adapters.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<User> mUsers;
    private RecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        dataInit();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerAdapter = new RecyclerAdapter(mUsers);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerAdapter.setListener(new RecyclerAdapter.OnSelfDefineClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                User user = mUsers.get(position);
                Toast.makeText(RecyclerViewActivity.this, user.getmUserName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recycler_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_add:
                mUsers.add(1, new User("JACK LUO", 18, 18));
                mRecyclerAdapter.notifyItemInserted(1);
                mRecyclerView.scrollToPosition(1);
                return true;
        }
        return true;
    }

    private void dataInit() {
        mUsers = new ArrayList<User>();
        for (int i = 0; i < 20; i++) {
            mUsers.add(new User("USER" + i, i, i));
        }

    }
}
