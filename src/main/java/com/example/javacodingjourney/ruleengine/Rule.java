package com.example.javacodingjourney.ruleengine;


import java.util.UUID;

public class Rule<T> implements Comparable<Rule<T>> {
    // Unique identifier for the rule
    private final String id;

    // Rule name for identification
    private final String name;

    // Condition to evaluate
    private final Condition<T> condition;

    // Action to execute when condition is met
    private final Action<T> action;

    // Priority for rule execution
    private int priority;

    // Metadata and additional properties
    private RuleMetadata metadata;

    // Rule builder for fluent rule creation
    public static class Builder<T> {
        private String name = "Unnamed Rule";
        private Condition<T> condition;
        private Action<T> action;
        private int priority = 0;

        public Builder<T> name(String name) {
            this.name = name;
            return this;
        }

        public Builder<T> condition(Condition<T> condition) {
            this.condition = condition;
            return this;
        }

        public Builder<T> action(Action<T> action) {
            this.action = action;
            return this;
        }

        public Builder<T> priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Rule<T> build() {
            return new Rule<>(name, condition, action, priority);
        }
    }

    // Inner class for rule metadata
    public static class RuleMetadata {
        private String description;
        private boolean enabled = true;
        private long createdAt;

        public RuleMetadata() {
            this.createdAt = System.currentTimeMillis();
        }

        // Getters and setters
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled; }
        public long getCreatedAt() { return createdAt; }
    }

    // Constructors
    public Rule(String name, Condition<T> condition, Action<T> action) {
        this(name, condition, action, 0);
    }

    public Rule(String name, Condition<T> condition, Action<T> action, int priority) {
        // Validate inputs
        if (name == null || condition == null || action == null) {
            throw new IllegalArgumentException("Rule must have a name, condition, and action");
        }

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.condition = condition;
        this.action = action;
        this.priority = priority;
        this.metadata = new RuleMetadata();
    }

    // Core Rule Methods
    public boolean evaluate(T context) {
        // Check if rule is enabled before evaluation
        return metadata.isEnabled() && condition.evaluate(context);
    }

    public void execute(T context) {
        // Only execute if rule is enabled
        if (metadata.isEnabled()) {
            action.execute(context);
        }
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
    public RuleMetadata getMetadata() { return metadata; }

    // Comparable implementation for sorting
    @Override
    public int compareTo(Rule<T> other) {
        // Higher priority rules come first
        return Integer.compare(other.priority, this.priority);
    }

    // Fluent builder method
    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    // toString for logging and debugging
    @Override
    public String toString() {
        return "Rule{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", priority=" + priority +
                ", enabled=" + metadata.isEnabled() +
                '}';
    }

    // Equality based on ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule<?> rule = (Rule<?>) o;
        return id.equals(rule.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}