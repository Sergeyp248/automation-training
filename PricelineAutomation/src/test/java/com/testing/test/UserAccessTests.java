package com.testing.test;

import com.testing.model.User;
import com.testing.page.MainPage;
import com.testing.service.UserCreator;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class UserAccessTests extends CommonConditions {
	@Test
	public void oneCanSignInPriceline()
	{
		User testUser = UserCreator.withCredentialsFromProperty();
		String loggedInUserEmail = new MainPage(driver)
				.openPage()
				.login(testUser)
				.getLoggedInUserEmail();
		assertThat(loggedInUserEmail, is(equalTo(testUser.getUserEmail())));
	}
}
