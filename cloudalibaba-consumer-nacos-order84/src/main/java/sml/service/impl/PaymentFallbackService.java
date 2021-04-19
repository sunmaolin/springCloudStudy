package sml.service.impl;


import org.springframework.stereotype.Component;
import sml.entities.CommonResult;
import sml.entities.Payment;
import sml.service.PaymentService;

@Component
public class PaymentFallbackService implements PaymentService {
	@Override
	public CommonResult<Payment> paymentSQL(Long id) {
		return new CommonResult<>(44444, "服务降级返回,---PaymentFallbackService", new Payment(id, "errorSerial"));
	}
}
