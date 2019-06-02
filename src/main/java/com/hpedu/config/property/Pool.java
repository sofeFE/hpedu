package com.hpedu.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value="classpath:redis.properties")
@ConfigurationProperties(prefix="redis.pool")
@Data
public class Pool{
//		@Value("${pool.maxIdle}")
		private int maxIdle;

//		@Value("${pool.maxTotal}")
		private int maxTotal;

//		@Value("${pool.maxWaitMillis}")
		private int maxWaitMillis;

//		@Value("${pool.minEvictableIdleTimeMillis}")
		private int minEvictableIdleTimeMillis;

//		@Value("${redis.pool.numTestsPerEvictionRun}")
		private int numTestsPerEvictionRun;

//		@Value("${redis.pool.timeBetweenEvictionRunsMillis}")
		private long timeBetweenEvictionRunsMillis;

//		@Value("${redis.pool.testOnBorrow}")
		private boolean testOnBorrow;

//		@Value("${redis.pool.testWhileIdle}")
		private boolean testWhileIdle;

		
}