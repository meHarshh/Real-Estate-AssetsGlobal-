package org.assetsglobal.cache;

import org.assetsglobal.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheBeanConfig {

	@Bean
	CacheStore<String> otpCache() {
		return new CacheStore<String>(10);
	}

	@Bean
	CacheStore<User> userCache() {
		return new CacheStore<User>(30);
	}
}
