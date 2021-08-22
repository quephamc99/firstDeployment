package Utils;

import com.aventstack.extentreports.ExtentTest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ExtentReportLogger {
    private static ExtentReportLogger mInstance;

    private ExtentTest mParentTest;
    private ExtentTest mChildTest, childNode;

    /**
     * This constructor must be "private" to make sure that it cannot be initialized
     * anywhere except for the getInstance() method.
     */
    private ExtentReportLogger() {
    }

    /**
     * Get instance of Logger object. (Singleton design pattern).
     *
     * @return an instance of Logger object.
     */
    public static ExtentReportLogger getInstance() {
        if (mInstance == null) {
            mInstance = new ExtentReportLogger();
        }
        return mInstance;
    }

    public ExtentTest getParentTest() {
        return mParentTest;
    }

    public ExtentTest getChildTest() {
        return mChildTest;
    }

    public void setParentTest(ExtentTest test) {
        mParentTest = test;
    }

    public void setChildTest(ExtentTest test) {
        mChildTest = test;
    }

    public  void logDescriptionStep(String text){
        childNode = ExtentReportLogger.getInstance().getParentTest().createNode(text);
        ExtentReportLogger.getInstance().setChildTest(childNode);
    }

    public String getScreenshot(WebDriver driver) throws Exception {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        // after execution, you could see a folder "FailedTestsScreenshots" under src
        // folder
        String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + "errorCapture_" + dateName
                + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }
}
