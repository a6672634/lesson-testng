package org.itstep.qa.lesson;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LessonTest {

    WebDriver driver;

    @BeforeClass
    public void initWebdriver() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/chromedriver243.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        driver = new ChromeDriver(options);
    }

    @BeforeMethod
    public void startUp() {
        driver.get("http://hflabs.github.io/suggestions-demo/");
    }

    @AfterClass
    public void closeBrowser() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

    @Test
    public void openPageTest() {
        //проверка открытия страницы
        WebElement pageHeader =
                driver.findElement(By.cssSelector(".page-header"));
        Assert.assertTrue(pageHeader.isDisplayed(), "Не найден заголовок страницы");
    }

    @Test
    public void sendMessageTest() {
        //проверка открытия страницы
        WebElement pageHeader =
                driver.findElement(By.cssSelector(".page-header"));
        Assert.assertTrue(pageHeader.isDisplayed(), "Не найден заголовок страницы");
        //находим и заполняем поле Фамилия
        WebElement field =
                driver.findElement(By.id("fullname-surname"));
        field.sendKeys("Петров");
        //находим и заполняем поле Имя
        field =
                driver.findElement(By.id("fullname-name"));
        field.sendKeys("Петр");
        //находим и заполняем поле Отчество
        field =
                driver.findElement(By.id("fullname-patronymic"));
        field.sendKeys("Петрович");
        //находим и заполняем поле почты
        field =
                driver.findElement(By.id("email"));
        field.sendKeys("petrovich@hotmail.com");
        //находим и заполняем поле обращения
        field =
                driver.findElement(By.id("message"));
        field.sendKeys("Это очень важно!");
        //нажимаем кнопку Отправить
        WebElement button =
                driver.findElement(By.
                        xpath("//*[@id=\"feedback-form\"]/div[5]/button"));
        button.click();
        //проверяем, что тест выполнен успешно
        //внутрь try-catch запихана пристановка теста на 5 секунд
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement feedback =
                driver.findElement(By.id("feedback-message"));
        Assert.assertTrue(feedback.isDisplayed(), "Не найден результат отправки обращения");
        WebElement feedbackButton =
                driver.findElement(By.xpath("//*[@id=\"feedback-message\"]/p[2]/button"));
        Assert.assertTrue(feedbackButton.isDisplayed(),
                "Не найден результат отправки обращения");
        String controlValue = "Хорошо, я понялasdf";
        Assert.assertEquals(feedbackButton.getText(), controlValue, "Текст в кнопке не " +
                "сответствует = " + controlValue);
    }
}
