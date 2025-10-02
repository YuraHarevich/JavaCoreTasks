package org.example.SalesAndCustomerAnalysis;


import org.example.SalesAndCustomerAnalysis.model.Customer;
import org.example.SalesAndCustomerAnalysis.model.Order;
import org.example.SalesAndCustomerAnalysis.model.OrderItem;
import org.example.SalesAndCustomerAnalysis.model.OrderStatus;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderAnalyzer {

    // 1. List of unique cities where orders came from
    public Set<String> getUniqueCities(List<Order> orders) {
        return orders.stream()
                .map(order -> order.getCustomer().getCity())
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    // 2. Total income for all completed orders
    public double getTotalIncome(List<Order> orders) {
        return orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .flatMap(order -> order.getItems().stream())
                .mapToDouble(item -> item.getQuantity() * item.getPrice())
                .sum();
    }

    // 3. The most popular product by sales
    public String getMostPopularProduct(List<Order> orders) {
        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        OrderItem::getProductName,
                        Collectors.summingInt(OrderItem::getQuantity)
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No products found");
    }

    // 4. Average check for successfully delivered orders
    public double getAverageCheck(List<Order> orders) {
        return orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .mapToDouble(order -> order.getItems().stream()
                        .mapToDouble(item -> item.getQuantity() * item.getPrice())
                        .sum())
                .average()
                .orElse(0.0);
    }

    // 5. Customers who have more than 5 orders
    public List<Customer> getCustomersWithMoreThan5Orders(List<Order> orders) {
        Map<Customer, Long> customerOrderCount = orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomer, Collectors.counting()));

        return customerOrderCount.entrySet().stream()
                .filter(entry -> entry.getValue() > 5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
