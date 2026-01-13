package com.example.javacodingjourney.testing;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import java.util.*;
import java.util.stream.Stream;

/**
 * Comprehensive Testing Strategies Guide
 */
public class TestingStrategiesExamples {

    // ============================================
    // 1. UNIT TESTING - Test individual methods
    // ============================================
    
    static class Calculator {
        int add(int a, int b) { return a + b; }
        int divide(int a, int b) { return a / b; }
    }
    
    @Test
    @DisplayName("Unit Test: Basic addition")
    void testAddition() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.add(2, 3));
    }

    // ============================================
    // 2. PARAMETERIZED TESTING - Multiple inputs
    // ============================================
    
    @ParameterizedTest
    @CsvSource({"2,3,5", "10,20,30", "-5,5,0"})
    @DisplayName("Parameterized: Test multiple addition scenarios")
    void testAdditionWithMultipleInputs(int a, int b, int expected) {
        Calculator calc = new Calculator();
        assertEquals(expected, calc.add(a, b));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"racecar", "madam", "level"})
    @DisplayName("Parameterized: Test palindromes")
    void testPalindromes(String word) {
        assertTrue(isPalindrome(word));
    }
    
    @ParameterizedTest
    @MethodSource("provideStringsForLength")
    @DisplayName("Parameterized: Using method source")
    void testStringLength(String input, int expected) {
        assertEquals(expected, input.length());
    }
    
    static Stream<Arguments> provideStringsForLength() {
        return Stream.of(
            Arguments.of("hello", 5),
            Arguments.of("test", 4),
            Arguments.of("", 0)
        );
    }

    // ============================================
    // 3. BOUNDARY TESTING - Edge cases
    // ============================================
    
    @Test
    @DisplayName("Boundary: Test edge cases")
    void testBoundaryConditions() {
        Calculator calc = new Calculator();
        
        // Maximum values
        assertEquals(Integer.MAX_VALUE, calc.add(Integer.MAX_VALUE, 0));
        
        // Minimum values
        assertEquals(Integer.MIN_VALUE, calc.add(Integer.MIN_VALUE, 0));
        
        // Zero
        assertEquals(0, calc.add(0, 0));
    }

    // ============================================
    // 4. EXCEPTION TESTING - Error handling
    // ============================================
    
    @Test
    @DisplayName("Exception: Test divide by zero")
    void testDivideByZero() {
        Calculator calc = new Calculator();
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
    }
    
    @Test
    @DisplayName("Exception: Verify exception message")
    void testExceptionMessage() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Invalid input");
        });
        assertEquals("Invalid input", exception.getMessage());
    }

    // ============================================
    // 5. STATE-BASED TESTING - Object state
    // ============================================
    
    static class ShoppingCart {
        private List<String> items = new ArrayList<>();
        void addItem(String item) { items.add(item); }
        int getItemCount() { return items.size(); }
        void clear() { items.clear(); }
    }
    
    @Test
    @DisplayName("State-based: Test cart state changes")
    void testShoppingCartState() {
        ShoppingCart cart = new ShoppingCart();
        
        assertEquals(0, cart.getItemCount());
        
        cart.addItem("Apple");
        assertEquals(1, cart.getItemCount());
        
        cart.addItem("Banana");
        assertEquals(2, cart.getItemCount());
        
        cart.clear();
        assertEquals(0, cart.getItemCount());
    }

    // ============================================
    // 6. LIFECYCLE TESTING - Setup/Teardown
    // ============================================
    
    static class DatabaseConnection {
        boolean connected = false;
        void connect() { connected = true; }
        void disconnect() { connected = false; }
    }
    
    private DatabaseConnection db;
    
    @BeforeEach
    void setUp() {
        db = new DatabaseConnection();
        db.connect();
    }
    
    @AfterEach
    void tearDown() {
        db.disconnect();
    }
    
    @Test
    @DisplayName("Lifecycle: Test with setup")
    void testWithSetup() {
        assertTrue(db.connected);
    }

    // ============================================
    // 7. GROUPED ASSERTIONS - Multiple checks
    // ============================================
    
    static class Person {
        String name;
        int age;
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
    
    @Test
    @DisplayName("Grouped: Test multiple properties")
    void testPersonProperties() {
        Person person = new Person("Alice", 30);
        
        assertAll("person",
            () -> assertEquals("Alice", person.name),
            () -> assertEquals(30, person.age),
            () -> assertNotNull(person)
        );
    }

    // ============================================
    // 8. CONDITIONAL TESTING - Assumptions
    // ============================================
    
    @Test
    @DisplayName("Conditional: Run only on specific OS")
    void testOnlyOnMac() {
        assumeTrue(System.getProperty("os.name").contains("Mac"));
        // Test runs only on Mac
        assertTrue(true);
    }

    // ============================================
    // 9. TIMEOUT TESTING - Performance
    // ============================================
    
    @Test
    @Timeout(1)
    @DisplayName("Timeout: Must complete in 1 second")
    void testTimeout() {
        // Should complete quickly
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
        }
        assertTrue(sum > 0);
    }

    // ============================================
    // 10. NESTED TESTING - Logical grouping
    // ============================================
    
    @Nested
    @DisplayName("String Operations")
    class StringTests {
        
        @Test
        @DisplayName("Test uppercase")
        void testUpperCase() {
            assertEquals("HELLO", "hello".toUpperCase());
        }
        
        @Test
        @DisplayName("Test lowercase")
        void testLowerCase() {
            assertEquals("hello", "HELLO".toLowerCase());
        }
    }

    // ============================================
    // 11. REPEATED TESTING - Flaky tests
    // ============================================
    
    @RepeatedTest(5)
    @DisplayName("Repeated: Run 5 times")
    void testRepeated() {
        assertTrue(Math.random() >= 0);
    }

    // ============================================
    // 12. DISABLED TESTING - Skip tests
    // ============================================
    
    @Test
    @Disabled("Not implemented yet")
    @DisplayName("Disabled: Skip this test")
    void testNotReady() {
        fail("Should not run");
    }

    // ============================================
    // Helper methods
    // ============================================
    
    private boolean isPalindrome(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }
}