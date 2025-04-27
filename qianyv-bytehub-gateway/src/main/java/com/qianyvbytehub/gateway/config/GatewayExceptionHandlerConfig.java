package com.qianyvbytehub.gateway.config;

import com.jingdianjichi.gateway.exception.GatewayExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class GatewayExceptionHandlerConfig {

    @Bean
    public GatewayExceptionHandler gatewayExceptionHandler() {
        GatewayExceptionHandler gatewayExceptionHandler = new GatewayExceptionHandler();
        log.info("异常处理器的bean： {}", gatewayExceptionHandler);
        return gatewayExceptionHandler;
    }

}
