package com.ncommerce.payment.service;

import com.ncommerce.payment.constant.PaymentStatus;
import com.ncommerce.payment.dao.PaymentDao;
import com.ncommerce.payment.dto.PaymentDto;
import com.ncommerce.payment.entity.PaymentEntity;
import com.ncommerce.payment.mapper.PaymentMapper;
import com.ncommerce.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    public PaymentDto payment(PaymentDto paymentDto){
        PaymentEntity paymentEntity = PaymentMapper.dtoToEntity(paymentDto);
        paymentEntity = paymentDao.payment(paymentEntity);
        return PaymentMapper.entityToDto(paymentEntity);
    }

    public PaymentDto getPayment(Long paymentId) {
        return PaymentMapper.entityToDto(paymentDao.getPayment(paymentId));
    }

    public List<PaymentDto> getPayments(Long paymentId){
        return PaymentMapper.entityListIntoDtoList(paymentDao.getPayments(paymentId));
    }
}
