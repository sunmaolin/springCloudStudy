package sml.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 手写负载均衡轮询算法
 */
public interface LoadBalabcer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
