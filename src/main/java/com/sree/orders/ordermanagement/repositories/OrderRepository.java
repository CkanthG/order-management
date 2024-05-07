package com.sree.orders.ordermanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sree.orders.ordermanagement.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
