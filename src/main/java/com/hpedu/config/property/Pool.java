package com.hpedu.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@Component
//@PropertySource(value="classpath:/redis.properties")
//@ConfigurationProperties(prefix="redis")
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

		public int getMaxIdle() {
			return maxIdle;
		}

		public void setMaxIdle(int maxIdle) {
			this.maxIdle = maxIdle;
		}

		public void setMaxTotal(int maxTotal) {
			this.maxTotal = maxTotal;
		}

		public void setMaxWaitMillis(int maxWaitMillis) {
			this.maxWaitMillis = maxWaitMillis;
		}

		public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
			this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
		}

		public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
			this.numTestsPerEvictionRun = numTestsPerEvictionRun;
		}

		public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
			this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
		}

		public void setTestOnBorrow(boolean testOnBorrow) {
			this.testOnBorrow = testOnBorrow;
		}

		public void setTestWhileIdle(boolean testWhileIdle) {
			this.testWhileIdle = testWhileIdle;
		}

		public int getMaxTotal() {
			return maxTotal;
		}

		public int getMaxWaitMillis() {
			return maxWaitMillis;
		}

		public int getMinEvictableIdleTimeMillis() {
			return minEvictableIdleTimeMillis;
		}

		public int getNumTestsPerEvictionRun() {
			return numTestsPerEvictionRun;
		}

		public long getTimeBetweenEvictionRunsMillis() {
			return timeBetweenEvictionRunsMillis;
		}

		public boolean isTestOnBorrow() {
			return testOnBorrow;
		}

		public boolean isTestWhileIdle() {
			return testWhileIdle;
		}
}