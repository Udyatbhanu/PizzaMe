package com.home.ubbs.pizzame.viewmodel;

import com.home.ubbs.pizzame.BuildConfig;
import com.home.ubbs.pizzame.PizzaMeApplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by udyatbhanu-mac on 6/23/17.
 */


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = Config.NONE)
public class PizzaPlacesViewModelTest {

    private static final String TITLE_TEST = "Pizza Places Near You";

    private PizzaMeApplication pizzaMeApplication;

    @Before
    public void setUpItemPizzaModelTest() {
        pizzaMeApplication = (PizzaMeApplication) RuntimeEnvironment.application;


    }


    @Test
    public void shouldGetTitle() throws Exception {
        PizzaPlacesViewModel pizzaPlacesDetailsViewModel = new PizzaPlacesViewModel(pizzaMeApplication);
        assertNotNull(pizzaPlacesDetailsViewModel.getTitle());
        assertEquals(TITLE_TEST, pizzaPlacesDetailsViewModel.getTitle());
    }

    @Test
    public void shouldArrayUrls() throws Exception {
        PizzaPlacesViewModel pizzaPlacesDetailsViewModel = new PizzaPlacesViewModel(pizzaMeApplication);
        assertNotNull(pizzaPlacesDetailsViewModel.getToolbarImage());
    }

}
