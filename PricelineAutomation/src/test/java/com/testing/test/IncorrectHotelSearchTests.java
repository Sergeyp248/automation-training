package com.testing.test;

import com.testing.util.*;
import com.testing.page.HotelsSearchPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;

public class IncorrectHotelSearchTests extends CommonConditions {

    @Test
    public void emptyDestinationHotelSearch() {
        String errorMessage = new HotelsSearchPage(driver)
                .openPage()
                .emptyDestinationSearch()
                .getLocationErrorMessage();
        assertThat(errorMessage, is(equalTo(StringUtils.LOCATION_ERROR_MESSAGE)));
    }

}
