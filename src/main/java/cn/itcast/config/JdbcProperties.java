package cn.itcast.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2018/12/02 4:03 PM
 */
//@ConfigurationProperties(prefix = "jdbc")
public class JdbcProperties {
    String url;
    String driverClassName;
    String username;
    String password;
    User user = new User();

    class User{
        String name;
        int age;
        List<String> language;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
