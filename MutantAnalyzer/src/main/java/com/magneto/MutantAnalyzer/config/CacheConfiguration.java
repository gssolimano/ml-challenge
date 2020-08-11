package com.magneto.MutantAnalyzer.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;

@EnableCaching
@Configuration
public class CacheConfiguration {
	
	@Bean
	public CacheManager cacheManager() { 
		return new EhCacheCacheManager(cacheManagerFactory().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean cacheManagerFactory() {
		EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
		bean.setConfigLocation(new ClassPathResource("cacheConfig.xml"));
		bean.setShared(true);
		return bean;
	}
	
	
	
	
	/*
	@Bean 
	public CacheManager cacheManager() {
        return new EhCacheCacheManager(cacheMangerFactory().getObject());
    }

    @Bean
    public EhCacheManagerFactoryBean cacheMangerFactory() {
        EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
        bean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        bean.setShared(true);
        return bean;
    }
    */

}
