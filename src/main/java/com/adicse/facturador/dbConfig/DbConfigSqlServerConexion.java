package com.adicse.facturador.dbConfig;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//		  entityManagerFactoryRef = "entityManagerFactory",
//		  basePackages = { "com.adicse.facturador.wintac.repo" }
//		)

public class DbConfigSqlServerConexion {

	@Primary
	@Bean(name="dataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		  DriverManagerDataSource dataSource = new DriverManagerDataSource();
		    dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		    dataSource.setUrl("jdbc:sqlserver://192.168.24.9;databaseName=bdwintac");
		    dataSource.setUsername( "sa" );
		    dataSource.setPassword( "Cayodrago." );
		    dataSource.setSchema("dbo");
		    return dataSource ;
	}

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("dataSource") DataSource dataSource) {
		 Map<String, Object> hibernateProperties = new HashMap<>();
		 
		 hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");	
//		 hibernateProperties.put("driver-class-name", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
//		 hibernateProperties.put("driverClassName", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
//		 hibernateProperties.put("url", "jdbc:sqlserver://192.168.24.9;databaseName=bdwintac");
		 
		return builder.dataSource(dataSource).packages("com.adicse.facturador.wintac.model")
				.properties(hibernateProperties)
				.persistenceUnit("wintac").build() ;
	}

	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
		return new JpaTransactionManager(barEntityManagerFactory) ;
	}
}
