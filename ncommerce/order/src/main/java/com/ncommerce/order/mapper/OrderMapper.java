package com.ncommerce.order.mapper;

import com.ncommerce.order.dto.OrderDto;
import com.ncommerce.order.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public static Order dtoToEntity(OrderDto orderDto){
        Order order = new Order();
        order.setId(order.getId());
        order.setProductId(orderDto.getProductId());
        order.setUserId(orderDto.getUserId());
        order.setPrice(orderDto.getPrice());
        order.setQuantity(orderDto.getQuantity());
        return order;
    }

    public static OrderDto entityToDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setProductId(order.getProductId());
        orderDto.setUserId(order.getUserId());
        orderDto.setPrice(order.getPrice());
        orderDto.setQuantity(order.getQuantity());
        return orderDto;
    }

    public static List<OrderDto> entityListToDtoList(List<Order> orders){
        return orders.stream().map(OrderMapper::entityToDto).collect(Collectors.toList());
    }

    public static List<Order> dtoListToEntityList(List<OrderDto> orderDto){
        return orderDto.stream().map(OrderMapper::dtoToEntity).collect(Collectors.toList());
    }
}
