package com.testing.page;

import com.testing.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage
{
	private final String BASE_URL = "https://priceline.com";
	private final Logger logger = LogManager.getRootLogger();

	private final By linkLoggedInUserLocator = By.xpath("//span[@id='sign-out-menu-email-address']");

	@FindBy(id = "signin-first-name")
	private WebElement inputLogin;

	@FindBy(id = "password")
	private WebElement inputPassword;

	@FindBy(id = "button-sign-in")
	private WebElement buttonSubmit;

	@FindBy(id = "global-navigation-modal")
	private WebElement detailedInformationButton;

	@FindBy(xpath = "//span[@class='message']")
	private WebElement signInButton;

	public MainPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public MainPage openPage()
	{
		driver.navigate().to(BASE_URL);
		logger.info("Main page opened");
		return this;
	}

	public MainPage login(User user)
	{
		signInButton.click();
		inputLogin.sendKeys(user.getUserEmail());
		inputPassword.sendKeys(user.getPassword());
		buttonSubmit.click();
		logger.info("Login performed");
		detailedInformationButton.click();
		return new MainPage(driver);
	}

	public String getLoggedInUserEmail()
	{
		WebElement linkLoggedInUser = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.presenceOfElementLocated(linkLoggedInUserLocator));
		return linkLoggedInUser.getAttribute("content");
	}
}
