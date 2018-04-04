package com.app.icontacts.data;

import com.app.icontacts.model.Contacts;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ContactsService {

  @GET
  Observable<Contacts> fetchContacts(@Url String url);

  @POST
  Observable<Contacts> pushContacts(@Url String url);
}
