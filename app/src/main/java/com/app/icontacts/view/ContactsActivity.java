package com.app.icontacts.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.app.icontacts.R;
import com.app.icontacts.databinding.ContactsViewActivityBinding;
import com.app.icontacts.viewmodel.ContactsViewModel;

import java.util.Observable;
import java.util.Observer;

public class ContactsActivity extends AppCompatActivity implements Observer {

    private ContactsViewActivityBinding contactsViewActivityBinding;
    private ContactsViewModel contactsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setSupportActionBar(contactsViewActivityBinding.toolbar);
        setUpContactListView(contactsViewActivityBinding.contactsList);
        setupObserver(contactsViewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        callService();

    }

    private void callService() {
        contactsViewModel.initializeViews();
        contactsViewModel.fetchContactsList();
    }

    //initialize the view model and the layout ...
    private void initDataBinding() {
        contactsViewActivityBinding = DataBindingUtil.setContentView(this, R.layout.contacts_view_activity);
        contactsViewModel = new ContactsViewModel(this);
        contactsViewActivityBinding.setMainViewModel(contactsViewModel);
    }

    //set Adapter to the List View...
    private void setUpContactListView(RecyclerView contactList) {
        ContactsAdapter adapter = new ContactsAdapter();
        contactList.setAdapter(adapter);
        contactList.setLayoutManager(new LinearLayoutManager(this));
    }

    //setup observer to get updates for chnaging the ui on service complete....
    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contactsViewModel.reset();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //on Refresh Icon selected, send ViewModel request to fetch the data...
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_refresh) {
            callService();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //After Service is completed, Update the UI...
    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof ContactsViewModel) {
            ContactsAdapter contactsAdapter = (ContactsAdapter) contactsViewActivityBinding.contactsList.getAdapter();
            ContactsViewModel contactsViewModel = (ContactsViewModel) observable;
            contactsAdapter.setContactsList(contactsViewModel.getContactsList());
            contactsAdapter.notifyDataSetChanged();
        }
    }
}
