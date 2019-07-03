package com.mi.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengwen on 2019/7/3.
 */
@Configuration
public class DruidConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid(){
        return new DruidDataSource();
    }

    //配置Druid的监控
    //1、配置一个管理后台的servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");

        Map<String,String> initParms = new HashMap<>();
        initParms.put("loginUsername","admin");
        initParms.put("loginPassword","123456");
        initParms.put("allow",""); //默认是允许所有访问
        initParms.put("deny","192.168.15.21"); //不允许访问
        bean.setInitParameters(initParms);
        return bean;
    }
    //2、配置一个监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String,String> initParms = new HashMap<>();
        initParms.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParms);

        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;
    }
}
