package com.example.javacodingjourney.ruleengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RuleEngine<T> {
    private static final Logger LOGGER = Logger.getLogger(RuleEngine.class.getName());

    // Core rule storage
    private final List<Rule<T>> rules;

    // Execution modes
    private ExecutionMode executionMode = ExecutionMode.FIRST_MATCH;

    // Execution listeners
    private List<RuleEngineListener<T>> listeners = new ArrayList<>();

    // Enum for execution strategies
    public enum ExecutionMode {
        FIRST_MATCH,     // Stop after first matching rule
        ALL_MATCHES,     // Execute all matching rules
        PRIORITY_MATCH   // Execute highest priority matching rule
    }

    // Listener interface for rule execution events
    public interface RuleEngineListener<T> {
        void onRuleEvaluated(Rule<T> rule, T context, boolean matched);
        void onRuleExecuted(Rule<T> rule, T context);
    }

    // Constructors
    public RuleEngine() {
        this.rules = new ArrayList<>();
    }

    public RuleEngine(ExecutionMode mode) {
        this.rules = new ArrayList<>();
        this.executionMode = mode;
    }

    // Rule Management Methods
    public void addRule(Rule<T> rule) {
        rules.add(rule);
        // Sort rules by priority
        Collections.sort(rules);
    }

    public void removeRule(Rule<T> rule) {
        rules.remove(rule);
    }

    public void clearRules() {
        rules.clear();
    }

    // Rule Execution Methods
    public void executeRules(T context) {
        // Notify listeners about rule engine start
        notifyRuleEngineStart(context);

        // Execute rules based on mode
        switch (executionMode) {
            case FIRST_MATCH:
                executeFirstMatch(context);
                break;
            case ALL_MATCHES:
                executeAllMatches(context);
                break;
            case PRIORITY_MATCH:
                executePriorityMatch(context);
                break;
        }

        // Notify listeners about rule engine completion
        notifyRuleEngineCompletion(context);
    }

    // Execution Strategies
    private void executeFirstMatch(T context) {
        for (Rule<T> rule : rules) {
            try {
                boolean matched = rule.evaluate(context);
                notifyRuleEvaluated(rule, context, matched);

                if (matched) {
                    rule.execute(context);
                    notifyRuleExecuted(rule, context);
                    break; // Stop after first match
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error executing rule", e);
            }
        }
    }

    private void executeAllMatches(T context) {
        for (Rule<T> rule : rules) {
            try {
                boolean matched = rule.evaluate(context);
                notifyRuleEvaluated(rule, context, matched);

                if (matched) {
                    rule.execute(context);
                    notifyRuleExecuted(rule, context);
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error executing rule", e);
            }
        }
    }

    private void executePriorityMatch(T context) {
        Optional<Rule<T>> matchedRule = rules.stream()
                .filter(rule -> rule.evaluate(context))
                .findFirst();

        matchedRule.ifPresent(rule -> {
            notifyRuleEvaluated(rule, context, true);
            rule.execute(context);
            notifyRuleExecuted(rule, context);
        });
    }

    // Listener Management
    public void addListener(RuleEngineListener<T> listener) {
        listeners.add(listener);
    }

    public void removeListener(RuleEngineListener<T> listener) {
        listeners.remove(listener);
    }

    // Notification Methods
    private void notifyRuleEvaluated(Rule<T> rule, T context, boolean matched) {
        listeners.forEach(listener ->
                listener.onRuleEvaluated(rule, context, matched)
        );
    }

    private void notifyRuleExecuted(Rule<T> rule, T context) {
        listeners.forEach(listener ->
                listener.onRuleExecuted(rule, context)
        );
    }

    private void notifyRuleEngineStart(T context) {
        // Optional: Implement start notification logic
    }

    private void notifyRuleEngineCompletion(T context) {
        // Optional: Implement completion notification logic
    }

    // Utility Methods
    public List<Rule<T>> getRules() {
        return new ArrayList<>(rules);
    }

    public List<Rule<T>> getMatchingRules(T context) {
        return rules.stream()
                .filter(rule -> rule.evaluate(context))
                .collect(Collectors.toList());
    }

    // Setter for Execution Mode
    public void setExecutionMode(ExecutionMode mode) {
        this.executionMode = mode;
    }
}