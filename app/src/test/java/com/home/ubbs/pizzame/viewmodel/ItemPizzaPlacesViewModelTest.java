package com.home.ubbs.pizzame.viewmodel;

import android.view.View;

import com.home.ubbs.pizzame.BuildConfig;
import com.home.ubbs.pizzame.PizzaMeApplication;
import com.home.ubbs.pizzame.R;
import com.home.ubbs.pizzame.core.SessionStack;
import com.home.ubbs.pizzame.core.events.NavigationEvent;
import com.home.ubbs.pizzame.core.events.PermissionEvent;
import com.home.ubbs.pizzame.model.MockPizzaServiceResponse;
import com.home.ubbs.pizzame.model.PizzaResponse;
import com.home.ubbs.pizzame.model.Result;
import com.home.ubbs.pizzame.service.PizzaPlacesService;
import com.home.ubbs.pizzame.utils.Constants;
import com.home.ubbs.pizzame.views.activities.PizzaPlacesDetailsActivity;

import org.greenrobot.eventbus.EventBus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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

@RunWith(RobolectricGradleTestRunner.class) @Config(constants = BuildConfig.class, sdk = 21)
public class ItemPizzaPlacesViewModelTest {


    private static final String ID_TEST = "96926217";
    private static final String TITLE_TEST = "Brother John's Pizza Joint";
    private static final String DISTANCE_TEST = "0.2";
    private static final String ADDRESS_TEST = "3088 Balboa St";
    private static final String CITY_TEST = "San Francisco";
    private static final String STATE_TEST = "CA";

    @Mock private View view;
    @Mock private PizzaPlacesService pizzaPlacesService;
    private PizzaMeApplication pizzaMeApplication;

    PizzaResponse response;

    @Before
    public void setUpItemPizzaModelTest() {
        MockitoAnnotations.initMocks(this);

        pizzaMeApplication = (PizzaMeApplication) RuntimeEnvironment.application;
        pizzaMeApplication.setPizzaPlacesService(pizzaPlacesService);
        pizzaMeApplication.setScheduler(Schedulers.trampoline());

        response = MockPizzaServiceResponse.getMockPizzaResponse();

    }


    @Test
    public void shouldGetId() throws Exception {
        Result result = new Result();
        result.setId(ID_TEST);
        ItemPizzaPlacesViewModel itemPizzaPlacesViewModel = new ItemPizzaPlacesViewModel(result, pizzaMeApplication);
        assertEquals(result.getId(), itemPizzaPlacesViewModel.getId());
    }

    @Test
    public void shouldGetTitle() throws Exception {
        Result result = new Result();
        result.setTitle(TITLE_TEST);
        ItemPizzaPlacesViewModel itemPizzaPlacesViewModel = new ItemPizzaPlacesViewModel(result, pizzaMeApplication);
        assertEquals(result.getTitle(), itemPizzaPlacesViewModel.getTitle());
    }

    @Test
    public void shouldAddress() throws Exception {
        Result result = new Result();
        result.setDistance(ADDRESS_TEST);
        ItemPizzaPlacesViewModel itemPizzaPlacesViewModel = new ItemPizzaPlacesViewModel(result, pizzaMeApplication);
        assertEquals(result.getAddress(), itemPizzaPlacesViewModel.getAddress());
    }

    @Test
    public void shouldGetDistance() throws Exception {
        Result result = new Result();
        result.setDistance(DISTANCE_TEST);
        ItemPizzaPlacesViewModel itemPizzaPlacesViewModel = new ItemPizzaPlacesViewModel(result, pizzaMeApplication);
        assertEquals(pizzaMeApplication.getString(R.string.distance_unit, result.getDistance()), itemPizzaPlacesViewModel.getDistance());
    }

    @Test
    public void shouldGetCity() throws Exception {
        Result result = new Result();
        result.setDistance(CITY_TEST);
        ItemPizzaPlacesViewModel itemPizzaPlacesViewModel = new ItemPizzaPlacesViewModel(result, pizzaMeApplication);
        assertEquals(result.getCity(), itemPizzaPlacesViewModel.getCity());
    }

    @Test
    public void shouldGetState() throws Exception {
        Result result = new Result();
        result.setDistance(STATE_TEST);
        ItemPizzaPlacesViewModel itemPizzaPlacesViewModel = new ItemPizzaPlacesViewModel(result, pizzaMeApplication);
        assertEquals(result.getState(), itemPizzaPlacesViewModel.getState());
    }

    @Test
    public void shouldGetResult() throws Exception {
        Result result = MockPizzaServiceResponse.getResult();

        ItemPizzaPlacesViewModel itemPizzaPlacesViewModel = new ItemPizzaPlacesViewModel(result, pizzaMeApplication);
        assertEquals(ID_TEST, itemPizzaPlacesViewModel.getId());
        assertEquals(TITLE_TEST, itemPizzaPlacesViewModel.getTitle());
        assertEquals(ADDRESS_TEST, itemPizzaPlacesViewModel.getAddress());
        assertEquals(CITY_TEST, itemPizzaPlacesViewModel.getCity());
        assertEquals(STATE_TEST , itemPizzaPlacesViewModel.getState());
        assertEquals(pizzaMeApplication.getString(R.string.distance_unit, result.getDistance()), itemPizzaPlacesViewModel.getDistance());
    }

    @Test
    public void testOnItemClick() throws Exception {

        Result result = MockPizzaServiceResponse.getResult();
        ItemPizzaPlacesViewModel itemPizzaPlacesViewModel = new ItemPizzaPlacesViewModel(result, pizzaMeApplication);
        itemPizzaPlacesViewModel.onItemClick(view);
        assertNotNull(SessionStack.get(Constants.SESSION_RESULT_KEY));

        EventBus eventBus = Mockito.mock(EventBus.class);
        eventBus.post(new NavigationEvent(PizzaPlacesDetailsActivity.class, result));
        Mockito.verify(eventBus).post(Mockito.any(PermissionEvent.class));
    }


}
