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

import java.util.concurrent.TimeUnit;

public class MainPage extends AbstractPage {
    private final String BASE_URL = "https://priceline.com";
    private final Logger logger = LogManager.getRootLogger();

    private final By linkLoggedInUserLocator = By.xpath("//*[@id=\"sign-out-menu-email-address\"]");

    @FindBy(id = "signin-first-name")
    private WebElement inputLogin;

    @FindBy(id = "password")
    private WebElement inputPassword;

    @FindBy(id = "button-sign-in")
    private WebElement buttonSubmit;

    @FindBy(xpath = "//*[@id=\"in-path-sign-in-out-click\"]")
    private WebElement signInButton;

    @FindBy(xpath = "//*[@id=\"email-error-tooltip-message\"]")
    private WebElement blankEmailErrorMessage;

    @FindBy(xpath = "//*[@id=\"password-error-tooltip-message\"]")
    private WebElement blankPasswordErrorMessage;

    @FindBy(xpath = "//*[@id=\"sign-out-menu-email-address\"]")
    private WebElement detailedInformationBlock;

    @FindBy(xpath = "//*[@id=\"hero\"]/div[2]/div[3]/div[3]/div/div[2]/div[1]/input")
    private WebElement sendDealInput;

    @FindBy(xpath = "//*[@id=\"hero\"]/div[2]/div[3]/div[3]/div/div[2]/div[2]/button")
    private WebElement sendDealButton;

    @FindBy(xpath = "//*[@id=\"email_error\"]/div/div")
    private WebElement sendDealErrorMessageBlock;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MainPage openPage() {
        driver.navigate().to(BASE_URL);
        logger.info("Main page opened");
        return this;
    }

    public MainPage login(User user) {
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        signInButton.click();
        inputLogin.sendKeys(user.getUserEmail());
        inputPassword.sendKeys(user.getPassword());
        buttonSubmit.click();
        logger.info("Login performed");
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        return new MainPage(driver);
    }

    public MainPage loginWithEmptyEmail(User user) {
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        signInButton.click();
        inputLogin.sendKeys(user.getUserEmail());
        inputPassword.sendKeys(user.getPassword());
        buttonSubmit.click();
        return new MainPage(driver);
    }

    public MainPage loginWithEmptyPassword(User user) {
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        signInButton.click();
        inputLogin.sendKeys(user.getUserEmail());
        inputPassword.sendKeys(user.getPassword());
        buttonSubmit.click();
        return new MainPage(driver);
    }

    public String getLoggedInUserEmail() {
        WebElement linkLoggedInUser = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(linkLoggedInUserLocator));
        return linkLoggedInUser.getText();
    }

    public MainPage sendDealTo(String email) {
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        sendDealInput.sendKeys(email);
        sendDealButton.click();
        return new MainPage(driver);
    }

    public String getErrorMessageFromSendDeal() {
        return sendDealErrorMessageBlock.getText();
    }

    public String getErrorMessageFromSignInEmailInput() {
        return blankEmailErrorMessage.getText();
    }

    public String getErrorMessageFromSignInPasswordInput() {
        return blankPasswordErrorMessage.getText();
    }
}
