package com.sree.orders.ordermanagement.services;

import java.net.URI;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sree.orders.ordermanagement.entities.Order;
import com.sree.orders.ordermanagement.models.OrderDto;
import com.sree.orders.ordermanagement.repositories.OrderRepository;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(
            it -> it.toDto(it)
        ).toList();
    }

    public void saveOrder(OrderDto orderDto) {
        Order oder = orderDto.toEntity(orderDto);
        orderRepository.save(oder);
    }
}
