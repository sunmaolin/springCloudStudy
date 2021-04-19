package sml.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sml.entities.CommonResult;
import sml.entities.Payment;

import java.util.HashMap;


@RestController
public class PaymentController
{
	@Value("${server.port}")
	private String serverPort;

	public static HashMap<Long, Payment> hashMap = new HashMap<>();
	static {
		hashMap.put(1L, new Payment(1L, "aaaaaaaaaaaaaaaaa"));
		hashMap.put(2L, new Payment(2L, "bbbbbbbbbbbbbbbbb"));
		hashMap.put(3L, new Payment(3L, "ccccccccccccccccc"));
	}

	@GetMapping(value = "/paymentSQL/{id}")
	public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id)
	{
		Payment payment = hashMap.get(id);
		CommonResult<Payment> commonResult = new CommonResult<>(200, "from mysql,serverPort:" + serverPort, payment);
		return commonResult;
	}
}


