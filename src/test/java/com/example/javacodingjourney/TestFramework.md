
1. Test Annotation:
```java src/main/java/testframework/Test.java
package testframework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RamTest {
    // Optional parameters for test configuration
    boolean enabled() default true;
    String description() default "";
    Class<? extends Throwable> expectedException() default None.class;

    // Marker class for no expected exception
    class None extends Throwable {
        private None() {}
    }
}
```

2. Assertion Utility:
```java src/main/java/testframework/Assert.java
package testframework;

public class Assert {
    public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Condition was false");
        }
    }

    public static void assertFalse(boolean condition) {
        if (condition) {
            throw new AssertionError("Condition was true");
        }
    }

    public static void assertEquals(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected: " + expected + ", but was: " + actual);
        }
    }

    public static void assertNotNull(Object obj) {
        if (obj == null) {
            throw new AssertionError("Object was null");
        }
    }

    public static void fail(String message) {
        throw new AssertionError(message);
    }
}
```

3. Test Runner:
```java src/main/java/testframework/TestRunner.java
package testframework;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    public static void run(Class<?> testClass) {
        System.out.println("Running tests for: " + testClass.getName());
        
        int totalTests = 0;
        int passedTests = 0;
        int failedTests = 0;
        
        List<String> failureMessages = new ArrayList<>();
        
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                Test testAnnotation = method.getAnnotation(Test.class);
                
                if (!testAnnotation.enabled()) {
                    continue;
                }
                
                totalTests++;
                try {
                    method.setAccessible(true);
                    method.invoke(testClass.getDeclaredConstructor().newInstance());
                    passedTests++;
                    System.out.println("✓ " + method.getName() + " passed");
                } catch (Exception e) {
                    Throwable cause = e.getCause();
                    
                    // Handle expected exceptions
                    if (testAnnotation.expectedException() != Test.None.class) {
                        if (cause != null && cause.getClass().equals(testAnnotation.expectedException())) {
                            passedTests++;
                            System.out.println("✓ " + method.getName() + " passed (expected exception)");
                            continue;
                        }
                    }
                    
                    failedTests++;
                    String failureMessage = "✗ " + method.getName() + " failed: " + 
                                            (cause != null ? cause.getMessage() : e.getMessage());
                    System.out.println(failureMessage);
                    failureMessages.add(failureMessage);
                }
            }
        }
        
        // Print summary
        System.out.println("\nTest Summary:");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed Tests: " + passedTests);
        System.out.println("Failed Tests: " + failedTests);
        
        if (!failureMessages.isEmpty()) {
            System.out.println("\nFailure Details:");
            for (String message : failureMessages) {
                System.out.println(message);
            }
            System.exit(1);
        }
    }
}
```

4. Example Usage:
```java src/main/java/testframework/example/MathTest.java
package testframework.example;

import testframework.Test;
import testframework.Assert;
import testframework.TestRunner;

public class MathTest {
    @Test
    public void testAddition() {
        int result = 2 + 2;
        Assert.assertEquals(4, result);
    }
    
    @Test(expectedException = ArithmeticException.class)
    public void testDivideByZero() {
        int result = 10 / 0;  // This will throw ArithmeticException
    }
    
    @Test(enabled = false)
    public void skippedTest() {
        // This test will be skipped
    }
    
    public static void main(String[] args) {
        TestRunner.run(MathTest.class);
    }
}
```

This custom test framework provides several key features:
- Custom `@Test` annotation
- Flexible test configuration (enable/disable, expected exceptions)
- Basic assertion methods
- Test runner with execution summary
- Error handling and reporting

Key characteristics:
- Annotation-based test discovery
- Simple assertions
- Test execution tracking
- Support for expected exceptions
- Ability to skip tests

How to use:
1. Create a test class with methods annotated with `@Test`
2. Use `Assert` methods for validations
3. Call `TestRunner.run(YourTestClass.class)` to execute tests

Potential improvements you might consider:
- Add `@Before` and `@After` annotations
- Implement more advanced assertion methods
- Add support for test timeouts
- Create more detailed reporting

Would you like me to elaborate on any part of the framework or discuss potential enhancements?