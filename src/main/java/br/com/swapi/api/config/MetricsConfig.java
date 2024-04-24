package br.com.swapi.api.config;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(PrometheusMeterRegistry.class)
public class MetricsConfig {


    @Bean
    InitializingBean forcePrometheusPostProcessor(BeanPostProcessor meterPrometheusProcessor, PrometheusMeterRegistry prometheusMeterRegistry){
        return () -> meterPrometheusProcessor.postProcessAfterInitialization(prometheusMeterRegistry,"");
    }


}
