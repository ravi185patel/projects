package com.ncommerce.payment.dao;

import com.ncommerce.payment.constant.PaymentStatus;
import com.ncommerce.payment.dto.PaymentDto;
import com.ncommerce.payment.entity.PaymentEntity;
import com.ncommerce.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Component
public class PaymentDao {

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentEntity payment(PaymentEntity paymentEntity){
        paymentEntity.setPaymentStatus(PaymentStatus.SUCCESS);
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
