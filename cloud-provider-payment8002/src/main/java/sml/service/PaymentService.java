package sml.service;

import sml.entities.Payment;

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById( Long id);

}
