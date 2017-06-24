package com.home.ubbs.pizzame.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by udyatbhanu-mac on 6/23/17.
 */

public class MockPizzaServiceResponse {

    private static final String ID_TEST = "96926217";
    private static final String TITLE_TEST = "Brother John's Pizza Joint";
    private static final String DISTANCE_TEST = "0.2";
    private static final String ADDRESS_TEST = "3088 Balboa St";
    private static final String CITY_TEST = "San Francisco";
    private static final String STATE_TEST = "CA";
    private static final String AVERAGE_RATING_TEST = "3.4";
    private static final String TOTAL_RATINGS_TEST = "5";

    private static final String BUSINESS_URL_TEST = "www.pizzajointgrillmenu.com";
    private static final String LATITUDE_TEST = "30.37375";
    private static final String LONGITUDE_TEST = "-97.75542";
    private static final String PHONE_TEST = "707 342 4932";



    public static List<Result> getResults(){
        List<Result> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(getResult());
        }
        return results;
    }


    public static Result getResult() {

        Rating rating =  new Rating();
        rating.setAverageRating(AVERAGE_RATING_TEST);
        rating.setTotalRatings(TOTAL_RATINGS_TEST);

        Result result = new Result();
        result.setId(ID_TEST);
        result.setTitle(TITLE_TEST);
        result.setDistance(DISTANCE_TEST);
        result.setAddress(ADDRESS_TEST);
        result.setCity(CITY_TEST);
        result.setState(STATE_TEST);
        result.setRating(rating);
        result.setBusinessClickUrl(BUSINESS_URL_TEST);
        result.setLatitude(LATITUDE_TEST);
        result.setLongitude(LONGITUDE_TEST);
        result.setPhone(PHONE_TEST);

        return result;
    }


    public static PizzaResponse getMockPizzaResponse(){
        PizzaResponse pizzaResponse = new PizzaResponse();
        Query query = new Query();
        Results results = new Results();
        results.setResult(getResults());
        query.setResults(results);
        pizzaResponse.setQuery(query);


        return pizzaResponse;

    }
}
