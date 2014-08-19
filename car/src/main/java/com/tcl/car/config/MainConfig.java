package com.tcl.car.config;
import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.IntNode;
import com.tcl.craiglist.service.AlgorithmCraiglistCrawler;
import com.tcl.craiglist.service.AlgorithmCraiglistCrawlerService;




@Configuration
@EnableScheduling
@EnableJpaRepositories(entityManagerFactoryRef = "carEntityManagerFactory", basePackages = { "com.tcl.car.repository" })
@ComponentScan(basePackages = { "com.tcl.car.controller" })
public class MainConfig {

	

	private DataSource mySqlDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		;
		ds.setDriverClassName("com.mysql.jdbc.Driver");
//		ds.setUrl("jdbc:mysql://localhost:3306/craiglist?useUnicode=true&characterEncoding=gbk");
//		ds.setUsername("root");
//		ds.setPassword("glf1030");
		
	
		
		ds.setUrl("jdbc:mysql://192.168.1.55:3306/craiglist?useUnicode=true&characterEncoding=gbk");
		ds.setUsername("root");
		ds.setPassword("111111");
//		ds.setUsername("rauto");
//		ds.setPassword("DeJxedLX");
		return ds;
		

	}

	@Bean
	public JdbcTemplate rautoJdbcTemplate() {
		return new JdbcTemplate(mySqlDataSource());
	}

	/*
	 * @Bean public RideoReviewRepository rideoReviewRepo() { return new JdbcRideoReviewRepository(); }
	 */

	

	@Bean
	public EntityManagerFactory carEntityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		vendorAdapter.setGenerateDdl(true);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setDataSource(mySqlDataSource());
		// must call before afterPropertiesSet(), otherwise no persistence.xml found error
		factory.setPackagesToScan("com.tcl.car.model");
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(carEntityManagerFactory());
		return transactionManager;
	}
    @Bean 
    public HibernateExceptionTranslator hibernateExceptionTranslator(){ 
      return new HibernateExceptionTranslator(); 
    }

    @Bean
    public AlgorithmCraiglistCrawler acc()
    {
    	return new AlgorithmCraiglistCrawler();
    }
    @Bean
    public AlgorithmCraiglistCrawlerService accservice()
    {
    	return new AlgorithmCraiglistCrawlerService();
    }
}
