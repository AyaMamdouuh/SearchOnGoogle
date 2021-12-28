package Pages;
import Pages.Result1;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Search extends Base.base
{
    @FindBy(name  ="q")
    WebElement searchTextBox;

    @FindBy(name="btnK")
    WebElement googleSearchBtn;

    public Search(WebDriver driver) {
    }

    public Search() {

    }


    public Result1 clickOnGoogleSearchBtn() throws InterruptedException {
       // googleSearchBtn.click();
        searchTextBox.sendKeys(Keys.ENTER);
        return new Result1();
    }
    public void enterSearchKey(String key)
    {
        searchTextBox.sendKeys(key);
    }
    public String getSearchKey()
    {
        return searchTextBox.getText();
    }

    public WebElement getGoogleSearchBtn()
    {
        return googleSearchBtn;
    }




}
