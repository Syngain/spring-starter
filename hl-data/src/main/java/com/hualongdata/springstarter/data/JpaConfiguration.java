package com.hualongdata.springstarter.data;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by yangbajing on 16-9-5.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.hualongdata.springstarter.data.repository")
@EntityScan("com.hualongdata.springstarter.data.model")
public class JpaConfiguration {
//    @Bean
//    public DataSource dataSource() {
//        ds = new HikariDataSourcePoolMetadata();
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.HSQL)
//                .build();
//    }
//
//    @Bean
//    public EntityManagerFactory entityManagerFactory() {
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setDatabase(Database.POSTGRESQL);
////        vendorAdapter.setGenerateDdl(true);
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("com.hualongdata.springstarter.data.model");
//        factory.setDataSource(dataSource());
//        factory.afterPropertiesSet();
//
//        return factory.getObject();
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory());
//        return txManager;
//    }

}
