package com.ncommerce.order.dao;


import com.ncommerce.order.entity.Order;
import com.ncommerce.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class OrderDao {

    @Autowired
    private OrderRepository orderRepository;

    public Order getOrder(String id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    public Order placeOrder(Order order){
        return orderRepository.save(order);
    }

    public boolean placeOrders(List<Order> orders){
        orderRepository.saveAll(orders);
        return true;
    }
}
