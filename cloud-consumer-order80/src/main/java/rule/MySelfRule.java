package rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 我们自己定义的负载均衡规则，不能被componentScan扫描，否则达不到定制化的需求
 */
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        //定义为随机负载均衡算法
        return new RandomRule();
    }

}
