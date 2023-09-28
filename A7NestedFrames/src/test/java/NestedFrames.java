import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NestedFrames {

    public WebDriver driver;
    public String baseUrl = "https://the-internet.herokuapp.com/nested_frames";

    @BeforeTest
    public void setup() throws InterruptedException {

        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void check() throws InterruptedException {
//        WebElement frameTop = driver.findElement(By.xpath("//*[@name='frame-top']"));
//        WebElement frameMiddle = driver.findElement(By.xpath("//*[@name='frameset-middle']"));
//        WebElement frameLeft = driver.findElement(By.name("frame-left"));
        List<WebElement> frames = driver.findElements(By.tagName("frame"));
        System.out.println(frames.size());

        Thread.sleep(5000);
        driver.switchTo().frame(0);
        List<WebElement> frames1 = driver.findElements(By.tagName("frame"));
        System.out.println(frames1.size());

        driver.switchTo().frame(1);


        Thread.sleep(15000);
        List<WebElement> text = driver.findElements(By.xpath("//*[contains(@src , '/frame_')]"));
        for (WebElement e: text ) {
            System.out.println(e.getText());
        }

    }

    @AfterTest
    public void closeBrowser()
    {
        driver.close();
    }
}