package com.example.service.activities;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.service.R;
import com.example.service.services.MyTestReceiver;
import com.example.service.services.MyTestService;

public class MainActivity extends AppCompatActivity {
    private MyTestReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setUpReceiver();
//        Intent intent = new Intent(MainActivity.this, MyTestService.class);
//        intent.putExtra("foo", "bar");
//        intent.putExtra("receiver", receiver);
//        startService(intent);
        MyTestService.startActionFoo(this, "foo", "bar");
        createNotification(1, R.mipmap.ic_launcher, "WeChat", "There are some message for you");
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

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("com.example.service.services.action.FOO");
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            Toast.makeText(context, bundle.getString("result") + "||" + bundle.getString("value"), Toast.LENGTH_SHORT).show();
        }
    };

    private void setUpReceiver() {
        receiver = new MyTestReceiver(new Handler());
        receiver.setReceiver(new MyTestReceiver.Receiver() {
            @Override
            public void onReceiveResult(int resultCode, Bundle resultData) {
                if (RESULT_OK == resultCode)
                    Toast.makeText(MainActivity.this, resultData.getString("resultValue", "No Data Passed"), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createNotification(int id, int iconRes, String title, String body) {
        Notification.Builder builder = new Notification.Builder(this).setSmallIcon(iconRes)
                .setContentTitle(title).setContentText(body);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, builder.build());
    }
}
