package sml.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced //加入负载均衡，否则UnknownHostException
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
