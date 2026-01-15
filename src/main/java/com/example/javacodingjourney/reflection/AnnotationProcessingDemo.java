package com.example.javacodingjourney.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface Test {
    String value() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RamTest {
    String value() default "";
    // Optional parameters for test configuration
    boolean enabled() default true;
    String description() default "";
    Class<? extends Throwable> expectedException() default None.class;

    // Marker class for no expected exception
    class None extends Throwable {
        private None() {}
    }
}

public class AnnotationProcessingDemo {
    public void processAnnotations() {
        Class<?> testClass = MyTest.class;

        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                Test testAnnotation = method.getAnnotation(Test.class);
                System.out.println("Method: " + method.getName() +
                        ", Annotation Value: " + testAnnotation.value());
            }
        }
    }
}