package listeners;

import io.qameta.allure.Attachment;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.*;
import java.nio.file.Files;

public class Listener implements ITestListener {
    private ByteArrayOutputStream response = new ByteArrayOutputStream();
    private PrintStream responseVar = new PrintStream(response, true);

    @Override
    public void onStart(ITestContext context) {
        RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL, responseVar));
    }

    public void onTestSuccess(ITestResult iTestResult) {
        File file = new File("src\\logs\\" + iTestResult.getName() + ".txt");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            response.writeTo(fileOutputStream);
            response.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }

        logResponse(file);
    }

    public void onTestFailure(ITestResult iTestResult) {
        onTestSuccess(iTestResult);
    }

    @Attachment(value = "response", type = "text/plain")
    public byte[] logResponse(File file) {
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());

            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }
}
