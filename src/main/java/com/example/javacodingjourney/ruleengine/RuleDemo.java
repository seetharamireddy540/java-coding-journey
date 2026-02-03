package com.example.javacodingjourney.ruleengine;

public class RuleDemo {
    public static void main(String[] args) {
        // Fluent Rule Creation
        Rule<Person> adultRule = Rule.<Person>builder()
                .name("Adult Rule")
                .condition(person -> person.getAge() >= 18)
                .action(person -> System.out.println(person.getName() + " is an adult"))
                .priority(1)
                .build();

        // Chained Conditions
        Condition<Person> isAdult = person -> person.getAge() >= 18;
        Condition<Person> isHighIncome = person -> person.getIncome() > 50000;
        Condition<Person> complexCondition = isAdult.and(isHighIncome);

        // Rule with Complex Condition
        Rule<Person> complexRule = new Rule<>(
                "Complex Adult High Income Rule",
                complexCondition,
                person -> System.out.println("Qualified person: " + person.getName())
        );
    }
}