package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver driver;

    // Поля ввода
    private By firstNameField = By.xpath("//input[@placeholder='* Имя']");
    private By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStationField = By.className("select-search__input");
    private By phoneNumberField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка "Далее"
    private By nextButton = By.xpath("//button[text()='Далее']");

    // Поля для ввода данных о заказе
    private By deliveryDateField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By rentalPeriodDropdown = By.className("Dropdown-placeholder");
    private By blackColorCheckbox = By.id("black");
    private By greyColorCheckbox = By.id("grey");
    private By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");

    // Кнопка "Заказать"
    private By orderButton = By.xpath("//button[text()='Заказать']");

    // Кнопка подтверждения заказа
    private By confirmOrderButton = By.xpath("//button[text()='Да']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillPersonalDetails(String firstName, String lastName, String address, String metroStation, String phoneNumber) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroStationField).sendKeys(metroStation);
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void fillOrderDetails(String deliveryDate, String rentalPeriod, String comment, boolean blackColor, boolean greyColor) {
        driver.findElement(deliveryDateField).sendKeys(deliveryDate);
        driver.findElement(rentalPeriodDropdown).click();
        driver.findElement(By.xpath("//div[text()='" + rentalPeriod + "']")).click();
        if (blackColor) {
            driver.findElement(blackColorCheckbox).click();
        }
        if (greyColor) {
            driver.findElement(greyColorCheckbox).click();
        }
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickConfirmOrderButton() {
        driver.findElement(confirmOrderButton).click();
    }
}
