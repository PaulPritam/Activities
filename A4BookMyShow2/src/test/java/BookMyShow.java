import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BookMyShow {
    public WebDriver driver;
    public String baseUrl = "https://in.bookmyshow.com/explore/home/chennai";

    @BeforeTest
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void checkImageUrls()
    {
        List<WebElement> imageUrl = driver.findElements(By.xpath("//*[starts-with(@class, 'style__Widge" +
                "tContainerBody-sc-l')]//child::img"));

        for (WebElement imgElement : imageUrl) {
            String srcAttribute = imgElement.getAttribute("src");
            if (srcAttribute != null && srcAttribute.startsWith("https://")) {
                System.out.println("Image URLs present: " + srcAttribute);
            }
        }
    }

    @Test
    public void checkPremiere() throws InterruptedException {
        Thread.sleep(15000);
        List<WebElement> name = driver.findElements(By.xpath("//*[starts-with(@class , 'sc-7o7nez-0 z')]"));
        System.out.println("The premiere names are: " + name.size());
        for (WebElement text: name ) {
            System.out.println(text.getText());
        }

        List<WebElement> language = driver.findElements(By.xpath("//*[starts-with(@class , 'sc-7o7nez-0 " +
                "veMGd')]"));

        System.out.println("The language of the above shows: " + language.size());
        for (WebElement text: language ) {
            System.out.println(text.getText());
        }
    }

    @AfterTest
    public void closeBrowser()
    {
        driver.quit();
    }

}
