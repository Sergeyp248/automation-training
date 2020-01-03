package com.testing.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class HotelsSearchPage extends AbstractPage{

    private final String HOTELS_URL = "https://priceline.com/hotels/";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//*[@id=\"location\"]")
    private WebElement destinationInput;

    @FindBy(xpath = "//*[@id=\"date-range\"]")
    private WebElement dateRange;

    @FindBy(id = "roomCount")
    private WebElement roomsAmount;

    @FindBy(xpath = "//*[@id=\"app-container\"]/div/div[1]/div/div[1]/div/div/div[1]/div/div[2]/div[1]/div/form/div/div/div/div[5]/button")
    private WebElement searchButton;

    @FindBy(id = "location-error")
    private WebElement locationErrorMessage;

    public HotelsSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public HotelsSearchPage openPage() {
        driver.navigate().to(HOTELS_URL);
        logger.info("Hotel search page opened");
        return this;
    }

    public HotelsSearchPage emptyDestinationSearch() {
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        destinationInput.click();
        destinationInput.sendKeys("");
        searchButton.click();
        return this;
    }

    public String getLocationErrorMessage() {
        return locationErrorMessage.getText();
    }
}
