package com.example.javacodingjourney.testing;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Advanced Testing Patterns and Strategies
 */
public class AdvancedTestingPatterns {

    // ============================================
    // 13. INTEGRATION TESTING - Multiple components
    // ============================================
    
    static class UserService {
        private UserRepository repository;
        
        UserService(UserRepository repository) {
            this.repository = repository;
        }
        
        User createUser(String name, String email) {
            if (repository.findByEmail(email) != null) {
                throw new IllegalArgumentException("User already exists");
            }
            User user = new User(name, email);
            return repository.save(user);
        }
    }
    
    static class UserRepository {
        private Map<String, User> users = new HashMap<>();
        
        User save(User user) {
            users.put(user.email, user);
            return user;
        }
        
        User findByEmail(String email) {
            return users.get(email);
        }
    }
    
    static class User {
        String name, email;
        User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }
    
    @Test
    @DisplayName("Integration: Test service with repository")
    void testUserServiceIntegration() {
        UserRepository repository = new UserRepository();
        UserService service = new UserService(repository);
        
        User user = service.createUser("John", "john@test.com");
        
        assertEquals("John", user.name);
        assertEquals("john@test.com", user.email);
        
        // Test duplicate email
        assertThrows(IllegalArgumentException.class, 
            () -> service.createUser("Jane", "john@test.com"));
    }

    // ============================================
    // 14. BEHAVIOR-DRIVEN TESTING - Given/When/Then
    // ============================================
    
    @Test
    @DisplayName("BDD: Shopping cart checkout process")
    void testShoppingCartCheckout() {
        // Given - Initial state
        ShoppingCart cart = new ShoppingCart();
        PaymentProcessor processor = new PaymentProcessor();
        
        // When - Actions
        cart.addItem("Laptop", 999.99);
        cart.addItem("Mouse", 29.99);
        
        // Then - Assertions
        assertEquals(2, cart.getItemCount());
        assertEquals(1029.98, cart.getTotal(), 0.01);
        
        // When - Process payment
        boolean paymentResult = processor.processPayment(cart.getTotal());
        
        // Then - Verify result
        assertTrue(paymentResult);
    }
    
    static class ShoppingCart {
        private List<CartItem> items = new ArrayList<>();
        
        void addItem(String name, double price) {
            items.add(new CartItem(name, price));
        }
        
        int getItemCount() { return items.size(); }
        
        double getTotal() {
            return items.stream().mapToDouble(item -> item.price).sum();
        }
        
        static class CartItem {
            String name;
            double price;
            CartItem(String name, double price) {
                this.name = name;
                this.price = price;
            }
        }
    }
    
    static class PaymentProcessor {
        boolean processPayment(double amount) {
            return amount > 0;
        }
    }

    // ============================================
    // 15. DATA-DRIVEN TESTING - External data
    // ============================================
    
    @TestFactory
    @DisplayName("Data-driven: Dynamic tests from data")
    Collection<DynamicTest> testPasswordValidation() {
        List<TestCase> testCases = Arrays.asList(
            new TestCase("password123", false, "Too weak"),
            new TestCase("Password123!", true, "Strong password"),
            new TestCase("12345", false, "Too short"),
            new TestCase("UPPERCASE123!", true, "Valid password")
        );
        
        return testCases.stream()
            .map(testCase -> DynamicTest.dynamicTest(
                testCase.description,
                () -> assertEquals(testCase.expected, isValidPassword(testCase.password))
            ))
            .toList();
    }
    
    static class TestCase {
        String password;
        boolean expected;
        String description;
        
        TestCase(String password, boolean expected, String description) {
            this.password = password;
            this.expected = expected;
            this.description = description;
        }
    }
    
    private boolean isValidPassword(String password) {
        return password.length() >= 8 && 
               password.matches(".*[A-Z].*") && 
               password.matches(".*[0-9].*") && 
               password.matches(".*[!@#$%^&*].*");
    }

    // ============================================
    // 16. PROPERTY-BASED TESTING - Random inputs
    // ============================================
    
    @RepeatedTest(100)
    @DisplayName("Property: List reverse is idempotent")
    void testListReverseProperty() {
        List<Integer> original = generateRandomList();
        List<Integer> reversed = new ArrayList<>(original);
        Collections.reverse(reversed);
        Collections.reverse(reversed);
        
        assertEquals(original, reversed);
    }
    
    private List<Integer> generateRandomList() {
        Random random = new Random();
        int size = random.nextInt(10) + 1;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(100));
        }
        return list;
    }

    // ============================================
    // 17. MUTATION TESTING - Test quality
    // ============================================
    
    static class MathUtils {
        static int max(int a, int b) {
            return a > b ? a : b;  // Mutation: change > to >=
        }
        
        static boolean isEven(int n) {
            return n % 2 == 0;  // Mutation: change == to !=
        }
    }
    
    @Test
    @DisplayName("Mutation: Strong tests for max function")
    void testMaxFunction() {
        assertEquals(5, MathUtils.max(3, 5));
        assertEquals(5, MathUtils.max(5, 3));
        assertEquals(5, MathUtils.max(5, 5));  // This catches >= mutation
        assertEquals(-1, MathUtils.max(-5, -1));
    }
    
    @Test
    @DisplayName("Mutation: Strong tests for isEven function")
    void testIsEvenFunction() {
        assertTrue(MathUtils.isEven(2));
        assertTrue(MathUtils.isEven(0));
        assertFalse(MathUtils.isEven(1));
        assertFalse(MathUtils.isEven(3));
    }

    // ============================================
    // 18. CONTRACT TESTING - Interface compliance
    // ============================================
    
    interface Sortable<T> {
        void sort(List<T> items);
    }
    
    static class BubbleSort implements Sortable<Integer> {
        public void sort(List<Integer> items) {
            Collections.sort(items);
        }
    }
    
    @Test
    @DisplayName("Contract: Test sorting contract")
    void testSortingContract() {
        Sortable<Integer> sorter = new BubbleSort();
        List<Integer> items = Arrays.asList(3, 1, 4, 1, 5);
        
        sorter.sort(items);
        
        // Contract: items should be sorted
        for (int i = 1; i < items.size(); i++) {
            assertTrue(items.get(i-1) <= items.get(i));
        }
    }
}