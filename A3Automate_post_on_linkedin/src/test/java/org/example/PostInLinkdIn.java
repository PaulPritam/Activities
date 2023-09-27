package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PostInLinkdIn {

    public WebDriver driver;
    public String baseUrl = "https://www.linkedin.com/home";

    @BeforeTest
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void signIn()
    {
        WebElement userName = driver.findElement(By.xpath("//*[@id='session_key']"));
        userName.sendKeys("userEmail");

        WebElement password = driver.findElement(By.xpath("//*[@id='session_password']"));
        password.sendKeys("Password");

        WebElement signIn = driver.findElement(By.xpath("//*[@type='submit']"));
        signIn.click();

//        WebElement password2 = driver.findElement(By.xpath("//*[@id='password']"));

    }

    @Test(priority = 2)
    public void checkNumbers()
    {
        WebElement profileViewers = driver.findElement(By.xpath("(//*[@class='feed-identity-widget-item__" +
                "stat'])[1]"));
        System.out.println("The number of people viewed my profile: " + profileViewers.getText());

        WebElement impressions = driver.findElement(By.xpath("(//*[@class='feed-identity-widget-item__stat" +
                "'])[2]"));
        System.out.println("The number of people viewed my profile: " + impressions.getText());
    }

    @Test(priority = 3)
    public void createPost()
    {
        WebElement startPost = driver.findElement(By.xpath("//*[text() = 'Start a post']"));
        startPost.click();

        WebElement picBtn = driver.findElement(By.xpath("(//*[@class='share-promoted-detour-button__icon" +
                "-container'])[1]"));
        picBtn.click();
    }
    @AfterTest
    public void closeBrowser()
    {
        driver.close();
    }
}
