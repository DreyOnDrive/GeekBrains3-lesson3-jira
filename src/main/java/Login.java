import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class Login {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static boolean coockiesIsReady = false;
    public static List<Cookie> coockies = null;
    public static boolean loginSuccess = true;

    public static void main(String[] args) throws InterruptedException {

        System.out.println(ANSI_RESET + "ТЕСТ-КЕЙС ВХОДА В СИСТЕМУ" + ANSI_RESET);

        WebDriverManager.chromiumdriver().setup();
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();


        try{
            driver.get("https://my-atlassian-site-441.atlassian.net/");
            System.out.println(ANSI_GREEN + "Страница входа открыта - успех" + ANSI_GREEN);
        } catch (Exception e){
            System.out.println(ANSI_RED + "Страница входа открыта - провал" + ANSI_RED);
            loginSuccess = false;
        }

        try {
            driver.findElement(By.xpath("//input[@name='username']")).sendKeys("cegog77137@shirulo.com");
            System.out.println(ANSI_GREEN + "Почта введена - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Почта введена - провал " + ANSI_RED);
            loginSuccess = false;
        }

        try {
            driver.findElement(By.xpath("//span[text()[contains(.,'Продолжить')]]")).click();
            System.out.println(ANSI_GREEN + "Подтвердить почту - успех"+ ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Подтвердить почту - провал" + ANSI_RED);
            loginSuccess = false;
        }

        try {
            WebDriverWait wait1 = new WebDriverWait(driver, 5);
            wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
            driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("cegog77137");
            System.out.println(ANSI_GREEN + "Ввести пароль - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Ввести пароль - провал" + ANSI_RED);
            loginSuccess = false;
        }

        try {
            driver.findElement(By.xpath("//span[text()[contains(.,'Войти')]]")).click();
            System.out.println(ANSI_GREEN + "Кнопка входа нажата - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Кнопка входа нажата - провал" + ANSI_RED);
            loginSuccess = false;
        }

        try {
            WebDriverWait wait2 = new WebDriverWait(driver, 5);
            wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()[contains(.,'Создать')]]")));
            System.out.println(ANSI_GREEN + "Вход в учётную запись - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Вход в учётную запись - провал" + ANSI_RED);
            loginSuccess = false;
        }


        try {
            driver.findElement(By.xpath("//div [@style='display: inline-block; position: relative; outline: 0px;']")).click();
            System.out.println(ANSI_GREEN + "Меню учётной записи - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Меню учётной записи - провал" + ANSI_RED);
            loginSuccess = false;
        }


        try {
            driver.findElement(By.xpath("//span[.='Выйти']")).click();
            System.out.println(ANSI_GREEN + "Выход из учётной записи - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Выход из учётной записи - провал" + ANSI_RED);
            loginSuccess = false;
        }


        try {
            coockies = new ArrayList<Cookie>((driver.manage().getCookies()));
            coockiesIsReady = true;
            System.out.println(ANSI_GREEN + "Coockies для следующего теста получены - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Coockies для следующего теста НЕ получены - провал" + ANSI_RED);
            loginSuccess = false;
        }

        driver.quit();

        if (loginSuccess) {
            System.out.println();
            System.out.println(ANSI_GREEN + "Тест-кейс выполнен успешно" + ANSI_GREEN);
        } else {
            System.out.println();
            System.out.println(ANSI_RED + "Тест-кейс провален" + ANSI_RED);
        }
    }
}
