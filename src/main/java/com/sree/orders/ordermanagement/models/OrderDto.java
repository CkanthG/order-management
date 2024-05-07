package com.sree.orders.ordermanagement.models;

import com.sree.orders.ordermanagement.entities.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private int id;
    private String oderName;
    private String orderDescription;
    private double orderValue;
    private int orderQuantity;

    public Order toEntity(OrderDto orderDto) {
        return new Order(orderDto.getId(), orderDto.oderName, orderDto.getOrderDescription(), orderDto.getOrderValue(), orderDto.getOrderQuantity());
    }
}
