package com.adicse.facturador.dbConfig;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//  entityManagerFactoryRef = "userDBEntityManagerFactory",
//  transactionManagerRef = "transactionManager",
//  basePackages = { "com.adicse.facturador.wintac" }
//)

public class DbConfigSqlServer {

	@Primary
	@Bean
	@ConfigurationProperties("spring.datasource")
	public DataSourceProperties userDsProperties() {
		return new DataSourceProperties();
	}
	
	@Primary
	@Bean
	public DataSource userDs(@Qualifier("userDsProperties") DataSourceProperties  userDsProperties) {
		return userDsProperties.initializeDataSourceBuilder().build();
	}


	@Bean
	public LocalContainerEntityManagerFactoryBean userDBEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("userDs") DataSource userDs) {
		 Map<String, Object> hibernateProperties = new HashMap<>();
		 hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");	
		 hibernateProperties.put("driver-class-name", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 hibernateProperties.put("jdbcUrl", "jdbc:sqlserver://192.168.24.9;databaseName=bdwintac");
		return builder.dataSource(userDs).packages("com.adicse.facturador.wintac.model")
				.properties(hibernateProperties)
				.persistenceUnit("wintac").build();
	}

	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("userDBEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory) ;
	}
}
