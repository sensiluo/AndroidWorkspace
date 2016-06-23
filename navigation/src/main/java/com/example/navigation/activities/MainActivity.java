package com.example.navigation.activities;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.navigation.R;
import com.example.navigation.model.Book;
import com.example.navigation.network.BookClient;
import com.example.navigation.views.adapter.BookAdapter;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    public static final String BOOK_DETAIL_TAG="book";
    private ListView listView;
    private BookClient bookClient;
    private BookAdapter bookAdapter;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lv_books);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        ArrayList<Book> books = new ArrayList<Book>();
        bookAdapter = new BookAdapter(this,books);
        fetchBooks();
        listView.setAdapter(bookAdapter);
        setupItemSelectedListener();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem menuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchBooks(query);
                searchView.clearFocus();
                searchView.setQuery("", false);
                searchView.setIconified(true);
                menuItem.collapseActionView();
                MainActivity.this.setTitle(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    private void fetchBooks(String query){
        progressBar.setVisibility(ProgressBar.VISIBLE);
        bookClient = new BookClient();
        bookClient.getBooks(query,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try{
                    progressBar.setVisibility(ProgressBar.GONE);
                    JSONArray docs = null;
                    if(response !=null){
                        docs = response.getJSONArray("docs");
                        final ArrayList<Book> books = Book.fromJson(docs);
                        bookAdapter.clear();
                        for(Book book:books){
                            bookAdapter.add(book);
                        }
                        bookAdapter.notifyDataSetChanged();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                progressBar.setVisibility(ProgressBar.GONE);
            }
        });
    }

    private void fetchBooks(){
        progressBar.setVisibility(ProgressBar.VISIBLE);
        bookClient = new BookClient();
        bookClient.getBooks("oscar Wilde",new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try{
                    progressBar.setVisibility(ProgressBar.GONE);
                    JSONArray docs = null;
                    if(response !=null){
                        docs = response.getJSONArray("docs");
                        final ArrayList<Book> books = Book.fromJson(docs);
                        bookAdapter.clear();
                        for(Book book:books){
                            bookAdapter.add(book);
                        }
                        bookAdapter.notifyDataSetChanged();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                progressBar.setVisibility(ProgressBar.GONE);
            }
        });
    }

    private void setupItemSelectedListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,BookDetailActivity.class);
                intent.putExtra(BOOK_DETAIL_TAG,bookAdapter.getItem(position));
                startActivity(intent);
            }
        });
    }
}
