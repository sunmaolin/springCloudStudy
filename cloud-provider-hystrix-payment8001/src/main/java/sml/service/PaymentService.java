package sml.service;

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
    public String paymentInfo_TimeOut(Integer id){
        long timeNumber = 3000;
        try {
            Thread.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+" paymentInfo_TimeOut: "+id+"\t"+"O(∩_∩)O哈哈~"+" 耗时"+timeNumber+"秒钟";
    }

}
