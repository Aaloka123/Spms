package com.spms.config;

import org.springframework.beans.factory.annotation.Qualifier;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration//marks as a spring configuration class (creates beans)
@EnableTransactionManagement//enables @transactional support
// Tells Spring:
// - scan repositories only in auth.repository package
// - those repos must use authEntityManagerFactory
// - those repos must use authTransactionManager
@EnableJpaRepositories(
        basePackages = "com.spms.auth.repository",
        entityManagerFactoryRef = "authEntityManagerFactory",
        transactionManagerRef = "authTransactionManager"
)

public class AuthDataSourceConfig {

    // Reads DB settings from application.properties:
    // spring.datasource.auth.url / username / password / driver
    @Bean
    @ConfigurationProperties("spring.datasource.auth")
    public DataSourceProperties authDataSourceProperties(){
       return new DataSourceProperties();
    }

    // Creates the actual connection pool to spms_auth_db
    @Bean
    public DataSource authDataSource(){
        return authDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    // Creates JPA EntityManagerFactory for AUTH entities only (User, Role)
    @Bean
    public LocalContainerEntityManagerFactoryBean authEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em =
                new LocalContainerEntityManagerFactoryBean();

        // Use the auth database connection
        em.setDataSource(authDataSource());

        // Scan only auth entities (IMPORTANT: not Product)
        em.setPackagesToScan("com.spms.auth.entity");

        // Name this persistence unit "auth"
        em.setPersistenceUnitName("auth");

        // Use Hibernate as JPA provider
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Hibernate settings for this auth DB only
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "update"); // create/update tables
        props.put("hibernate.show_sql", "true");       // print SQL in console
        props.put("hibernate.format_sql", "true");     // pretty SQL
        em.setJpaPropertyMap(props);
        return em;
    }
    // Handles commit/rollback for auth DB operations
    @Bean
    public PlatformTransactionManager authTransactionManager(
            @Qualifier("authEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}