import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeEpic {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static boolean makeEpicSuccess = true;

    public static void main(String[] args) throws InterruptedException {
        System.out.println();

        if (!Login.coockiesIsReady){
            System.out.println(ANSI_RESET + "ТЕСТ-КЕЙС СОЗДАНИЯ ЭПИКА не может стартовать без coockies"+ ANSI_RESET);
            return;
        } if (!Login.loginSuccess) {
            System.out.println(ANSI_RESET + "ТЕСТ-КЕЙС СОЗДАНИЯ ЭПИКА не может стартовать без удачной регистрации"+ ANSI_RESET);
            return;
        } else {
            System.out.println(ANSI_RESET + "ТЕСТ-КЕЙС СОЗДАНИЯ ЭПИКА" + ANSI_RESET);
        }

        WebDriverManager.chromiumdriver().setup();
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();


        try{
            driver.get("https://my-atlassian-site-441.atlassian.net/");
            driver.manage().deleteAllCookies();
            for (Cookie coockie : Login.coockies) {
                driver.manage().addCookie(coockie);
            }
            driver.navigate().to("https://my-atlassian-site-441.atlassian.net/jira/your-work");
            System.out.println(ANSI_GREEN + "Переход в личный кабинет - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Переход в личный кабинет - провал" + ANSI_RED);
            makeEpicSuccess  = false;
        }


        try {
            driver.findElement(By.xpath("//span[text()[contains(.,'Создать')]]")).click();
            System.out.println(ANSI_GREEN + "Нажать создать - успех" + ANSI_GREEN);
        } catch (Exception e){
            System.out.println(ANSI_RED + "Нажать создать - провал" + ANSI_RED);
            makeEpicSuccess = false;
        }


        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()[contains(.,'Создать задачу')]]")));
            System.out.println(ANSI_GREEN + "Переход в поп-ап создания тикета - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Переход в поп-ап создания тикета - провал" + ANSI_RED);
            makeEpicSuccess = false;
        }


        try {
            driver.findElement(By.xpath("//input[@id='issuetype-field']")).click();
            driver.findElement(By.xpath("//input[@id='issuetype-field']")).sendKeys("Эпик\n");
            System.out.println(ANSI_GREEN + "Тип тикета - успех" + ANSI_GREEN);
        } catch (Exception e){
            System.out.println(ANSI_RED + "Тип тикета - провал" + ANSI_RED);
            makeEpicSuccess = false;
        }

        Thread.sleep(1000);


        try {
            ((JavascriptExecutor)driver).executeScript("document.getElementById('summary').value = 'New Test Epic';");
            System.out.println(ANSI_GREEN + "Заполнить заголовок - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Заполнить заголовок - провал" + ANSI_RED);
            makeEpicSuccess = false;
        }


        try {
            ((JavascriptExecutor)driver).executeScript("document.getElementById('description').value = 'New Test Epic';");
            System.out.println(ANSI_GREEN + "Заполнить описание - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Заполнить описание - провал" + ANSI_RED);
            makeEpicSuccess = false;
        }


        try {
            driver.findElement(By.xpath("//a[@href='#assignee']")).click();
            System.out.println(ANSI_GREEN + "Назначение - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Назначение - провал" + ANSI_RED);
            makeEpicSuccess = false;
        }


        try {
            driver.findElement(By.xpath("//input[@class='aui-button aui-button-primary']")).click();
            System.out.println(ANSI_GREEN + "Создать тикет - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Создать тикет - провал" + ANSI_RED);
            makeEpicSuccess = false;
        }

        Thread.sleep(10000);

        try {
            driver.findElement(By.xpath("//span[text()[contains(.,'Ваша работа')]]")).click();
            System.out.println(ANSI_GREEN + "Ваша работа - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Наша работа - провал" + ANSI_RED);
            makeEpicSuccess = false;
        }


        try {
            ((JavascriptExecutor)driver).executeScript("document.querySelector(\"a[href^='/browse/TEST']\").click();");
            System.out.println(ANSI_GREEN + "Назначенный тикет - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Назначенный тикет - провал" + ANSI_RED);
            makeEpicSuccess = false;
        }


        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()[contains(.,'New Test Epic')]]")));
            System.out.println(ANSI_GREEN + "Переход в созданный тикет - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Переход в созданный тикет - провал" + ANSI_RED);
            makeEpicSuccess = false;
        }


        try {
            driver.findElement(By.xpath("//button[@aria-label='Действия']")).click();
            System.out.println(ANSI_GREEN + "Действие с тикетом - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Действие с тикетом - провал" + ANSI_RED);
            makeEpicSuccess = false;
        }


        try {
            driver.findElement(By.xpath("//span[text()[contains(.,'Удалить')]]")).click();
            System.out.println(ANSI_GREEN + "Удаление тикета - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Удаление тикета - провал" + ANSI_RED);
            makeEpicSuccess = false;
        }


        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='dialog']//span[.='Удалить']")));
            System.out.println(ANSI_GREEN + "Переход в поп-ап удаления тикета - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Переход в поп-ап удаления тикета - провал" + ANSI_RED);
            makeEpicSuccess = false;
        }


        try {
            driver.findElement(By.xpath("//div[@role='dialog']//span[.='Удалить']")).click();
            System.out.println(ANSI_GREEN + "Подтверждение удаления тикета - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Подтверждение удаления тикета - провал" + ANSI_RED);
            makeEpicSuccess = false;
        }

            driver.quit();


        if (makeEpicSuccess) {
            System.out.println();
            System.out.println(ANSI_GREEN + "Тест-кейс выполнен успешно" + ANSI_GREEN);
        } else {
            System.out.println();
            System.out.println(ANSI_RED + "Тест-кейс провален" + ANSI_RED);
        }
    }
}

