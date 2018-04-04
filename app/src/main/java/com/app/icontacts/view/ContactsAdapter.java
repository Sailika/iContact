package com.app.icontacts.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.app.icontacts.R;
import com.app.icontacts.databinding.ItemBinding;
import com.app.icontacts.model.Data;
import com.app.icontacts.viewmodel.ContactItemViewModel;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsAdapterViewHolder> {

    private ArrayList<Data> contactsList;

    public ContactsAdapter() {
        this.contactsList = null;
    }

    @Override
    public ContactsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBinding contactItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item,
                        parent, false);
        return new ContactsAdapterViewHolder(contactItemBinding);
    }

    @Override
    public void onBindViewHolder(ContactsAdapterViewHolder holder, int position) {
        holder.bindPeople(contactsList.get(position));
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public void setContactsList(ArrayList<Data> contactsList) {
        this.contactsList = contactsList;
        notifyDataSetChanged();
    }

    public static class ContactsAdapterViewHolder extends RecyclerView.ViewHolder {

        ItemBinding mContactItemBinding;

        //Constructor
        public ContactsAdapterViewHolder(ItemBinding contactItemBinding) {
            super(contactItemBinding.cardView);
            this.mContactItemBinding = contactItemBinding;
        }

        //method to bind the list items
        void bindPeople(Data contact) {
            if (mContactItemBinding.getContactViewModel() == null) {
                mContactItemBinding.setContactViewModel(new ContactItemViewModel(contact, itemView.getContext()));
            } else {
                mContactItemBinding.getContactViewModel().setContact(contact);
            }
        }
    }
}
