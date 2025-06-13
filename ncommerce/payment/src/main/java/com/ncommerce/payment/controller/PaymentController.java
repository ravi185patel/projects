package com.ncommerce.payment.controller;

import com.ncommerce.payment.dto.PaymentDto;
import com.ncommerce.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable("paymentId") Long paymentId) {
        return ResponseEntity.ok(paymentService.getPayment(paymentId));
    }

    @GetMapping("/all/{paymentId}")
    public ResponseEntity<List<PaymentDto>> getPayments(@PathVariable("paymentId") Long paymentId){
        return ResponseEntity.ok(paymentService.getPayments(paymentId));
    }

    @PostMapping
    public ResponseEntity<PaymentDto> payment(@RequestBody PaymentDto paymentDto) throws Throwable {
        return ResponseEntity.ok(paymentService.payment(paymentDto));
    }
}
