
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SearchAmazon {


    public WebDriver driver;
    public String baseUrl = "https://www.google.co.in/";

    @BeforeTest
    public void setup() throws InterruptedException {

        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void check() {
        WebElement googleSearchBox = driver.findElement(By.xpath("//*[@id='APjFqb']"));
        googleSearchBox.sendKeys("amazon" + Keys.ENTER);

        WebElement firstDisplayedUrl = driver.findElement(By.xpath("(//*[@class = 'OSrXXb VN4UC chcHdb'])[1]"));

        String actualText = firstDisplayedUrl.getText();
        String expectedText = "https://www.amazon.in";

        Assert.assertEquals(expectedText, actualText);
    }

    @AfterTest
    public void closeBrowser()
    {
        driver.close();
    }
}
