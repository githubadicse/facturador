package com.adicse.facturador.dbConfig;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//		entityManagerFactoryRef = "barEntityManagerFactory", 
//		transactionManagerRef = "barTransactionManager", 
//		basePackages = {
//		"com.adicse.facturador.sunat.repo" 
//				}
//)
public class DbConfigPostgresqlConexion {

	
	
	@Bean(name = "barDataSource")
	@ConfigurationProperties(prefix = "bar.datasource")
	public DataSource dataSource() {
		  DriverManagerDataSource dataSource = new DriverManagerDataSource();
		    dataSource.setDriverClassName("org.postgresql.Driver");
		    dataSource.setUrl("jdbc:postgresql://localhost:5432/facturaElectronica");
		    dataSource.setUsername( "admin" );
		    dataSource.setPassword( "postgres" );
		    dataSource.setSchema("facturaElectronica");

		    return dataSource;
	}

	@Bean(name = "barEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("barDataSource") DataSource dataSource) {
		 Map<String, Object> hibernateProperties = new HashMap<>();
		 hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");		
//		 hibernateProperties.put("driver-class-name", "org.postgresql.Driver");
//		 hibernateProperties.put("driverClassName", "org.postgresql.Driver");
//		 hibernateProperties.put("url", "jdbc:postgresql://localhost:5432/facturaElectronica");
		 
		 LocalContainerEntityManagerFactoryBean x =  builder.dataSource(dataSource).packages("com.adicse.facturador.sunat.model")
					.properties(hibernateProperties)
					.persistenceUnit("repo").build();

		 
		return builder.dataSource(dataSource).packages("com.adicse.facturador.sunat.model")
				.properties(hibernateProperties)
				.persistenceUnit("sunat").build();
	}

	@Bean(name = "barTransactionManager")
	public PlatformTransactionManager barTransactionManager(
			@Qualifier("barEntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
		return new JpaTransactionManager(barEntityManagerFactory) ;
	}
}
