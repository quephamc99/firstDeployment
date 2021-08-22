import Utils.BaseClass;
import Utils.ExtentReportLogger;
import com.aventstack.extentreports.Status;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({BaseClass.class})
public class test extends BaseClass {

    @Test
    public void test1(){
        ExtentReportLogger.getInstance().logDescriptionStep("Running Test 1!");
        ExtentReportLogger.getInstance().getChildTest().log(Status.INFO, "The legal pension is 1234567 euro");
    }

    @Test
    public void test2(){
        ExtentReportLogger.getInstance().logDescriptionStep("Running Test 2!");
        ExtentReportLogger.getInstance().getChildTest().log(Status.INFO, "The legal pension is 9876543210 euro");
    }
}
