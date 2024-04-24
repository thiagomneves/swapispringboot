package br.com.swapi.api.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cache.redis")
public class CacheProperties {
   private String host;
    private int port;
    private int ttlCache;

    public String getHost() {
        return host;
    }

    public CacheProperties setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public CacheProperties setPort(int port) {
        this.port = port;
        return this;
    }

    public int getTtlCache() {
        return ttlCache;
    }

    public CacheProperties setTtlCache(int ttlCache) {
        this.ttlCache = ttlCache;
        return this;
    }
}
