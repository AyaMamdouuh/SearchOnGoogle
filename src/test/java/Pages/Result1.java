package Pages;

import Base.base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.FindBy;


public class Result1 extends base {

    @FindBy(xpath = "//*[@id='xjs']/table/tbody/tr/td[3]")
    WebElement SecondPage;

    public Result2 clickOnSecondPage()
    {
        SecondPage.click();
        return new Result2();
    }
    public WebElement getSecondPage()
    {
        return SecondPage;
    }

    public String getURL()
    {
        return getURL();
    }
}
