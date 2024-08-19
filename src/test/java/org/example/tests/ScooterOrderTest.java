package org.example.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class ScooterOrderTest {

    @Test
    public void testScooterOrder() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");  // Remove this line for non-headless mode
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("http://yourwebsite.com");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            System.out.println("Waiting for order button to be clickable...");
            WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("order_button_top")));
            System.out.println("Order button is clickable.");

            orderButton.click();

            // Add assertions and further interactions here

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
