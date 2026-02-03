package com.example.javacodingjourney.ruleengine;


public class RuleEngineDemo {
    public static void main(String[] args) {
        RuleEngine<Person> ruleEngine = new RuleEngine<>();

        // Complex Conditions
        Condition<Person> isAdult = person -> person.getAge() >= 18;
        Condition<Person> isHighIncome = person -> person.getIncome() > 50000;
        Condition<Person> isMale = person -> person.getGender().equals("Male");

        // Combining conditions
        Condition<Person> adultHighIncomeCondition = isAdult.and(isHighIncome);

        // Or condition
        Condition<Person> adultOrHighIncomeCondition = isAdult.or(isHighIncome);

        // Negation condition
        Condition<Person> notMaleCondition = isMale.not();

        // Multiple condition combination
        Condition<Person> complexCondition = Condition.allOf(
                isAdult,
                isHighIncome,
                person -> person.getName().length() > 5
        );

        // Or condition with multiple checks
        Condition<Person> multiOrCondition = Condition.anyOf(
                isAdult,
                isHighIncome,
                person -> person.getAge() < 25
        );

        // Rules with complex conditions
        Rule<Person> advancedRule = new Rule<>(
                "Advanced Complex Rule",
                complexCondition,
                person -> System.out.println("Complex rule matched: " + person.getName())
        );

        // Add rules to engine
        ruleEngine.addRule(advancedRule);

        // Example persons
        Person person1 = new Person("John Doe", 30, 60000, "Male");
        Person person2 = new Person("Jane Smith", 25, 45000, "Female");

        // Execute rules
        ruleEngine.executeRules(person1);
        ruleEngine.executeRules(person2);
    }
}