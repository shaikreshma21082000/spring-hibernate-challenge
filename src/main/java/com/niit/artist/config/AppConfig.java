package com.niit.artist.config;
import com.niit.artist.model.Artist;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/*This class will contain the application-context for the application.
 * Define the following annotations:
 * @Configuration - Annotating a class with the @Configuration indicates that the
 *                  class can be used by the Spring IoC container as a source of
 *                  bean definitions
 * @EnableTransactionManagement - Enables Spring's annotation-driven transaction management capability.
 *
 * */
@Configuration
@EnableTransactionManagement
public class AppConfig {
     /*
            Use this configuration while submitting solution in hobbes.
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://" + System.getenv("MYSQL_HOST") + ":3306/" + System.getenv("MYSQL_DATABASE")
                    +"?verifyServerCertificate=false&useSSL=false&requireSSL=false");
            dataSource.setUsername(System.getenv("MYSQL_USER"));
            dataSource.setPassword(System.getenv("MYSQL_PASSWORD")); */

    /****************************************************************************/
    /*
     * Define the bean for DataSource(MYSQL). To create the DataSource bean, the below is needed:
     * 1. Driver class name
     * 2. Database URL
     * 3. UserName
     * 4. Password
     */
    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //set the url to your local url
        ds.setUrl("jdbc:mysql://localhost:3306/artistsdb?useSSL=false");
        //set the username
        ds.setUsername("root");
        //set the password
        ds.setPassword("password@123");
        return ds;
    }
    /*
     * Define the bean for SessionFactory. Hibernate SessionFactory is the factory
     * class through which we get sessions and perform database operations.
     */
    @Bean
    public LocalSessionFactoryBean getSessionFactory(DataSource ds) throws IOException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(ds);
        sessionFactory.setPackagesToScan("com.niit.artist.model");
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        sessionFactory.setHibernateProperties(properties);
        sessionFactory.setAnnotatedClasses(Artist.class);
        sessionFactory.afterPropertiesSet();
        return sessionFactory;
    }
    /*
     * Define the bean for Transaction Manager. HibernateTransactionManager handles
     * transaction in Spring. The application that uses single hibernate session
     * factory for database transaction has good choice to use
     * HibernateTransactionManager. HibernateTransactionManager can work with plain
     * JDBC too. HibernateTransactionManager allows bulk update and bulk insert and
     * ensures data integrity.
     */
    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}

