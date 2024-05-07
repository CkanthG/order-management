package com.sree.orders.ordermanagement.entities;

import com.sree.orders.ordermanagement.models.OrderDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private int id;
    private String oderName;
    private String orderDescription;
    private double orderValue;
    private int orderQuantity;

    public OrderDto toDto(Order order) {
        return new OrderDto(order.getId(), order.getOderName(), order.getOrderDescription(), order.getOrderValue(), order.getOrderQuantity());
    }
}
