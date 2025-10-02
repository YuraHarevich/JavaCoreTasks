package org.example.SalesAndCustomerAnalysis;

import org.example.SalesAndCustomerAnalysis.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OrderAnalyzerTest {

    private OrderAnalyzer analyzer;
    private List<Order> testOrders;

    @BeforeEach
    void setUp() {
        analyzer = new OrderAnalyzer();
        testOrders = createTestOrders();
    }

    @Test
    void testGetUniqueCities() {
        Set<String> cities = analyzer.getUniqueCities(testOrders);

        assertEquals(2, cities.size());
        assertTrue(cities.contains("New York"));
        assertTrue(cities.contains("Los Angeles"));
    }

    @Test
    void testGetAverageCheck() {
        double averageCheck = analyzer.getAverageCheck(testOrders);

        assertEquals(122.5, averageCheck, 0.001);
    }

    @Test
    void testGetMostPopularProduct() {
        String mostPopular = analyzer.getMostPopularProduct(testOrders);

        assertEquals("Book", mostPopular);
    }

    @Test
    void testGetTotalIncome() {
        double totalIncome = analyzer.getTotalIncome(testOrders);

        assertEquals(490.0, totalIncome, 0.001);
    }

    @Test
    void testGetCustomersWithMoreThan5Orders() {
        List<Customer> customers = analyzer.getCustomersWithMoreThan5Orders(testOrders);

        assertEquals(1, customers.size());
        assertEquals("customer1", customers.get(0).getCustomerId());
    }

    @Test
    void testEmptyOrders() {
        List<Order> emptyOrders = List.of();

        assertEquals(0, analyzer.getUniqueCities(emptyOrders).size());
        assertEquals(0.0, analyzer.getTotalIncome(emptyOrders));
        assertEquals("No products found", analyzer.getMostPopularProduct(emptyOrders));
        assertEquals(0.0, analyzer.getAverageCheck(emptyOrders));
        assertEquals(0, analyzer.getCustomersWithMoreThan5Orders(emptyOrders).size());
    }

    private List<Order> createTestOrders() {
        Customer customer1 = new Customer("customer1", "John Doe", "john@test.com",
                LocalDateTime.now(), 30, "New York");
        Customer customer2 = new Customer("customer2", "Jane Smith", "jane@test.com",
                LocalDateTime.now(), 25, "Los Angeles");

        List<Order> orders = new ArrayList<>();

        OrderItem item1 = new OrderItem("iPhone", 2, 100.0, Category.ELECTRONICS);
        OrderItem item2 = new OrderItem("Laptop", 1, 50.0, Category.ELECTRONICS);
        Order order1 = new Order("order1", LocalDateTime.now(), customer1,
                Arrays.asList(item1, item2), OrderStatus.DELIVERED);
        orders.add(order1);

        OrderItem item3 = new OrderItem("T-Shirt", 3, 30.0, Category.CLOTHING);
        Order order2 = new Order("order2", LocalDateTime.now(), customer2,
                Arrays.asList(item3), OrderStatus.PROCESSING);
        orders.add(order2);


        OrderItem item4 = new OrderItem("iPhone", 1, 200.0, Category.ELECTRONICS);
        Order order3 = new Order("order3", LocalDateTime.now(), customer1,
                Arrays.asList(item4), OrderStatus.DELIVERED);
        orders.add(order3);

        for (int i = 4; i <= 7; i++) {
            OrderItem item = new OrderItem("Book", 1, 20.0, Category.BOOKS);
            OrderStatus status = (i <= 5) ? OrderStatus.DELIVERED : OrderStatus.CANCELLED;
            Order order = new Order("order" + i, LocalDateTime.now(), customer1,
                    Arrays.asList(item), status);
            orders.add(order);
        }

        return orders;
    }
}