package sml.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

    /**
     * 当配置了一个id为path_route_sml的路由地址
     * 当访问http://localhost:9527/guonei时会自动转发到新地址 http://news.baodu.com/guonei
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_sml",
                r->r.path("/guonei").uri("http://news.baidu.com/guonei"))
                .build();
        return routes.build();
    }
}
