package com.example.persistence.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.persistence.Model.Todo;
import com.example.persistence.R;
import com.example.persistence.utils.TodoDatabaseHelper;
import com.example.persistence.views.adapter.TodoCursorAdapter;

public class TodoActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        listView = (ListView) findViewById(R.id.lv_todo);
        TodoDatabaseHelper dbHelper = TodoDatabaseHelper.getInstance(this,1);
        Todo item1 = new Todo();
        item1.setBody("Get milk");
        item1.setPriority(1);
        Todo item2 = new Todo();
        item2.setBody("Do laundry");
        item2.setPriority(2);
        dbHelper.addItem(item1);
        dbHelper.addItem(item2);
        TodoCursorAdapter cursorAdapter = new TodoCursorAdapter(this, dbHelper.queryItem());
        listView.setAdapter(cursorAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo, menu);
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
}
