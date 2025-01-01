package com.example.javacodingjourney.util;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LambdaUtils {

    public static void main(String[] args) {
        List<Integer> number = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        number.stream().filter(n -> n % 2 == 0).forEach(System.out::println);

        List<Employee> employees = List.of(
                Employee.builder().name("John").age(30).salary(1000).id(1L).build(),
                Employee.builder().name("Jane").age(25).salary(2000).id(2L).build(),
                Employee.builder().name("Jane").age(34).salary(2000).id(2L).build(),
                Employee.builder().name("Jack").age(20).salary(3000).id(3L).build(),
                Employee.builder().name("Jack").age(20).salary(33000).id(3L).build()
        );

        Map<Long, List<Employee>> map = employees.stream().collect(Collectors.groupingBy(Employee::getId));
        map.forEach((key, value) -> System.out.println(key + " " + value));
        List<? extends Number> numbers = new ArrayList<>();

    }

    public static <T> void copy(List<? super T> dest,    // Consumer
                                List<? extends T> src)      // Producer
    {
        for (T element : src) {
            dest.add(element);
        }
    }

    @Builder
    @Getter
    public static class Employee {
        private String name;
        private int age;
        private double salary;
        private Long id;

        @Override
        public String toString() {
            return "Employee{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    ", salary=" + salary +
                    ", id=" + id +
                    '}';
        }
    }

}
