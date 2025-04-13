package annotations;

import java.lang.annotation.*;

// All annotations declared here 👇

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RunThisTest {
    String value();
}