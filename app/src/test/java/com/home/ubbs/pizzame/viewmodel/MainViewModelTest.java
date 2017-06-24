package com.home.ubbs.pizzame.viewmodel;

import android.content.Context;

import com.home.ubbs.pizzame.BuildConfig;
import com.home.ubbs.pizzame.PizzaMeApplication;
import com.home.ubbs.pizzame.core.events.NavigationEvent;
import com.home.ubbs.pizzame.model.MockPizzaServiceResponse;
import com.home.ubbs.pizzame.model.PizzaResponse;
import com.home.ubbs.pizzame.service.PizzaPlacesService;

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

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * Created by udyatbhanu-mac on 6/23/17.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainViewModelTest {

    private static final String LATITUDE_TEST = "30.37375";
    private static final String LONGITUDE_TEST = "-97.75542";


    private PizzaMeApplication pizzaMeApplication;
    @Mock
    private PizzaPlacesService pizzaPlacesService;

    private PizzaResponse response;


    @Before
    public void setUpItemPizzaModelTest() {
        MockitoAnnotations.initMocks(this);

        pizzaMeApplication = (PizzaMeApplication) RuntimeEnvironment.application;
        pizzaMeApplication.setPizzaPlacesService(pizzaPlacesService);
        pizzaMeApplication.setScheduler(Schedulers.trampoline());

        response = MockPizzaServiceResponse.getMockPizzaResponse();

    }

    @Test
    public void shouldReturnToolBarImageUrl() throws Exception {
        MainViewModel mainViewModel = new MainViewModel(pizzaMeApplication);
        assertNotNull(mainViewModel.getToolbarImage());
    }


    @Test
    public void simulatePizzaServiceCallFromApi() throws Exception {
        PizzaResponse response = MockPizzaServiceResponse.getMockPizzaResponse();
        doReturn(Observable.just(response)).when(pizzaPlacesService).fetchPizzaPlaces(LATITUDE_TEST, LONGITUDE_TEST);
    }


    @Test
    public void shouldSendEventOnResponse() throws Exception {
        Context mockContext = mock(Context.class);
        EventBus eventBus = Mockito.mock(EventBus.class);
        MainViewModel mainViewModel = new MainViewModel(mockContext);
        mainViewModel.handleServiceResponse(eventBus);
        Mockito.verify(eventBus).post(Mockito.any(NavigationEvent.class));
    }


}
