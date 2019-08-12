package com.hlhealth.user.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;

@Configuration
public class Config {

	@Autowired
	Environment environment;

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		return druidDataSource;
	}

	@Primary
	@Bean("dataSourceProxy")
	public DataSourceProxy dataSourceProxy(DataSource dataSource) {
		return new DataSourceProxy(dataSource);
	}

	@Bean(name = "sessionFactory")
	@ConditionalOnBean(DataSourceProxy.class)
	public SqlSessionFactory sessionFactory(DataSourceProxy dataSourceProxy) throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSourceProxy);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactoryBean.setConfigLocation(resolver.getResource(environment.getProperty("mybatis.config-location")));
		sessionFactoryBean
				.setMapperLocations(resolver.getResources(environment.getProperty("mybatis.mapper-locations"))); // *Mapper.xml位置
		
		return sessionFactoryBean.getObject();
	}
}
