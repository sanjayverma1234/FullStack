package com.Group3tatastrive.VetPawtner.Controller;

import com.Group3tatastrive.VetPawtner.Entity.Payment;
import com.Group3tatastrive.VetPawtner.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> GetAllPaymentDetails()
    {
        return ResponseEntity.ok(paymentService.GetAllPayments());
    }
    @PostMapping("/insertpayments")
    public ResponseEntity<Payment> insertPayment(@RequestBody Payment payment)
    {
        return ResponseEntity.ok(paymentService.insertPayment(payment));
    }
    @PutMapping("modifypayments/{id}")
    public ResponseEntity<Payment> modifyPayment(@PathVariable int id,@RequestBody Payment payment)
    {
        return ResponseEntity.ok(paymentService.modifyPay(id,payment));
    }
    @DeleteMapping("payments/{id}")
    public ResponseEntity<Payment> deletePayment(@PathVariable int id)
    {
        return ResponseEntity.ok(paymentService.deletePayment(id));
    }
}
