package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;


public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By faqDropdownButtons = By.xpath(".//div[@class='accordion__button']");
    private final By faqAnswers = By.xpath(".//div[@data-accordion-component='AccordionItemPanel']/p");
    private final By acceptCookieButton = By.id("rcc-confirm-button");
    public static final String SCOOTER_URL = "https://qa-scooter.praktikum-services.ru/";

    @FindBy(className = "Button_Button__ra12g")
    private WebElement orderButtonTop;

    @FindBy(css = ".Button_Button__ra12g.Button_Middle__1CSJM")
    private WebElement orderButtonBottom;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);

    }

    public void clickOrderButtonTop() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("rcc-confirm-button")));
            cookieButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(orderButtonTop));
            orderButtonTop.click();
    }

    public void clickOrderButtonBottom() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom));
            orderButtonBottom.click();
    }

    public void fillScooterOrderForm(String deliveryDate) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dateField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='* Когда привезти самокат']")));
            dateField.sendKeys(deliveryDate);
    }
    public void acceptCookies() {
        WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(acceptCookieButton));
        cookieButton.click();
    }
    private By getFaqHeading(int index) {
        return By.id("accordion__heading-" + index);
    }
    private By getFaqContent(int index) {
        return By.id("accordion__panel-" + index);
    }
    public void clickFaqHeading(int index) {
        WebElement faqHeading = driver.findElement(getFaqHeading(index));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", faqHeading);
        wait.until(ExpectedConditions.elementToBeClickable(faqHeading));
        faqHeading.click();
    }
    public String getFaqAnswer(int index) {
        WebElement answerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(getFaqContent(index)));
        return answerElement.getText();
    }
}
