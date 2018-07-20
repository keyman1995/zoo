package com.zoo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.zoo.util.PropUtil;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class ApplicationConfig {


    @Bean
    public ServletRegistrationBean druidServlet(){
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("loginUsername",PropUtil.getConfig("spring.datasource.username"));
        reg.addInitParameter("loginPassword",PropUtil.getConfig("spring.datasource.password"));
        reg.addInitParameter("logSlowSql",PropUtil.getConfig("spring.datasource.logSlowSql"));
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new WebStatFilter());
        filter.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filter.addInitParameter("profileEnable", "true");
        return filter;
    }


    @Bean(name="dataSource")
    public DataSource getDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(PropUtil.getConfig("spring.datasource.driver-class-name"));
        dataSource.setDbType(PropUtil.getConfig("spring.datasource.type"));
        dataSource.setUsername(PropUtil.getConfig("spring.datasource.username"));
        dataSource.setPassword(PropUtil.getConfig("spring.datasource.password"));
        dataSource.setUrl(PropUtil.getConfig("spring.datasource.url"));

        dataSource.setInitialSize(Integer.valueOf(PropUtil.getConfig("spring.datasource.initialSize")));
        dataSource.setMinIdle(Integer.valueOf(PropUtil.getConfig("spring.datasource.minIdle")));
        dataSource.setMaxActive(Integer.valueOf(PropUtil.getConfig("spring.datasource.maxActive")));
        dataSource.setTimeBetweenConnectErrorMillis(Long.valueOf(PropUtil.getConfig("spring.datasource.timeBetweenEvictionRunsMillis")));
        dataSource.setMinEvictableIdleTimeMillis(Long.valueOf(PropUtil.getConfig("spring.datasource.minEvictableIdleTimeMillis")));
        dataSource.setValidationQuery(PropUtil.getConfig("spring.datasource.validationQuery"));
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        try {
            dataSource.setFilters(PropUtil.getConfig("spring.datasource.filters"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
