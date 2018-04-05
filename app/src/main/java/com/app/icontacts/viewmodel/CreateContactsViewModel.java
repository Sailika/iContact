package com.app.icontacts.viewmodel;


import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import com.app.icontacts.Constants;
import com.app.icontacts.ContactsApplication;
import com.app.icontacts.NetworkUtil;
import com.app.icontacts.R;
import com.app.icontacts.data.ContactsService;
import com.app.icontacts.model.CreateContactsRequest;
import com.app.icontacts.model.CreateContactsResponse;
import com.app.icontacts.view.CreateContactsActivity;

import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class CreateContactsViewModel extends Observable {

    private Context mContext;

    public ObservableField<String> mNameEdt;
    public ObservableField<String> mJobEdt;
    public ObservableField<String> mAddBtn;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    //Constructor..
    public CreateContactsViewModel(Context context) {
        this.mContext = context;
        mNameEdt = new ObservableField<String>();
        mJobEdt = new ObservableField<String>();
        mAddBtn = new ObservableField<String>();

    }

    public void createUser(View view) {
        if (NetworkUtil.isNetworkAvailable(view.getContext())) {
            startService();
        } else {
            Toast.makeText(view.getContext(), view.getContext().getResources().getString(R.string.no_network),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void startService() {
        ContactsApplication contactsApplication = ContactsApplication.create(mContext);
        ContactsService contactService = contactsApplication.getContactsService();
        CreateContactsRequest request = CreateContactsActivity.getRequest();

        if (request.getName() != null && request.getName().length() != 0 && request.getJob().length() != 0 && request.getJob() != null) {
            final ProgressDialog dialog = CreateContactsActivity.getProgress(mContext, mContext.getString(R.string.pleaseWait));
            dialog.show();

            Disposable disposable = contactService.pushContacts(Constants.CREATE_USER_URL, request)
                    .subscribeOn(contactsApplication.subscribeScheduler())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<CreateContactsResponse>() {
                        @Override
                        public void accept(CreateContactsResponse contactResponse) throws Exception {
                            dialog.dismiss();
                            notifyActivity();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Toast.makeText(mContext, mContext.getResources().getString(R.string.create_user_error), Toast.LENGTH_LONG).show();

                        }
                    });

            compositeDisposable.add(disposable);
        } else {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.edittext_null), Toast.LENGTH_LONG).show();
        }
    }

    private void notifyActivity() {
        setChanged();
        notifyObservers();
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable = null;
        mContext = null;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

}
