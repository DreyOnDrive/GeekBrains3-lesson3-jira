import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeTeam {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static boolean makeTeamSuccess = true;

    public static void main(String[] args) throws InterruptedException {
        System.out.println();

        if (!Login.coockiesIsReady) {
            System.out.println(ANSI_RESET + "ТЕСТ-КЕЙС СОЗДАНИЯ КОМАНДЫ не может стартовать без coockies" + ANSI_RESET);
            return;
        }
        if (!Login.loginSuccess) {
            System.out.println(ANSI_RESET + "ТЕСТ-КЕЙС СОЗДАНИЯ КОМАНДЫ не может стартовать без удачной регистрации" + ANSI_RESET);
            return;
        } else {
            System.out.println(ANSI_RESET + "ТЕСТ-КЕЙС СОЗДАНИЯ КОМАНДЫ" + ANSI_RESET);
        }

        WebDriverManager.chromiumdriver().setup();
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();


        try {
            driver.get("https://my-atlassian-site-441.atlassian.net/");
            driver.manage().deleteAllCookies();
            for (Cookie coockie : Login.coockies) {
                driver.manage().addCookie(coockie);
            }
            driver.navigate().to("https://my-atlassian-site-441.atlassian.net/jira/your-work");
            System.out.println(ANSI_GREEN + "Переход в личный кабинет - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Переход в личный кабинет - провал" + ANSI_RED);
            makeTeamSuccess = false;
        }


        try {
            driver.findElement(By.xpath("//span[text()[contains(.,'Люди')]]")).click();
            System.out.println(ANSI_GREEN + "Переход на вкладку - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Переход на вкладку - провал" + ANSI_RED);
            makeTeamSuccess = false;
        }


        Thread.sleep(5000);


        try {
            ((JavascriptExecutor)driver).executeScript("document.querySelectorAll(\"span[data-item-title = 'true']\").item(1).click();");
            System.out.println(ANSI_GREEN + "Создание новой команды - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Создание новой команды - провал" + ANSI_RED);
            makeTeamSuccess = false;
        }


        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"teamName\"]")));
            System.out.println(ANSI_GREEN + "Переход в поп-ап создания команды - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Переход в поп-ап создания команды - провал" + ANSI_RED);
            makeTeamSuccess = false;
        }


        try {
            driver.findElement(By.xpath("//input[@name=\"teamName\"]")).sendKeys("New Team");
            System.out.println(ANSI_GREEN + "Ввод названия команды - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Ввод названия команды команды - провал" + ANSI_RED);
            makeTeamSuccess = false;
        }


        try {
            driver.findElement(By.xpath("//span[text()[contains(.,'Создать команду')]]")).click();
            System.out.println(ANSI_GREEN + "Создание команды - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Создание команды - провал" + ANSI_RED);
            makeTeamSuccess = false;
        }


        Thread.sleep(1000);


        try {
            ((JavascriptExecutor)driver).executeScript("document.querySelector(\"a[href^='/jira/people/team']\").click();");
            System.out.println(ANSI_GREEN + "Переход в новую команду - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Переход в новую команду - провал" + ANSI_RED);
            makeTeamSuccess = false;
        }

        Thread.sleep(3000);


        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-modal-stack='0']")));
            driver.findElement(By.xpath("//span[.='Начать работу']")).click();
            System.out.println(ANSI_GREEN + "Переход в поп-ап начала работы - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Переход в поп-ап начала работы - провал" + ANSI_RED);
            makeTeamSuccess = false;
        }


        Thread.sleep(1000);


        try {
            driver.findElement(By.xpath("//button[@type=\"button\"]//span[@aria-label=\"actions\"]")).click();
            System.out.println(ANSI_GREEN + "Опции команды - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Опции команды - провал" + ANSI_RED);
            makeTeamSuccess = false;
        }


        try {
            driver.findElement(By.xpath("//span[.='Удалить команду']")).click();
            System.out.println(ANSI_GREEN + "Удаление команды - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Удаление команды - провал" + ANSI_RED);
            makeTeamSuccess = false;
        }


        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[.='Это действие нельзя отменить. Действительно удалить команду?']")));
            driver.findElement(By.xpath("//p[.='Это действие нельзя отменить. Действительно удалить команду?']")).click();
            System.out.println(ANSI_GREEN + "Подтверждение удаления - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Подтверждение удаления - провал" + ANSI_RED);
            makeTeamSuccess = false;
        }


        try {
            driver.findElement(By.xpath("//strong[.='Удалить']")).click();
            System.out.println(ANSI_GREEN + "Удалить - успех" + ANSI_GREEN);
        } catch (Exception e) {
            System.out.println(ANSI_RED + "Удалить - провал" + ANSI_RED);
            makeTeamSuccess = false;
        }


        driver.quit();

        if (makeTeamSuccess) {
            System.out.println();
            System.out.println(ANSI_GREEN + "Тест-кейс выполнен успешно" + ANSI_GREEN);
        } else {
            System.out.println();
            System.out.println(ANSI_RED + "Тест-кейс провален" + ANSI_RED);
        }
    }
}