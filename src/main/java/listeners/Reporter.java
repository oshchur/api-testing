package listeners;

import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class Reporter implements IReporter {
    @Override
    public void generateReport(
            List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        String date = DateTimeFormatter.ofPattern("dd_MMMM_yyyy___HH_mm_ss").format(LocalDateTime.now());
        File file = new File("src/logs/suiteReports/" + date + ".txt");
        System.out.println("===============================>>> IReporter <<<===============================================");
        System.out.println(date);
        for (ISuite suite : suites) {
            String suiteName = suite.getName();
            System.out.println("**************** " + suiteName + " ****************");
            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (ISuiteResult sr : suiteResults.values()) {
                ITestContext tc = sr.getTestContext();
                String finalResultString = "*****     " + suiteName + "     *****\n" +
                        getAllTestsNameAndSpeed(tc.getPassedTests().getAllResults(), "Passed:") +
                        getAllTestsName(tc.getFailedTests().getAllResults(), "Failed:") +
                        getAllTestsName(tc.getSkippedTests().getAllResults(), "Skipped:") + "\n";
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(date + "\n");
                    fileWriter.append(finalResultString);
                    fileWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(finalResultString);
            }
            System.out.println("******************************************************");
        }
        System.out.println("===============================================================================================");
    }

    private String getAllTestsNameAndSpeed(Set<ITestResult> results, String name) {
        String output = ">>> " + name + "\n";
        ITestResult[] resultArray = results.toArray(new ITestResult[results.size()]);
        Arrays.sort(resultArray, (a,b) -> a.getTestClass().getName().compareToIgnoreCase(b.getTestClass().getName()));
        for(ITestResult result : resultArray) {
            output += result.getTestClass().getName() + "." +
                    result.getName() + "()" + "\nTime: " + (result.getEndMillis() - result.getStartMillis()) + " mc.\n";
        }
        return output + "----------------------------------------------------\n";
    }

    private String getAllTestsName(Set<ITestResult> results, String name) {
        String output = ">>> " + name + "\n";
        for(ITestResult result : results) {
            output += result.getTestClass().getName() + "." +
                    result.getName() + "\n";
        }
        return output + "----------------------------------------------------\n";
    }
}
