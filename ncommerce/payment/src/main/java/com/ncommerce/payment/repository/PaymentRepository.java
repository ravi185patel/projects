package com.ncommerce.payment.repository;

import com.ncommerce.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {
    List<PaymentEntity> findAllByPaymentId(Long paymentId);
}
