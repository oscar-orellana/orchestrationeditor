package com.udc.fic.orchestrationeditor.configuration;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.udc.fic.orchestrationeditor.repository", entityManagerFactoryRef = "entityManagerFactoryBean")
@ComponentScan("com.udc.fic.orchestrationeditor")
@PropertySource("classpath:application.properties")
public class RepositoryConfiguration {

	@Resource
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(environment.getProperty(ConfigurationProperties.DATABASE_DRIVER));
		dataSource.setUrl(environment.getProperty(ConfigurationProperties.DATABASE_URL));
		dataSource.setUsername(environment.getProperty(ConfigurationProperties.DATABASE_USERNAME));
		dataSource.setPassword(environment.getProperty(ConfigurationProperties.DATABASE_PASSWORD));

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() throws ClassNotFoundException {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean
				.setPackagesToScan(environment.getProperty(ConfigurationProperties.ENTITYMANAGER_PACKAGES_TO_SCAN));
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

		Properties jpaProperties = new Properties();

		jpaProperties.put(ConfigurationProperties.HIBERNATE_DIALECT,
				environment.getProperty(ConfigurationProperties.HIBERNATE_DIALECT));
		jpaProperties.put(ConfigurationProperties.HIBERNATE_HBM2DDL_AUTO,
				environment.getProperty(ConfigurationProperties.HIBERNATE_HBM2DDL_AUTO));
		jpaProperties.put(ConfigurationProperties.HIBERNATE_SHOW_SQL,
				environment.getProperty(ConfigurationProperties.HIBERNATE_SHOW_SQL));
		jpaProperties.put(ConfigurationProperties.HIBERNATE_FORMAT_SQL,
				environment.getProperty(ConfigurationProperties.HIBERNATE_FORMAT_SQL));

		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager() throws ClassNotFoundException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return transactionManager;
	}

}
