package main.java.org.example.listener;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import main.java.org.example.utils.VideoRecorder;
import org.testng.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VideoTestListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private static String videoFileName;

    @Override
    public void onStart(ITestContext context) {
        String reportName = "TestReport_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".html";
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("./reports/" + reportName);
        htmlReporter.config().setDocumentTitle("Automation Test Report");
        htmlReporter.config().setReportName("Execution Results");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Environment", "Test");
        extent.setSystemInfo("Tester", "Your Name");
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTest test = extent.createTest(testName);
        testThread.set(test);

        try {
            videoFileName = testName + "-" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            VideoRecorder.startRecording(videoFileName);
        } catch (Exception e) {
            test.fail("❌ Failed to start video recording: " + e.getMessage());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().pass("✅ Test Passed");
        stopVideo(result, true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testThread.get().fail("❌ Test Failed: " + result.getThrowable());
        stopVideo(result, true);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().skip("⚠️ Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    private void stopVideo(ITestResult result, boolean attachVideo) {
        try {
            VideoRecorder.stopRecording();

            if (attachVideo) {
                String videoPath = "../recordings/" + videoFileName + ".avi";
                File f = new File(videoPath);
                if (f.exists()) {
                    testThread.get().info("<a href='" + videoPath + "' target='_blank'>🎥 Click to view recording</a>");
                    // Or embed directly (optional, may not work with AVI)
//                     testThread.get().info("<video width='600' controls><source src='" + videoPath + "' type='video/avi'></video>");
                }
            }

        } catch (Exception e) {
            testThread.get().warning("⚠️ Failed to stop recording: " + e.getMessage());
        }
    }
}
