package com.aurora.springcloud.service.impl;

import com.aurora.springcloud.dao.PaymentDao;
import com.aurora.springcloud.entities.Payment;
import com.aurora.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource   //@Autowired也可以
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }

}