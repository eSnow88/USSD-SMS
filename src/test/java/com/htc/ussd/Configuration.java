package  com.htc.ussd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Configuration{
    static WebDriver driver;
    public void Driver(){
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://127.0.0.1:5500/web/");
    }


    public void closeNavigation(){
        driver.close();
    }
}