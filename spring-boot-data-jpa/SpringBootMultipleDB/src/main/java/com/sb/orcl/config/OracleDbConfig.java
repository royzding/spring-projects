package com.sb.orcl.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "userEntityManager",
  transactionManagerRef = "userTransactionManager",
  basePackages = { "com.sb.orcl.repo" }
)
public class OracleDbConfig {
    @Autowired
    private Environment env;
    
	  @Primary
	  @Bean(name = "dataSource")
	  @ConfigurationProperties(prefix = "spring.datasource")
	  public DataSource dataSource() {
	    return DataSourceBuilder.create().build();
	  }
	  
	    @Bean
	    @Primary
	    public LocalContainerEntityManagerFactoryBean userEntityManager() {
	        LocalContainerEntityManagerFactoryBean em
	          = new LocalContainerEntityManagerFactoryBean();
	        em.setDataSource(dataSource());
	        em.setPackagesToScan(
	          new String[] { "com.sb.orcl.model" });
	 
	        HibernateJpaVendorAdapter vendorAdapter
	          = new HibernateJpaVendorAdapter();
	        em.setJpaVendorAdapter(vendorAdapter);
	        HashMap<String, Object> properties = new HashMap<>();
	        properties.put("hibernate.hbm2ddl.auto",
	          env.getProperty("spring.datasource.hibernate.hbm2ddl.auto"));
	        properties.put("hibernate.dialect",
	          env.getProperty("spring.datasource.hibernate.dialect"));
	        em.setJpaPropertyMap(properties);
	 
	        return em;
	    }
	    
	    @Primary
	    @Bean
	    public PlatformTransactionManager userTransactionManager() {
	 
	        JpaTransactionManager transactionManager
	          = new JpaTransactionManager();
	        transactionManager.setEntityManagerFactory(
	          userEntityManager().getObject());
	        return transactionManager;
	    }
}