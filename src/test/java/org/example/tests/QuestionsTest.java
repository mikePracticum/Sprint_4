package org.example.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.example.pages.MainPage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuestionsTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Parameterized.Parameter(0)
    public int faqIndex;
    @Parameterized.Parameter(1)
    public String expectedAnswer;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }

    @Before
    public void setUp() {
        /* System.setProperty("webdriver.chrome.driver",  "/Users/mihailkrikun/projects/Sprint_4/drivers/chromedriver");
         ChromeOptions options = new ChromeOptions();
         options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
         driver = new ChromeDriver(options);*/

        System.setProperty("webdriver.gecko.driver", "/Users/mihailkrikun/projects/Sprint_4/drivers/geckodriver");
        FirefoxOptions options = new FirefoxOptions();

        options.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox");
        driver = new FirefoxDriver(options);

        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkFaqAnswer() {
        mainPage.clickFaqDropdown(faqIndex);
        String actualAnswer = mainPage.getFaqAnswer(faqIndex);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
