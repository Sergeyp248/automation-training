package com.testing.test;

import com.testing.model.User;
import com.testing.page.MainPage;
import com.testing.service.UserCreator;
import com.testing.util.StringUtils;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class IncorrectDataPassTests extends CommonConditions{

    @Test
    public void sendDealToIncorrectEmail() {
        String errorMessage = new MainPage(driver)
                .openPage()
                .sendDealTo("asdfadf")
                .getErrorMessageFromSendDeal();
        assertThat(errorMessage, is(equalTo(StringUtils.INVALID_EMAIL_ERROR_MESSAGE)));
    }

    @Test
    public void signInWithEmptyEmail()
    {
        User testUser = UserCreator.withEmptyUserEmail();
        String errorMessage = new MainPage(driver)
                .openPage()
                .loginWithEmptyEmail(testUser)
                .getErrorMessageFromSignInEmailInput();
        assertThat(errorMessage, is(Matchers.equalTo(StringUtils.BLANK_EMAIL_ERROR_MESSAGE)));
    }

    @Test
    public void signInWithEmptyPassword()
    {
        User testUser = UserCreator.withEmptyPassword();
        String errorMessage = new MainPage(driver)
                .openPage()
                .loginWithEmptyPassword(testUser)
                .getErrorMessageFromSignInPasswordInput();
        assertThat(errorMessage, is(Matchers.equalTo(StringUtils.BLANK_PASSWORD_ERROR_MESSAGE)));
    }
}
