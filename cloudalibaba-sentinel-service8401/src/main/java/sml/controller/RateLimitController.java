package sml.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sml.entities.CommonResult;
import sml.entities.Payment;
import sml.myHandler.CustomerBlockHanbler;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")// 根据资源名称限流，注意资源名是resource
    public CommonResult byResource() {
        return new CommonResult(200, "按资源名称限流测试", new Payment(2020L, "serial001"));
    }

    public CommonResult handleException(BlockException blockException) {
        return new CommonResult(444, blockException.getClass().getCanonicalName() + "\t服务不可用");
    }

    @GetMapping("/rateLimit/byUrl")
    @SentinelResource("byUrl")
    public CommonResult byUrl(){
        return new CommonResult(200, "按url限流测试ok", new Payment(2020L, "serial002"));
    }

    @GetMapping("/rateLimit/customerBlockHanbler")
    @SentinelResource(value = "customerBlockHanbler",
            blockHandlerClass = CustomerBlockHanbler.class,
            blockHandler = "handleException2")
    public CommonResult customerBlockHanbler(){
        return new CommonResult(200, "customerBlockHanbler", new Payment(2020L, "serial002"));
    }
}
