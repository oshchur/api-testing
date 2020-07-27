package listeners;

import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Listener implements ITestListener {
    private ByteArrayOutputStream response = new ByteArrayOutputStream();
    private PrintStream responseVar = new PrintStream(response, true);
    Logger logger;

    public void onStart(ITestContext context) {
        RestAssured.filters(new ResponseLoggingFilter(LogDetail.BODY, responseVar));
        logger = LoggerFactory.getLogger(context.getName());
    }

    public void onTestSuccess(ITestResult iTestResult) {
        String testName = "thread_" + Thread.currentThread().getId() + "_" + iTestResult.getName() + iTestResult.getStartMillis();
        MDC.put("testName", testName);

        logger.debug(iTestResult.getName() + ":\n" + response.toString());
        attachLogResponse(response);
    }

    public void onTestFailure(ITestResult iTestResult) {
        onTestSuccess(iTestResult);
    }

    @Attachment(value = "response", type = "text/plain")
    public byte[] attachLogResponse(ByteArrayOutputStream stream) {
        byte[] bytes = stream.toByteArray();
        stream.reset();

        return bytes;
    }
}
