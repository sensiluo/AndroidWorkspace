package com.example.luo.interaction.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luo.interaction.R;

/**
 * Created by H_P on 2016/4/21.
 * @author luo
 * @version 1.0
 */
public class ActionModeActivity extends AppCompatActivity{
    private TextView mTextView;
    private ActionMode mCurrentMode = null;
    private ActionMode.Callback mCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Actions");
            mode.getMenuInflater().inflate(R.menu.menu_action_mode,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.menu_delete:
                    mode.finish();
                    return true;
                case R.id.menu_edit:
                    Toast.makeText(ActionModeActivity.this,"Edit..",Toast.LENGTH_SHORT).show();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
                mCurrentMode=null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive_chart);
        mTextView = (TextView) findViewById(R.id.text_view_interactive);
        mTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mCurrentMode!=null){
                    return false;
                }
                mCurrentMode = startActionMode(mCallback);
                v.setSelected(true);
                return true;
            }
        });
    }
}
