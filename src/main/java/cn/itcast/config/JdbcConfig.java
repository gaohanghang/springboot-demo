package cn.itcast.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2018/12/02 3:42 PM
 */
//@Configuration
//@PropertySource("classpath:/jdbc.properties")
//@EnableConfigurationProperties(JdbcProperties.class)
public class JdbcConfig {

    // @Value("${jdbc.url}")
    // String url;
    // @Value("${jdbc.driverClassName}")
    // String driverClassName;
    // @Value("${jdbc.username}")
    // String username;
    // @Value("${jdbc.password}")
    // String password;

   /* @Autowired
    JdbcProperties jdbcProperties;

    public JdbcConfig(JdbcProperties jdbcProperties) {
        this.jdbcProperties = jdbcProperties;
    }*/

   /* @Bean
    public DataSource dataSource(JdbcProperties prop) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(prop.getDriverClassName());
        dataSource.setUrl(prop.getUrl());
        dataSource.setUsername(prop.getUsername());
        dataSource.setPassword(prop.getPassword());
        return dataSource;
    }*/

   // @Bean
   // @ConfigurationProperties(prefix = "jdbc")
   //  public DataSource dataSource() {
   //      return new DruidDataSource();
   //  }
}
