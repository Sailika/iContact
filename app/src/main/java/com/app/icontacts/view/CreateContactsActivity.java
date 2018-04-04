package com.app.icontacts.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.app.icontacts.R;
import com.app.icontacts.databinding.CreateActivityBinding;


public class CreateContactsActivity extends AppCompatActivity {


    private CreateActivityBinding createActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.create_activity);
        setSupportActionBar(createActivityBinding.toolbar);
        displayHomeAsUpEnabled();
    }


    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


}
