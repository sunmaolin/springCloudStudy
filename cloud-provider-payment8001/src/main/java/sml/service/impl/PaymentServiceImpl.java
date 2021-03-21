package sml.service.impl;

import org.springframework.stereotype.Service;
import sml.dao.PaymentDao;
import sml.entities.Payment;
import sml.service.PaymentService;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource // java自带的，也可以进行依赖注入
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
