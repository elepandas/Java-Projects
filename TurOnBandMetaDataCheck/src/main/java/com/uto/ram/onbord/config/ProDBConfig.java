package com.uto.ram.onbord.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = {"com.uto.ram.onbord.pro.repository"}, transactionManagerRef = "transactionManager")
public class ProDBConfig {
	
	@Autowired
	private Environment env;
	
	@Primary
	@Bean(name = "datasource")
	
	public DataSource dataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(env.getProperty("spring.prod.datasource.driver-class-name"));
	    dataSource.setUrl(env.getProperty("spring.prod.datasource.url"));
	    dataSource.setUsername(env.getProperty("spring.prod.datasource.username"));
	    dataSource.setPassword(env.getProperty("spring.prod.datasource.password"));
		
		return dataSource;
	}
	
	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("datasource") DataSource dataSource) {
		
		/*
		 * Map<String, Object> properties = new HashMap<>();
		 * properties.put("hibernate.hbm2ddl.auto","update");
		 * properties.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
		 */
		return builder.dataSource(dataSource).packages("com.uto.ram.onbord.model.pro").persistenceUnit("ProTable").build();
	}
	
	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory")EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
		
	}
	
}
