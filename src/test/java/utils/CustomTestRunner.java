package utils;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.Collections;

public class CustomTestRunner {

    public static void main(String[] args) {
        // Get the class and method name from system properties
        String className = System.getProperty("testClass");
        String methodName = System.getProperty("testMethod");

        // Create TestNG instance
        TestNG testng = new TestNG();

        // Create the XML Suite dynamically
        XmlSuite suite = new XmlSuite();
        suite.setName("DynamicSuite");

        // Create the XML Test and set its name
        XmlTest test = new XmlTest(suite);
        test.setName("DynamicTest");

        // Create the XmlClass based on class name passed via System property
        XmlClass testClass = new XmlClass(className);

        // If method name is provided, include it in the test class
        if (methodName != null) {
            XmlInclude method = new XmlInclude(methodName);
            testClass.setIncludedMethods(Collections.singletonList(method));
        }

        // Add the class to the test
        test.setXmlClasses(Collections.singletonList(testClass));

        // Set the suite and run the tests
        testng.setXmlSuites(Collections.singletonList(suite));
        testng.run();
    }
}




