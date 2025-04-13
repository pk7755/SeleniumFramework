package listener;

import com.epam.reportportal.service.Launch;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.utils.properties.ListenerProperty;
import com.epam.ta.reportportal.ws.model.launch.Mode;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;
import org.testng.ITestContext;

import java.util.Date;

public class CustomReportPortalListener extends ReportPortalTestNGListener {

    private static Launch launch;

    @Override
    public void onStart(ITestContext testContext) {
        String className = testContext.getAllTestMethods()[0].getInstance().getClass().getSimpleName();
        String dynamicLaunchName = "Launch_" + className;

        System.setProperty(ListenerProperty.LAUNCH_NAME.getPropertyName(), dynamicLaunchName);
        System.out.println(">>> ReportPortal Launch Name set to: " + dynamicLaunchName);

        super.onStart(testContext);
    }
}
