package lab9.Pages;

import lab9.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StackOverflowHomepage {

    public WebDriver driver;

    private static By LogoXpath = By.xpath("//*[@class=\"s-topbar--logo\"]");
    private static By LogoCss = By.cssSelector("body > header > div > a.s-topbar--logo");
    private static By SearchXpath = By.xpath("//*[@class=\"js-search-field\"]");
    private static By SearchCss = By.cssSelector("#search > div > input");

    public StackOverflowHomepage(){
        this.driver = BrowserFactory.createDriver();
    }

    public void open(){
        driver.get("https://stackoverflow.com");
    }
}
