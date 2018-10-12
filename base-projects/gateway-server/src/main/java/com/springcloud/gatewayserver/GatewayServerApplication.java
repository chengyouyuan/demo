package com.springcloud.gatewayserver;

import com.springcloud.common.version.VersionedZoneAvoidanceRule;
import com.springcloud.gatewayserver.filter.MyCustomerFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.support.DefaultServerCodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }
    private static final String ALLOWED_HEADERS = "*";
    private static final String ALLOWED_METHODS = "*";
    private static final String ALLOWED_ORIGIN = "*";
    private static final String ALLOWED_EXPOSE = "API-TRACE-ID";
    private static final String MAX_AGE = "864000";

    @Bean
    public VersionedZoneAvoidanceRule versionedZoneAvoidanceRule(){
        return new VersionedZoneAvoidanceRule();
    }
    @Bean
    public ServerCodecConfigurer serverCodecConfigurer() {
        return new DefaultServerCodecConfigurer();
    }

    @Bean
    public RouteLocatorBuilder routeLocatorBuilder(ConfigurableApplicationContext context) {
        return new RouteLocatorBuilder(context);
    }

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/test/prefix/**")
                        .filters(f -> f.stripPrefix(2)
                                .filter(new MyCustomerFilter())
                                .addResponseHeader("X-Response-test", "test"))
                        .uri("lb://service-a")
                        .order(0)
                        .id("test_service-a")
                )
                .build();
    }


    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            if (CorsUtils.isCorsRequest(request)) {
                List<String> accessControlRequestHeaders = request.getHeaders().getAccessControlRequestHeaders();
                ServerHttpResponse response = ctx.getResponse();
                HttpHeaders headers = response.getHeaders();
                //cors协议解决跨域请求的问题
                //表明，它允许来自所有请求的跨域请求。
                headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
                //能接受的跨域请求的请求方式所有
                headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
                //跨域请求验证的缓存时间
                headers.add("Access-Control-Max-Age", MAX_AGE);
                if (CollectionUtils.isEmpty(accessControlRequestHeaders)) {
                    //表明它允许跨域请求包含content-type头
                    headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
                } else {
                    headers.add("Access-Control-Allow-Headers",
                            String.join(",", accessControlRequestHeaders));
                }
                //响应报头指示哪些报头可以公开为通过列出他们的名字的响应的一部分。
                headers.add("Access-Control-Expose-Headers", ALLOWED_EXPOSE);
                //发送允许带cookie
                headers.add("Access-Control-Allow-Credentials", "true");
                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }
            return chain.filter(ctx);
        };
    }
}
