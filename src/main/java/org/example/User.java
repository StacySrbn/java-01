package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private List<Order> orderHistory;

    public void addOrder(Order order) {
        orderHistory.add(order);
    }

    @Override
    public String toString() {
        return "Користувач: " + name;
    }
}
