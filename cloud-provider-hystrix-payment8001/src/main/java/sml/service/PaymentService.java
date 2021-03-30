package sml.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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

}
