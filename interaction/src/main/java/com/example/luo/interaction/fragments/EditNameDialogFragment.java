package com.example.luo.interaction.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.luo.interaction.R;

/**
 * Created by H_P on 2016/4/22.
 * @author luo
 * @version 1.0
 */
public class EditNameDialogFragment extends DialogFragment {
    private EditText mEditText;
    public EditNameDialogFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_name,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mEditText = (EditText) view.findViewById(R.id.txt_your_name);
        Bundle args = getArguments();
        mEditText.setText(args.getString("title","enter text"));
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public static EditNameDialogFragment newInstance(String title){
        EditNameDialogFragment fragment = new EditNameDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

}
