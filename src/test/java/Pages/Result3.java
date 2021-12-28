package Pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Result3 extends Base.base{
    @FindBy(xpath = "//*[@id=\"xjs\"]/table/tbody/tr/td[5]/a")
    WebElement thirdPage;

    @FindBy(xpath = "//h3[@class='LC20lb MBeuO DKV0Md']")
    List<WebElement> res;


    public WebElement getFourthPage()
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
