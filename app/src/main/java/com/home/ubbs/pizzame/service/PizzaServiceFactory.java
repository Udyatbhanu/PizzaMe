package com.home.ubbs.pizzame.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by udyatbhanu-mac on 6/19/17.
 */

public class PizzaServiceFactory {

    //Ideally this would come from a resource svc file or some other resource. Since this is a simple service, it's hardcoded
    private final static String BASE_URL = "https://query.yahooapis.com/v1/public/";



    public static PizzaPlacesService create() {
        final ObjectMapper mapper = new ObjectMapper()
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(PizzaPlacesService.class);
    }
}
