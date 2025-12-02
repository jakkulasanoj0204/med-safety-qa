package com.medsafety.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ClaimCreationTest {

    private WebDriver driver;

    @BeforeClass
    public void setUpClass() {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void openClaimsPortalHomePage() {
        // TODO: Replace this with your actual application URL later
        String url = "https://example.com";
        driver.get(url);

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
                currentUrl.contains("example.com"),
                "Expected URL to contain 'example.com' but was: " + currentUrl
        );
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
