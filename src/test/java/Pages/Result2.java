package Pages;

import Base.base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Result2 extends base {

    @FindBy(xpath = "//*[@id=\"xjs\"]/table/tbody/tr/td[4]/a")
    WebElement thirdPage;

    @FindBy(xpath = "//h3[@class='LC20lb MBeuO DKV0Md']")
    List<WebElement> res;

    public Result3 clickOnThirdPage()
    {
        thirdPage.click();
        return new Result3();
    }
    public WebElement getThirdPage()
    {
        return thirdPage;
    }
    public List<WebElement> results()
    {
        return res;
    }
    public String getURL()
    {
        return getURL();
    }

}
