package Tests;

import Base.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public class GoogleSearch extends base {

    @Test (priority =1)
    public void UserSearchOnGoogleSearchEngine(Method method) throws InterruptedException, IOException {
        google.search= new Pages.Search();
        JavascriptExecutor j= ((JavascriptExecutor)driver);
        google.search.enterSearchKey(sheet.getRow(1).getCell(2).toString());
        //Assert.assertEquals(google.search.getSearchKey(),sheet.getRow(1).getCell(2).toString());
       System.out.println(google.search.getSearchKey());
        //google.search.enterSearchKey(config_properties.getProperty("SearchKey"));
        google.result1 =google.search.clickOnGoogleSearchBtn();
        j.executeScript("arguments[0].scrollIntoView(true);",google.result1.getSecondPage());
        Assert.assertTrue(google.result1.getSecondPage().isDisplayed());
        google.result2=google.result1.clickOnSecondPage();

        List<WebElement> results2Count= google.result2.results();
        j.executeScript("arguments[0].scrollIntoView(true);",google.result2.getThirdPage());
        Assert.assertTrue(google.result2.getThirdPage().isDisplayed());

        google.result3=google.result2.clickOnThirdPage();
        List<WebElement> results3Count= google.result3.results();
        j.executeScript("arguments[0].scrollIntoView(true);",google.result3.getFourthPage());
        Assert.assertEquals(results2Count,results3Count);
           }

}