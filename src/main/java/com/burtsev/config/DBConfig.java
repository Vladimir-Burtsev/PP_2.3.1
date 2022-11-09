package com.burtsev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(value = "com.burtsev")
@PropertySource("classpath:db.properties")
public class DBConfig {


    @Bean
    public DataSource getDataSource(Environment env) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSource.setUrl(env.getRequiredProperty("db.url"));
        dataSource.setUsername(env.getRequiredProperty("db.username"));
        dataSource.setPassword(env.getRequiredProperty("db.password"));
        return dataSource;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("com.burtsev.model");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();

        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        jpaProperties.put("hibernate.temp.use_jdbc_metadata_defaults", env.getRequiredProperty("hibernate.temp.use_jdbc_metadata_defaults"));
        jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        jpaProperties.put("hibernate.use_sql_comments", env.getRequiredProperty("hibernate.use_sql_comments"));
//        jpaProperties.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("hibernate.ejb.naming_strategy"));
//        jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));

        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }

//    @Bean
//    public EntityManager entityManager(){
//        EntityManager entityManager = entityManagerFactory(getDataSource()).getNativeEntityManagerFactory().createEntityManager();
//        return  entityManager;
//    }

    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
//    @Bean
//    public LocalSessionFactoryBean getSessionFactory(Environment env, DataSource dataSource) {
//        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
//        factoryBean.setDataSource(dataSource);
//
//        Properties props=new Properties();
//        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//        props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//
//        factoryBean.setHibernateProperties(props);
//        factoryBean.setAnnotatedClasses(User.class);
//        return factoryBean;
//    }

//    @Bean
//    public HibernateTransactionManager getTransactionManager(EntityManagerFactory entityManagerFactory) {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactoryBean.getObject());
//        return transactionManager;
//    }
}
