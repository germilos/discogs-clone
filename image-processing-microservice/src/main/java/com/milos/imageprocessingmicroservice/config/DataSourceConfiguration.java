package com.milos.imageprocessingmicroservice.config;

import java.lang.reflect.InvocationTargetException;
import java.sql.Driver;

import javax.sql.DataSource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ClassUtils;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Getter
@Setter
public class DataSourceConfiguration
{
	private String driverClassName;

	private String url;

	private String username;

	private String password;

	@Bean
	DataSource getDataSource() throws IllegalAccessException, InvocationTargetException, InstantiationException
	{
		final Class<?> driverClass = ClassUtils.resolveClassName(driverClassName, this.getClass().getClassLoader());
		final Driver driver = (Driver) ClassUtils.getConstructorIfAvailable(driverClass).newInstance();
		return new SimpleDriverDataSource(driver, url, username, password);
	}
}
