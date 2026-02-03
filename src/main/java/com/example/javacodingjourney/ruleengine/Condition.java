package com.example.javacodingjourney.ruleengine;


@FunctionalInterface
public interface Condition<T> {
    boolean evaluate(T context);

    // And condition
    default Condition<T> and(Condition<T> other) {
        return context -> this.evaluate(context) && other.evaluate(context);
    }

    // Or condition
    default Condition<T> or(Condition<T> other) {
        return context -> this.evaluate(context) || other.evaluate(context);
    }

    // Negation (Not) condition
    default Condition<T> not() {
        return context -> !this.evaluate(context);
    }

    // Static factory methods for complex conditions
    static <T> Condition<T> allOf(Condition<T>... conditions) {
        return context -> {
            for (Condition<T> condition : conditions) {
                if (!condition.evaluate(context)) {
                    return false;
                }
            }
            return true;
        };
    }

    static <T> Condition<T> anyOf(Condition<T>... conditions) {
        return context -> {
            for (Condition<T> condition : conditions) {
                if (condition.evaluate(context)) {
                    return true;
                }
            }
            return false;
        };
    }
}