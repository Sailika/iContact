package com.app.icontacts;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.app.icontacts.data.ContactsFactory;
import com.app.icontacts.data.ContactsService;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by saili on 4/3/2018.
 */

public class ContactsApplication extends MultiDexApplication {

    private ContactsService contactsService;
    private Scheduler scheduler;

    private static ContactsApplication get(Context context) {
        return (ContactsApplication) context.getApplicationContext();
    }

    public static ContactsApplication create(Context context) {
        return ContactsApplication.get(context);
    }

    public ContactsService getContactsService() {
        if (contactsService == null) {
            contactsService = ContactsFactory.create();
        }

        return contactsService;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setContactsService(ContactsService service) {
        this.contactsService = service;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
