package org.example.tests;

import org.example.pages.MainPage;
import org.example.pages.OrderPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ScooterOrderTest {
    private WebDriver driver;

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String deliveryDate;
    private final String rentalPeriod;
    private final String comment;
    private final boolean blackColor;
    private final boolean greyColor;

    public ScooterOrderTest(String firstName, String lastName, String address, String metroStation, String phoneNumber,
                            String deliveryDate, String rentalPeriod, String comment, boolean blackColor, boolean greyColor) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.comment = comment;
        this.blackColor = blackColor;
        this.greyColor = greyColor;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Иванов", "Москва, ул. Ленина, 1", "Черкизовская", "89999999999", "31.07.2024", "сутки", "Комментарий 1", true, false},
                {"Петр", "Петров", "Москва, ул. Ленина, 2", "Черкизовская", "89999999998", "01.08.2024", "трое суток", "Комментарий 2", false, true},
        });
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",  "drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);

        /*System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(System.getenv("FIREFOX_BINARY_PATH"));
        driver = new FirefoxDriver(options);*/

        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testScooterOrder() {
        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        mainPage.clickOrderButtonTop();
        orderPage.fillPersonalDetails(firstName, lastName, address, metroStation, phoneNumber);
        orderPage.clickNextButton();
        orderPage.fillOrderDetails(deliveryDate, rentalPeriod, comment, blackColor, greyColor);
        orderPage.clickOrderButton();
        orderPage.clickConfirmOrderButton();

        driver.get("https://qa-scooter.praktikum-services.ru/");

        mainPage.clickOrderButtonBottom();
        orderPage.fillPersonalDetails(firstName, lastName, address, metroStation, phoneNumber);
        orderPage.clickNextButton();
        orderPage.fillOrderDetails(deliveryDate, rentalPeriod, comment, blackColor, greyColor);
        orderPage.clickOrderButton();
        orderPage.clickConfirmOrderButton();
    }

}
