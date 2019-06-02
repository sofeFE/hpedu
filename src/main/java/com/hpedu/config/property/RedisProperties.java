package com.hpedu.config.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/redis.properties")
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisProperties {

	private int dataBase;
	private String hostName;
	private int port;
	private int timeout;
	@Autowired
	private Pool pool;


}
/*
 * private String dbUrl; 
 * private String username; 
 * private String password;
 * private String driverClassName; 
 * private int initialSize; 
 * private int minIdle;
 * private int maxActive; 
 * private int maxWait;
 */
