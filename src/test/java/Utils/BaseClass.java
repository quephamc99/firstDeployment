package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrdererContext;
import org.junit.jupiter.api.extension.*;

public class BaseClass implements BeforeAllCallback, BeforeTestExecutionCallback, AfterAllCallback, AfterTestExecutionCallback {
    static ExtentReports reports;
    static ExtentTest test;

    ExtentTest parentTest, childNode;

    @Override
    //@BeforeAll
    //public static void beforeAll(ExtensionContext context) throws Exception {
    public void beforeAll(ExtensionContext context) throws Exception {
        String filename = System.getProperty("user.dir") + "/extentReport/" + "test_Results.html";
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filename);
        reports = new ExtentReports();
        String css = ".r-img {width: 40%;}";
        htmlReporter.config().setCSS(css);
        reports.attachReporter(htmlReporter);

    }

    @Override
    //@BeforeEach
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        test = reports.createTest(context.getDisplayName());
        // Code for parent/child report
        test.log(Status.INFO, context.getDisplayName() + " - started");
        ExtentReportLogger.getInstance().setParentTest(test);
    }

    @Override
    //@AfterEach
    public void afterTestExecution(ExtensionContext context) throws Exception {
        if (!context.getExecutionException().isPresent()) {
            test.pass(context.getDisplayName() + " - ended");
        } else {
            //test.pass(context.getDisplayName() + " - failed");
            test.fail(context.getExecutionException().get().getLocalizedMessage());
            //test.addScreenCaptureFromPath(((ElementShould) context.getExecutionException().get()).getScreenshot());
        }
    }

    @Override
    //@AfterAll
    public void afterAll(ExtensionContext context) throws Exception {
        reports.flush();
    }
}
