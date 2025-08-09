package com.ncommerce.payment.mapper;

import com.ncommerce.common.dto.PaymentDto;
import com.ncommerce.payment.entity.PaymentEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentMapper {

    public static PaymentDto entityToDto(PaymentEntity paymentEntity){
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentId(paymentEntity.getPaymentId());
        paymentDto.setPayment(paymentEntity.getPayment());
        paymentDto.setPaymentStatus(paymentEntity.getPaymentStatus());
        paymentDto.setCustomerId(paymentEntity.getCustomerId());
        paymentDto.setPayment(paymentEntity.getPayment());
        paymentDto.setPaymentDate(paymentEntity.getPaymentDate());
        return paymentDto;
    }

    public static PaymentEntity dtoToEntity(PaymentDto paymentDto){
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setPayment(paymentDto.getPayment());
        paymentEntity.setPaymentStatus(paymentDto.getPaymentStatus());
        paymentEntity.setCustomerId(paymentDto.getCustomerId());
        paymentEntity.setPayment(paymentDto.getPayment());
        paymentEntity.setOrderId(paymentDto.getOrderId());
        paymentEntity.setPaymentDate(paymentDto.getPaymentDate());
        return paymentEntity;
    }

    public static List<PaymentDto> entityListIntoDtoList(List<PaymentEntity> paymentEntityList){
        return paymentEntityList.stream().map(PaymentMapper::entityToDto).collect(Collectors.toList());
    }
}
