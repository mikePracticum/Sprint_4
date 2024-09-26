package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstNameField = By.cssSelector("[placeholder='* Имя']");
    private final By lastNameField = By.cssSelector("[placeholder='* Фамилия']");
    private final By addressField = By.cssSelector("[placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationField = By.cssSelector("[placeholder='* Станция метро']");
    private final By phoneNumberField = By.cssSelector("[placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private final By deliveryDateField = By.cssSelector("[placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.cssSelector(".Dropdown-root");
    private final By commentField = By.cssSelector("[placeholder='Комментарий для курьера']");
    private final By blackColorCheckbox = By.id("black");
    private final By greyColorCheckbox = By.id("grey");
    private final By orderButton = By.cssSelector(".Order_Buttons__1xGrp .Button_Button__ra12g.Button_Middle__1CSJM:not(.Button_Inverted__3IF-i)");
    private final By confirmOrderButton = By.xpath(".//*[text()='Да']");
    private final By orderConfirmation = By.cssSelector(".Order_Modal__YZ-d3");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillPersonalDetails(String firstName, String lastName, String address, String metroStation, String phoneNumber) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);
        WebElement metroField = driver.findElement(metroStationField);
        metroField.click();
        metroField.sendKeys(metroStation);
        WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(@class, 'select-search__row') and .//div[text()='" + metroStation + "']]")));
        suggestion.click();
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void fillOrderDetails(String deliveryDate, String rentalPeriod, String comment, boolean blackColor, boolean greyColor) {
        WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryDateField));
        if (!dateField.isDisplayed()) {
            throw new AssertionError("Date field is not visible.");
        }
        dateField.click();
        dateField.clear();
        dateField.sendKeys(deliveryDate);
        dateField.sendKeys(Keys.TAB);
        String dateValue = dateField.getAttribute("value");
        WebElement rentalPeriodElement = driver.findElement(rentalPeriodField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", rentalPeriodElement);
        WebElement datePicker = driver.findElement(By.cssSelector(".react-datepicker__month-container"));
        if (datePicker.isDisplayed()) {
            driver.findElement(By.cssSelector("body")).click();
        }
        rentalPeriodElement = wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodField));
        rentalPeriodElement.click();
        WebElement rentalOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Dropdown-option' and text()='" + rentalPeriod + "']")));
        rentalOption.click();
        if (blackColor) {
            WebElement blackCheckbox = driver.findElement(blackColorCheckbox);
            if (!blackCheckbox.isSelected()) {
                blackCheckbox.click();
            }
        }
        if (greyColor) {
            WebElement greyCheckbox = driver.findElement(greyColorCheckbox);
            if (!greyCheckbox.isSelected()) {
                greyCheckbox.click();
            }
        }
        WebElement commentFieldElement = driver.findElement(commentField);
        commentFieldElement.sendKeys(comment);
    }
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
    public void clickConfirmOrderButton() {
        driver.findElement(confirmOrderButton).click();
    }
    public boolean isOrderSuccessMessageDisplayed() {
        WebElement successMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmation));
        String successMessageText = successMessageElement.getText();
        return successMessageText.contains("Заказ оформлен");
    }
}
