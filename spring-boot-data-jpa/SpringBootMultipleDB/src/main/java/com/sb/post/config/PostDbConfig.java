package com.sb.post.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "postEntityManager",
  transactionManagerRef = "postTransactionManager",
  basePackages = { "com.sb.post.repo" }
)
public class PostDbConfig {
    @Autowired
    private Environment env;
    
	  @Bean(name = "postDataSource")
	  @ConfigurationProperties(prefix = "post.datasource")
	  public HikariDataSource dataSource() {
	    return DataSourceBuilder.create().type(HikariDataSource.class).build();
	  }
	  
	    @Bean
	    public LocalContainerEntityManagerFactoryBean postEntityManager() {
	        LocalContainerEntityManagerFactoryBean em
	          = new LocalContainerEntityManagerFactoryBean();
	        em.setDataSource(dataSource());
	        em.setPackagesToScan(
	          new String[] { "com.sb.post.model" });
	 
	        HibernateJpaVendorAdapter vendorAdapter
	          = new HibernateJpaVendorAdapter();
	        em.setJpaVendorAdapter(vendorAdapter);
	        HashMap<String, Object> properties = new HashMap<>();
	        properties.put("hibernate.hbm2ddl.auto",
	          env.getProperty("post.datasource.hibernate.hbm2ddl.auto"));
	        properties.put("hibernate.dialect",
	          env.getProperty("post.datasource.hibernate.dialect"));
	        em.setJpaPropertyMap(properties);
	 
	        return em;
	    }
	    
	    @Bean
	    public PlatformTransactionManager postTransactionManager() {
	 
	        JpaTransactionManager transactionManager
	          = new JpaTransactionManager();
	        transactionManager.setEntityManagerFactory(
	        		postEntityManager().getObject());
	        return transactionManager;
	    }
}