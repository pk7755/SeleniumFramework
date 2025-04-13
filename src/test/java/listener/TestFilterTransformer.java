package listener;

import annotations.RunThisTest;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestFilterTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        String runAnnotation = System.getProperty("runAnnotation");
        System.out.println(">>> TestFilterTransformer loaded");

        if (runAnnotation != null) {
            System.out.println(">>>> runAnnotation = " + runAnnotation);
            System.out.println(">>>> Filtering " + testMethod.getName());

            if (testMethod.isAnnotationPresent(RunThisTest.class)) {
                RunThisTest customAnnotation = testMethod.getAnnotation(RunThisTest.class);
                System.out.println(">>>> Found " + testMethod.getName() + " with value " + customAnnotation.value());

                if (!customAnnotation.value().equalsIgnoreCase(runAnnotation)) {
                    System.out.println(">>>> Disabling " + testMethod.getName());
                    annotation.setEnabled(false);
                }
            } else {
                System.out.println(">>>> Disabling (no annotation) " + testMethod.getName());
                annotation.setEnabled(false);
            }
        }
    }
}
