package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    // Кнопка "Заказать" вверху страницы
    private By orderButtonTop = By.className("Button_Button__ra12g");

    // Кнопка "Заказать" внизу страницы
    private By orderButtonBottom = By.xpath("//div[@class='Home_FinishButton__1_cWm']//button[text()='Заказать']");

    // Вопросы о важном
    private By questionsList = By.className("accordion__button");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderButtonBottom() {
        driver.findElement(orderButtonBottom).click();
    }

    public void clickQuestion(int index) {
        driver.findElements(questionsList).get(index).click();
    }
}
