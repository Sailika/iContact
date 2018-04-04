package com.app.icontacts.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.app.icontacts.R;
import com.app.icontacts.databinding.CreateActivityBinding;
import com.app.icontacts.model.CreateContactsRequest;
import com.app.icontacts.viewmodel.CreateContactsViewModel;

import java.util.Observable;
import java.util.Observer;


public class CreateContactsActivity extends AppCompatActivity implements Observer {

    private static CreateActivityBinding createActivityBinding;
    private CreateContactsViewModel createContactsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setSupportActionBar(createActivityBinding.toolbar);
        createActivityBinding.toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setupObserver(createContactsViewModel);
        displayHomeAsUpEnabled();
    }

    public static CreateContactsRequest getRequest() {
        CreateContactsRequest request = new CreateContactsRequest();
        request.setName(createActivityBinding.nameEdt.getText().toString());
        request.setJob(createActivityBinding.jobEdt.getText().toString());
        return request;
    }

    //initialize the view model and the layout ...
    private void initDataBinding() {
        createActivityBinding = DataBindingUtil.setContentView(this, R.layout.create_activity);
        createContactsViewModel = new CreateContactsViewModel(this);
        createActivityBinding.setCreateActivityViewModel(createContactsViewModel);
        setSupportActionBar(createActivityBinding.toolbar);

    }

    private void displayHomeAsUpEnabled() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        createContactsViewModel.reset();
    }

    //setup observer to get updates for chnaging the ui on service complete....
    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof CreateContactsViewModel) {

            final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setMessage(getString(R.string.completed));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.dismiss();
                            finish();
                        }
                    });
            alertDialog.show();
        }
    }


    public static ProgressDialog getProgress(Context context, String text) {
        ProgressDialog prog = new ProgressDialog(context);
        prog.setTitle(text);
        prog.setCancelable(false);
        prog.setIndeterminate(true);
        prog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        return prog;
    }


}
