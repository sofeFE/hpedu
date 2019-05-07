package com.hpedu.config.property;

import org.springframework.beans.factory.annotation.Autowired;

//@Component
//@PropertySource(value = "classpath:/redis.properties")
//@ConfigurationProperties(prefix = "redis")
public class RedisConfigurationProperties {

	private int dataBase;
	private String hostName;
	private int port;
	private int timeout;
	@Autowired
	private Pool pool;

	public void setDataBase(int dataBase) {
		this.dataBase = dataBase;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setPool(Pool pool) {
		this.pool = pool;
	}

	public int getDataBase() {
		return dataBase;
	}

	public String getHostName() {
		return hostName;
	}

	public int getPort() {
		return port;
	}

	public int getTimeout() {
		return timeout;
	}

	public Pool getPool() {
		return pool;
	}

}
/*
 * private String dbUrl; private String username; private String password;
 * private String driverClassName; private int initialSize; private int minIdle;
 * private int maxActive; private int maxWait;
 */
