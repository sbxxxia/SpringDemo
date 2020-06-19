package com.sbxxxia.web.config;

import com.sbxxxia.web.web.AuditingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@ComponentScan("com.sbxxxia.web")
@EnableTransactionManagement
public class AppConfig {
    private DataSource dataSource;

    public AppConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public FilterRegistrationBean<AuditingFilter> auditingFilterFilterRegistrationBean(){
        FilterRegistrationBean<AuditingFilter> registration = new FilterRegistrationBean<>();
        AuditingFilter filter = new AuditingFilter();
        registration.setFilter(filter);
        registration.setOrder(Integer.MAX_VALUE);
        registration.setUrlPatterns(Arrays.asList("/messages/*"));
        return registration;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        // 사용자 세션 관리 (DB)
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan("com.sbxxxia.web");
        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
