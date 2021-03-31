package sml.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PaymentService {

    /**
     * 正常访问，肯定OK
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_OK: "+id+"\t"+"O(∩_∩)O哈哈";
    }

    /**
     * 超时访问
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            /*设置自身超时时间的峰值，峰值内可以正常运行，超过了需要有兜底的方法处理，作为服务降级fallback*/
           @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id){
        long timeNumber = 5000;
        // 无论是异常还是超时都会触发降级
//        try {
//            Thread.sleep(timeNumber);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        int age = 10/0;
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_TimeOut: "+id+"\t"+"O(∩_∩)O哈哈~"+" 耗时"+timeNumber+"秒钟";
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_TimeOutHandler: "+id+"\t"+"/(ㄒoㄒ)/~~";
    }

    // ------------- 服务熔断  -----------

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            //1.首先开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            //2.在快照时间窗口：断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照时间窗，默认为最近的十秒钟
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            //3.窗口期内，请求峰值达到10次，若没到十次不会触发（默认十秒内20个请求）
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            //4.10次请求失败率达到60%。断路器close->open（默认10秒内超过50%的请求失败）
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少次后跳闸
            //5. 一段时间后（默认5秒），这个时候断路器是半开状态close->halfOpen,会让其中一个请求进行转发，如果成功，断路器关闭
    })
    public String paymentCircuitBreaker(Integer id){
        if(id < 0){
            throw new RuntimeException("*****id  不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能为符属，请稍后再试。id：" + id;
    }

}
