package com.testing.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelsSearchPage extends AbstractPage{

    private final String HOTELS_URL = "https://priceline.com/hotels/";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//input[@class='sc-iGPElx glEPti']")
    private WebElement destinationInput;

    @FindBy(xpath = "//input[@class='sc-iGPElx llaPlf']")
    private WebElement dateRange;

    @FindBy(id = "roomCount")
    private WebElement roomsAmount;

    @FindBy(xpath = "//button[@class='sc-qrIAp bbZvcz']")
    private WebElement searchButton;

    @FindBy(id = "location-error")
    private WebElement locationErrorMessage;

    public HotelsSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public HotelsSearchPage openPage() {
        logger.info("Hotel search page opened");
        return this;
    }

    public HotelsSearchPage emptyDestinationSearch() {
        destinationInput.click();
        destinationInput.sendKeys("");
        searchButton.click();
        return this;
    }

    public String getLocationErrorMessage() {
        return locationErrorMessage.getText();
    }
}
