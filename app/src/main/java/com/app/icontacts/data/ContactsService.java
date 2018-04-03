package com.app.icontacts.data;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ContactsService {

  @GET
  Observable<ContactsService> fetchContacts(@Url String url);

  @POST
  Observable<ContactsService> pushContacts(@Url String url);
}
