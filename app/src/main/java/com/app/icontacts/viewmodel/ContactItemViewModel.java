package com.app.icontacts.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.app.icontacts.model.Data;
import com.squareup.picasso.Picasso;


public class ContactItemViewModel extends BaseObservable {

    private Data contact;
    private Context context;

    public ContactItemViewModel(Data contact, Context context) {
        this.contact = contact;
        this.context = context;
    }

    public String getName() {
        return contact.getFirstName() + " " + contact.getLastName();
    }


    public String getPictureProfile() {
        return contact.getAvatar();
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {

        Picasso.with(imageView.getContext())
                .load(url)
                .into(imageView);


    }


    public void setContact(Data contact) {
        this.contact = contact;
        notifyChange();
    }
}
