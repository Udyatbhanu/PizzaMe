package com.home.ubbs.pizzame.service;

import com.home.ubbs.pizzame.model.PizzaResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by udyatbhanu-mac on 6/19/17.
 */


public interface PizzaPlacesService {


   /* Query format:
    * "yql?q=select * from local.search where query = 'pizza' and latitude = {latitude} and longitude = {longitude}&format=json" */

    @GET("yql")
    Observable<PizzaResponse> fetchPizzaPlaces(@Query("q") String query, @Query("format") String format);



}
