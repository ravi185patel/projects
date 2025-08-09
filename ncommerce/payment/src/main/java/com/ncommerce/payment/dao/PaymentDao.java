package com.ncommerce.payment.dao;


import com.ncommerce.common.constant.PaymentStatus;
import com.ncommerce.payment.entity.PaymentEntity;
import com.ncommerce.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class PaymentDao {

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentEntity payment(PaymentEntity paymentEntity){
        paymentEntity.setPaymentStatus(PaymentStatus.PAYMENT_RECEIVED);
        paymentEntity.setPaymentDate(LocalDate.now());
        return paymentRepository.save(paymentEntity);
    }

    public PaymentEntity getPayment(Long paymentId){
        Optional<PaymentEntity> paymentEntityOptional = paymentRepository.findById(paymentId);
        if(paymentEntityOptional.isPresent()){
            return paymentEntityOptional.get();
        }
        throw new RuntimeException("No Payment Found");
    }

    public List<PaymentEntity> getPayments(Long paymentId){
        return paymentRepository.findAllByPaymentId(paymentId);
    }
}
