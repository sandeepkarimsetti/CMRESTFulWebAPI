package com.contactsmanager.boot;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "com.contactsmanager.domain")
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", basePackages = {"com.contactsmanager.*"})
public class BeanConfig {
	
/*	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
	    return new HibernateJpaSessionFactoryBean();
	}*/
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Bean
	public SessionFactory getSessionFactory() {
	    if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
	        throw new NullPointerException("factory is not a hibernate factory");
	    }
	    return entityManagerFactory.unwrap(SessionFactory.class);
	}

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	registry.addMapping("/*")
            		.allowedOrigins("*")
            		//.allowedOrigins("https://ang5-cm-rest-web-api-9912638661.stackblitz.io")
            		//.allowedMethods("PUT", "DELETE", "GET", "POST")
            	  //.allowedHeaders("header1", "header2", "header3")
            	  //.exposedHeaders("header1", "header2")
            	  //.allowCredentials(false)
            		.maxAge(3600);
            }
        };
    }
	/*	
	@Bean(name = "entityManagerFactory")
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setPackagesToScan("com.mysource.model");
		emf.setPersistenceUnitName("default");
		emf.afterPropertiesSet();
		return emf.getObject();
	}
	
	@Bean
	@ConfigurationProperties("spring.datasource")
	public HikariDataSource dataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}*/

}
