package com.home.ubbs.pizzame.viewmodel;

import com.home.ubbs.pizzame.BuildConfig;
import com.home.ubbs.pizzame.PizzaMeApplication;
import com.home.ubbs.pizzame.R;
import com.home.ubbs.pizzame.core.SessionStack;
import com.home.ubbs.pizzame.model.MockPizzaServiceResponse;
import com.home.ubbs.pizzame.model.PizzaResponse;
import com.home.ubbs.pizzame.model.Result;
import com.home.ubbs.pizzame.service.PizzaPlacesService;
import com.home.ubbs.pizzame.utils.Constants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import io.reactivex.schedulers.Schedulers;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by udyatbhanu-mac on 6/23/17.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = Config.NONE)
public class PizzaPlacesDetailsViewModelTest {

    private static final String ID_TEST = "96926217";
    private static final String TITLE_TEST = "Brother John's Pizza Joint";
    private static final String DISTANCE_TEST = "0.2";
    private static final String ADDRESS_TEST = "3088 Balboa St";
    private static final String CITY_TEST = "San Francisco";
    private static final String STATE_TEST = "CA";
    private static final String AVERAGE_RATING_TEST = "3.4";
    private static final String TOTAL_RATINGS_TEST = "5";
    private static final String PHONE_TEST = "707 342 4932";

    private static final String BUSINESS_URL_TEST = "www.pizzajointgrillmenu.com";

    private PizzaMeApplication pizzaMeApplication;
    @Mock
    private PizzaPlacesService pizzaPlacesService;

    private Result result;



    private PizzaResponse response;

    @Before
    public void setUpItemPizzaModelTest() {
        MockitoAnnotations.initMocks(this);

        pizzaMeApplication = (PizzaMeApplication) RuntimeEnvironment.application;
        pizzaMeApplication.setPizzaPlacesService(pizzaPlacesService);
        pizzaMeApplication.setScheduler(Schedulers.trampoline());
        result = MockPizzaServiceResponse.getResult();
        SessionStack.put(Constants.SESSION_RESULT_KEY, result);
        response = MockPizzaServiceResponse.getMockPizzaResponse();


    }


    @Test
    public void shouldReturnImageUrl() throws Exception {
        PizzaPlacesDetailsViewModel pizzaPlacesDetailsViewModel = new PizzaPlacesDetailsViewModel(pizzaMeApplication);
        assertNotNull(pizzaPlacesDetailsViewModel.getToolbarImage());
    }

    @Test
    public void shouldGetTitle() throws Exception {
        PizzaPlacesDetailsViewModel pizzaPlacesDetailsViewModel = new PizzaPlacesDetailsViewModel(pizzaMeApplication);
        assertNotNull(pizzaPlacesDetailsViewModel.getTitle());
        assertEquals(TITLE_TEST, pizzaPlacesDetailsViewModel.getTitle());
    }

    @Test
    public void shouldGetAddress() throws Exception {
        result.setTitle(ADDRESS_TEST);
        PizzaPlacesDetailsViewModel pizzaPlacesDetailsViewModel = new PizzaPlacesDetailsViewModel(pizzaMeApplication);
        assertNotNull(pizzaPlacesDetailsViewModel.getAddress());
        assertEquals(ADDRESS_TEST, pizzaPlacesDetailsViewModel.getAddress());
    }

    @Test
    public void shouldGetCity() throws Exception {
        PizzaPlacesDetailsViewModel pizzaPlacesDetailsViewModel = new PizzaPlacesDetailsViewModel(pizzaMeApplication);
        assertNotNull(pizzaPlacesDetailsViewModel.getCity());
        assertEquals(CITY_TEST, pizzaPlacesDetailsViewModel.getCity());
    }

    @Test
    public void shouldGetDistance() throws Exception {
        PizzaPlacesDetailsViewModel pizzaPlacesDetailsViewModel = new PizzaPlacesDetailsViewModel(pizzaMeApplication);
        assertNotNull(pizzaPlacesDetailsViewModel.getDistance());
        assertEquals(pizzaMeApplication.getString(R.string.distance_unit,DISTANCE_TEST), pizzaPlacesDetailsViewModel.getDistance());
    }

    @Test
    public void shouldGetRating() throws Exception {
        PizzaPlacesDetailsViewModel pizzaPlacesDetailsViewModel = new PizzaPlacesDetailsViewModel(pizzaMeApplication);
        assertNotNull(pizzaPlacesDetailsViewModel.getAverageRating());
        assertEquals(AVERAGE_RATING_TEST, pizzaPlacesDetailsViewModel.getAverageRating());
    }

    @Test
    public void shouldGetWebsite() throws Exception {
        PizzaPlacesDetailsViewModel pizzaPlacesDetailsViewModel = new PizzaPlacesDetailsViewModel(pizzaMeApplication);
        assertNotNull(pizzaPlacesDetailsViewModel.getWebsite());
        assertEquals(BUSINESS_URL_TEST, pizzaPlacesDetailsViewModel.getWebsite());
    }

    @Test
    public void shouldGetPhone() throws Exception {
        PizzaPlacesDetailsViewModel pizzaPlacesDetailsViewModel = new PizzaPlacesDetailsViewModel(pizzaMeApplication);
        assertNotNull(pizzaPlacesDetailsViewModel.getPhone());
        assertEquals(PHONE_TEST, pizzaPlacesDetailsViewModel.getPhone());
    }



}
