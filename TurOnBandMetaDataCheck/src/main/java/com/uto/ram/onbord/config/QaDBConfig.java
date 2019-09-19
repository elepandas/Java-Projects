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
	@EnableJpaRepositories(entityManagerFactoryRef = "qaEntityManagerFactory", basePackages = {"com.uto.ram.onbord.qa.repository"},transactionManagerRef = "qaTransactionManager")
	public class QaDBConfig {
		
		@Autowired
		private Environment env;
		
		@Bean(name = "qaDatasource")
		public DataSource dataSource() {
			
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
		    dataSource.setDriverClassName(env.getProperty("spring.qa.datasource.driver-class-name"));
		    dataSource.setUrl(env.getProperty("spring.qa.datasource.url"));
		    dataSource.setUsername(env.getProperty("spring.qa.datasource.username"));
		    dataSource.setPassword(env.getProperty("spring.qa.datasource.password"));
			
			return dataSource;
		}
		
		
		@Bean(name = "qaEntityManagerFactory")
		public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("qaDatasource") DataSource dataSource) {
			
		/*
		 * Map<String, Object> properties = new HashMap<>();
		 * properties.put("hibernate.hbm2ddl.auto","update");
		 * properties.put("hibernate.dialect","org.hibernate.dialect.MYSQL5Dialect");
		 */
			return builder.dataSource(dataSource).packages("com.uto.ram.onbord.model.qa").persistenceUnit("QaTable").build();
		}
		
		
		@Bean(name = "qaTransactionManager")
		public PlatformTransactionManager transactionManager(@Qualifier("qaEntityManagerFactory")EntityManagerFactory entityManagerFactory) {
			return new JpaTransactionManager(entityManagerFactory);
			
		}
		
	}
