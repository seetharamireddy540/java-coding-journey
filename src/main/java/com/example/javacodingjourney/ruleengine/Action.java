package com.example.javacodingjourney.ruleengine;

@FunctionalInterface
public interface Action<T> {
    void execute(T context);

    // Optional: Chaining actions
    default Action<T> andThen(Action<T> after) {
        return context -> {
            this.execute(context);
            after.execute(context);
        };
    }
}