package com.app.icontacts.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import com.app.icontacts.Constants;
import com.app.icontacts.ContactsApplication;
import com.app.icontacts.R;
import com.app.icontacts.data.ContactsService;
import com.app.icontacts.model.Contacts;
import com.app.icontacts.model.Data;

import java.util.ArrayList;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class ContactsViewModel extends Observable {

    public ObservableInt mProgress;
    public ObservableInt mRecycler;
    public ObservableInt mLabel;
    public ObservableField<String> messageLabel;

    private ArrayList<Data> contactsList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ContactsViewModel(@NonNull Context context) {

        this.context = context;
        this.contactsList = new ArrayList<>();
        mProgress = new ObservableInt(View.GONE);
        mRecycler = new ObservableInt(View.GONE);
        mLabel = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>(context.getString(R.string.loading_contacts));
    }

    public void onClickFabLoad(View view) {
        initializeViews();
        fetchStoresList();
    }

    public void initializeViews() {
        mLabel.set(View.GONE);
        mRecycler.set(View.GONE);
        mProgress.set(View.VISIBLE);
    }

    public void fetchStoresList() {
        ContactsApplication contactsApplication = ContactsApplication.create(context);
        ContactsService contactService = contactsApplication.getContactsService();

        Disposable disposable = contactService.fetchContacts(Constants.GET_USER_URL)
                .subscribeOn(contactsApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Contacts>() {
                    @Override
                    public void accept(Contacts contactResponse) throws Exception {
                        changeStoresDataSet(contactResponse.getData());
                        mProgress.set(View.GONE);
                        mLabel.set(View.GONE);
                        mRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        messageLabel.set(context.getString(R.string.error_loading_contacts));
                        mProgress.set(View.GONE);
                        mLabel.set(View.VISIBLE);
                        mRecycler.set(View.GONE);
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void changeStoresDataSet(ArrayList<Data> contacts) {
        contactsList.addAll(contacts);
        setChanged();
        notifyObservers();
    }

    public ArrayList<Data> getContactsList() {
        return contactsList;
    }


    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        context = null;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
