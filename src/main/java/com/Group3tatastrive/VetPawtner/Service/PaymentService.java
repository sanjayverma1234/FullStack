package com.Group3tatastrive.VetPawtner.Service;

import com.Group3tatastrive.VetPawtner.Entity.Payment;
import com.Group3tatastrive.VetPawtner.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    public List<Payment> GetAllPayments()
    {
        return paymentRepository.findAll();
    }
    public Payment insertPayment(@RequestBody Payment payment)
    {
        return paymentRepository.save(payment);
    }
    public Payment modifyPay(@PathVariable int id, @RequestBody Payment payment)
    {
        Payment payment1 = paymentRepository.findById(id).orElse(null);
        payment1.setStatus(payment.getStatus());
        payment1.setAmount(payment.getAmount());
        payment1.setUser(payment.getUser());
        payment1.setFunding(payment.getFunding());
        return paymentRepository.save(payment1);
    }
    public Payment deletePayment(int id)
    {
        Payment payment1 = paymentRepository.findById(id).orElse(null);
        if(payment1 != null)
        {
            paymentRepository.delete(payment1);
        }
        return payment1;
    }
}
