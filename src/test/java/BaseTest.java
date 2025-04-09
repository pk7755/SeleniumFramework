package test.java;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;



public class BaseTest {
    public static WebDriver driver;

    private static final String REPORTS_DIR = "reports";
    private static final int MAX_REPORTS = 0;

    @BeforeSuite
    public void setUpSuite() {
        cleanOldReports();
        // Init ExtentReports here...
    }

    private void cleanOldReports() {
        File dir = new File(REPORTS_DIR);

        if (!dir.exists() || !dir.isDirectory()) return;

        // Get list of HTML files sorted by last modified (newest first)
        File[] reportFiles = dir.listFiles((d, name) -> name.endsWith(".html"));
        if (reportFiles == null || reportFiles.length <= MAX_REPORTS) return;

        Arrays.sort(reportFiles, Comparator.comparingLong(File::lastModified).reversed());

        for (int i = MAX_REPORTS; i < reportFiles.length; i++) {
            reportFiles[i].delete();
        }
    }

    @BeforeTest
    public void beforeTestMethod() throws Exception {

    }

    @BeforeMethod
    @Parameters(value = {"browserName", "env"})
    public void beforeMethodMethod(String browserName, @Optional("test") String xmlEnv) {
        String env = System.getProperty("env", xmlEnv); // CLI overrides XML

        // Load the config file based on the given environment
        ConfigReader.load(env);

        setupDriver(browserName);
        driver.manage().window().maximize();

        // Navigate to the environment's base URL
        String baseUrl = ConfigReader.get("base.url");


        driver.get(baseUrl);
        // Then add the cookie
        Cookie cookie = new Cookie("Mellon", "Mellon");
        driver.manage().addCookie(cookie);
        // Refresh the page to apply the cookie
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterTest
    public void afterTestMethod() throws Exception {
    }

    public void setupDriver(String browserName) {
        if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator +"drivers" + File.separator + "chromedriver.exe");
            driver = new ChromeDriver();
        } else if(browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator +"drivers" + File.separator + "geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else if(browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.msedge.driver", System.getProperty("user.dir") + File.separator +"drivers" + File.separator + "msedgedriver.exe");
            driver = new EdgeDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator +"drivers" + File.separator + "chromedriver.exe");
            driver = new ChromeDriver();
        }
    }

}
