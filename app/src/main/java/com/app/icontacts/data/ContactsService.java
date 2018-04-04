package com.app.icontacts.data;

import com.app.icontacts.model.Contacts;
import com.app.icontacts.model.CreateContactsRequest;
import com.app.icontacts.model.CreateContactsResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ContactsService {

    @GET
    Observable<Contacts> fetchContacts(@Url String url);

    @POST
    Observable<CreateContactsResponse> pushContacts(@Url String url, @Body CreateContactsRequest request);
}
