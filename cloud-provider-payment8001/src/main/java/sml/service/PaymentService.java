package sml.service;

import org.apache.ibatis.annotations.Param;
import sml.entities.Payment;

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById( Long id);

}
