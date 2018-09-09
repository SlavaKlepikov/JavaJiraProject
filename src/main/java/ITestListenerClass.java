import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class ITestListenerClass implements ITestListener {
    private static final Logger LOGGER = LogManager.getLogger(ITestListener.class.getName());

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LOGGER.info("onTestStart: " +  iTestResult.getName() + " start" );
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LOGGER.info("onTestSuccess: " +  iTestResult.getName() + " succeed" );
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LOGGER.info("onTestFailure: " +  iTestResult.getName() + " failed" );
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LOGGER.info("onTestSkipped: "+  iTestResult.getName() + " skipped" );
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        LOGGER.info("onTestFailedButWithinSuccessPercentage: " + iTestResult.getName() );
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        LOGGER.info("onStart: " + iTestContext.getName() );
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LOGGER.info("onFinish: " + iTestContext.getName() );
    }

}
