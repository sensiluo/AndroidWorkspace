package com.example.demo.activities;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TextView userName = (TextView) findViewById(R.id.text_custom_font);
//        Typeface alexBrush = Typeface.createFromAsset(this.getAssets(), "AlexBrush-Regular.ttf");
//        userName.setTypeface(alexBrush);
        Button button = (Button) findViewById(R.id.button);
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(button, "Y", 1000);
//        objectAnimator.setDuration(2000);
//        objectAnimator.setInterpolator(new AccelerateInterpolator());
//        objectAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                Toast.makeText(MainActivity.this,"END",Toast.LENGTH_LONG).show();
//            }
//        });
//        objectAnimator.start();
//        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.fade_out);
//        animator.setTarget(button);
//        animator.start();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CardLayoutActivity.class));
                overridePendingTransition(R.animator.right_in, R.animator.left_out);
                Toast.makeText(MainActivity.this, "hi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
