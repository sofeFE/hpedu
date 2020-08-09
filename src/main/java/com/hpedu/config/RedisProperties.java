package com.hpedu.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Component
@PropertySource("classpath:/redis.properties")
@ConfigurationProperties(prefix = "redis",ignoreInvalidFields = true)
@Getter
@Setter
public class RedisProperties {

	private int dataBase;
	private String hostName;
	private int port;
	private int timeout;
//	@NestedConfigurationProperty -->一个非内部类属于该类的内置属性
	private Pool pool;

	@Getter
	@Setter
	public static class Pool{
		private int maxIdle;
		private int maxTotal;
		private int maxWaitMillis;
		private int minEvictableIdleTimeMillis;
		private int numTestsPerEvictionRun;
		private long timeBetweenEvictionRunsMillis;
		private boolean testOnBorrow;
		private boolean testWhileIdle;
	}
}
