package com.xworkz.login.configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DBConfiguration {

	public DBConfiguration() {
		System.out.println("calling default constructor of:"+this.getClass().getSimpleName());
		
}
	@Bean
	public LocalContainerEntityManagerFactoryBean factoryBean(DataSource dataSource) {
		
		LocalContainerEntityManagerFactoryBean factoryBean =new LocalContainerEntityManagerFactoryBean(); 
		factoryBean.setPackagesToScan("com.xworkz");
		factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		factoryBean.setDataSource(dataSource);
		
		HashMap<String, Object> jpaProperties = new HashMap<String, Object>();
		jpaProperties.put("hibernate.show_sql", true);
		
		factoryBean.setJpaPropertyMap(jpaProperties);
		return factoryBean;
	}
	
	@Bean
	public DataSource dataSource() {
		
		System.out.println("Created DataSource");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost:3306/login");
			dataSource.setPassword("s1820923");
			dataSource.setUsername("root");
			return dataSource;
		}
	}
