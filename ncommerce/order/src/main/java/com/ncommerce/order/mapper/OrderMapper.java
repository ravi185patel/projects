package com.ncommerce.order.mapper;

import com.ncommerce.order.dto.OrderDto;
import com.ncommerce.order.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    OrderDto toDTO(Order order);
    Order toEntity(OrderDto dto);
}
