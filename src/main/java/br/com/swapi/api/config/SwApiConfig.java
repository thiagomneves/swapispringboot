package br.com.swapi.api.config;


import br.com.swapi.api.config.properties.CacheProperties;
import br.com.swapi.api.config.properties.SwApiProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({SwApiProperties.class, CacheProperties.class})
public class SwApiConfig {
}
