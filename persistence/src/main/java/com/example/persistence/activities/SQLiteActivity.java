package com.example.persistence.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;

import com.example.persistence.Model.Post;
import com.example.persistence.Model.User;
import com.example.persistence.R;
import com.example.persistence.utils.PostsDatabaseHelper;

import java.util.List;

public class SQLiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        //create an user object
        User user = new User();
        user.setUserName("luo");
        user.setProfilePictureUrl("https://www.baidu.com");
        //create a post object
        Post post = new Post();
        post.setText("hello");
        post.setUser(user);

        PostsDatabaseHelper dbHelper = PostsDatabaseHelper.getInstance(this);
        dbHelper.addOrUpdateUser(user);
        dbHelper.addPost(post);

        List<Post> posts = dbHelper.getAllPosts();
        for (Post p : posts) {
            Log.d("result", p.getText());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sqlite, menu);
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
