import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ImdbAutomation {

    public WebDriver driver;
    public String baseUrl = "https://www.imdb.com/chart/top/";

    @BeforeTest
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void checkMovies()
    {
        List<WebElement> highestRating = driver.findElements(By.xpath("//*[starts-with(@class , " +
                "'sc-4dcdad14-0')]"));

        System.out.println("The highest rated movie is: " + highestRating.get(0).getText());
        System.out.println("The number of movies included in the table is: " + highestRating.size());


    }

    @Test(priority = 2)
    public void checkPremiere() throws InterruptedException {
        List<WebElement> year = driver.findElements(By.xpath("//*[starts-with(@class , 'ipc-title " +
                "ipc-title--base ipc-title--title ipc-title-l')]//following-sibling::div//child::span[1]"));

        List<WebElement> movies = driver.findElements(By.xpath("//*[starts-with(@class , " +
                "'sc-4dcdad14-0')]"));

        String oldestMovie = "";
        int oldestYear = 2023;
        for (int i = 0; i < year.size(); i++) {
            int value = Integer.valueOf(year.get(i).getText());
            if (value < oldestYear)
            {
                oldestYear = value;
                oldestMovie = movies.get(i).getText();
            }
        }
        System.out.println("Oldest Movie: " + oldestMovie);

        int recentYear = 2023;
        for (int i = 0; i < year.size(); i++) {
            int value = Integer.valueOf(year.get(i).getText());
            if (value == recentYear)
            {
                oldestMovie = movies.get(i).getText();
            }
        }
        System.out.println("Recent Movie: " + oldestMovie);
    }

    @AfterTest
    public void closeBrowser()
    {
        driver.quit();
    }
}
