package com.example.demo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.demo.R;

import java.lang.reflect.Array;

public class CardLayoutActivity extends AppCompatActivity {
    private static final String TAG = "CardLayoutActivity";
    private ListView mListView;
    private ArrayAdapter<String> mArrayAdapter;
    private String[] mdata = {"ONE \n", "TWO \n", "THREE \n", "FOUR \n", "FIVE \n", "SIX \n"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_layout);
        mListView = (ListView) findViewById(R.id.list_view);
        mArrayAdapter = new ArrayAdapter<String>(this, R.layout.item_card_background, mdata);
        mListView.setAdapter(mArrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_card_layout, menu);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.animator.left_in, R.animator.right_out);
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
    }
}
