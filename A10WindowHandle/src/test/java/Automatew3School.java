import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Automatew3School {
    public static WebDriver driver;
    public String baseUrl = "https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open";

    @BeforeTest
    public void setup() throws InterruptedException {

        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public static void captureScreenshot() {
        try {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
            File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE);
            String filePath = "C:\\Users\\user\\IdeaProjects\\A10WindowHandle\\screenshots\\screenshots.png";

            FileUtils.copyFile(screenshotFile, new File(filePath));
        } catch (Exception e) {
            System.err.println("Error... " + e.getMessage());
        }
    }

    @Test
    public void check() throws InterruptedException {
        WebElement frame1 = driver.findElement(By.xpath("//*[@id='iframeResult']"));
        driver.switchTo().frame(frame1);

        WebElement tryItBtn = driver.findElement(By.xpath("//button[text() = 'Try it']"));
        tryItBtn.click();
//        driver.switchTo().parentFrame();
        Thread.sleep(10000);
        String parentWindowHandle = driver.getWindowHandle();

        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(windowHandle);

                System.out.println(driver.getCurrentUrl());

                captureScreenshot();
                driver.close();
                driver.switchTo().window(parentWindowHandle);

                break;
            }
        }
    }

    @AfterTest
    public void closeBrowser() {
        driver.close();
    }

}
