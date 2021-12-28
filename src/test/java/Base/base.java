package Base;

import Pages.Google;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.commons.math3.stat.inference.TestUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class base {
    public static WebDriver driver;
    public  static Properties config_properties =new Properties();
    public Google google;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public static ExtentReports extent;
    public static ExtentTest logger;
    public base() {
         PageFactory.initElements(driver,this);
         google=new Google();

        try
        {
            File file=new File("src/main/resources/data.xlsx");
            FileInputStream fis =new FileInputStream(new File("src/main/resources/config.properties"));
            config_properties.load(fis);
            FileInputStream fis2 =new FileInputStream(file);

            workbook =new XSSFWorkbook(fis2);
            sheet= workbook.getSheetAt(0);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @BeforeMethod
    public void  launchBrowser(Method method) {
        if(config_properties.getProperty("browser_type").equals("chrome"))
        //if(sheet.getRow(1).getCell(0).toString().equals("chrome"))
        {
            WebDriverManager.chromedriver().setup();
           // System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +config_properties.getProperty("driver_Path"));
            driver = new ChromeDriver();
        }
       //else if(sheet.getRow(1).getCell(0).toString().equals("firefox"))
        else if (config_properties.getProperty("browser_type").equalsIgnoreCase("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();

            //System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\java\\driverBinaries\\chromedriver.exe");
            driver=new FirefoxDriver();
        }
        //else if(sheet.getRow(1).getCell(0).toString().equals("edge"))
        else if(config_properties.getProperty("browser_type").equalsIgnoreCase("edge"))
        {
            //System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") +config_properties.getProperty("driver_Path"));
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        }
//        WebDriverManager.chromedriver().setup();
//        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        //driver.navigate().to("https://www.google.com");
        //driver.get(config_properties.getProperty("url"));
        driver.get(sheet.getRow(1).getCell(1).toString());
        logger=extent.startTest(method.getName());
    }

    @AfterMethod
    public void getStatus(ITestResult result) throws IOException {
       // TestUtils.takePicture(method.getName());
        if(result.getStatus()==ITestResult.SUCCESS)
        {
            logger.log(LogStatus.PASS,"Test Pass!");
            takePic(result.getName());
        }
        else if(result.getStatus()==ITestResult.FAILURE)
        {
            logger.log(LogStatus.FAIL,result.getThrowable());
            takePic(result.getName());

        }
        else
        {
            logger.log(LogStatus.SKIP,"Test Skip");
        }
    }
    @AfterMethod
    public void terminateBrowsser()
    {
        driver.quit();

    }
    @BeforeSuite
    public void createReport()
    {
        extent =new ExtentReports("src/main/reports/index.html",true);
        extent.addSystemInfo("browser type",config_properties.getProperty("browser_type"));
    }
    @AfterSuite
    public void end()
    {
        extent.flush();
    }

    public static void takePic(String methodName) throws IOException {
        File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile,new File("src/main/reports/snapshot/"+methodName+".png"));
    }
}
