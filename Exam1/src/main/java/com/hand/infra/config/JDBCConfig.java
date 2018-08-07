package com.hand.infra.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import java.io.IOException;


@Configuration
@MapperScan(basePackages = "com.hand.infra.mapper")
@ComponentScan(basePackages = "com.hand")
@EnableTransactionManagement
@EnableAspectJAutoProxy
@PropertySource(value = "classpath:db.properties")
public class JDBCConfig implements TransactionManagementConfigurer {

	@Value("${mysql.driver}")
	private String driver;

	@Value("${mysql.dbName}")
	private String dbName;

	@Value("${mysql.initialSize}")
	private int initialSize;

	@Value("${mysql.minIdle}")
	private int minIdle;

	@Value("${mysql.maxActive}")
	private int maxActive;

	@Value("${mysql.maxWait}")
	private int maxWait;

	@Bean(name = "druidDataSource")
	public DruidDataSource getDruid() {
		DruidDataSource druidDataSource = new DruidDataSource();
//		druidDataSource.setDriverClassName(driver);
//		druidDataSource.setUrl("jdbc:mysql://localhost:3306/"+dbName+"?serverTimezone=UTC&useSSL=false");
//		druidDataSource.setUsername("root");
//		druidDataSource.setPassword("123456");
		druidDataSource.setDriverClassName(driver);
		druidDataSource.setUrl("jdbc:mysql://"+System.getenv("IP")+":3306/"+dbName+"?serverTimezone=UTC");
		druidDataSource.setUsername(System.getenv("USERNAME"));
		druidDataSource.setPassword(System.getenv("PASSWORD"));
		druidDataSource.setMaxActive(maxActive);
		druidDataSource.setMinIdle(minIdle);
		druidDataSource.setInitialSize(initialSize);
		druidDataSource.setMaxWait(maxWait);
		return druidDataSource;
	}

	@Bean(name = "sqlSessionFactoryBean")
	public SqlSessionFactoryBean getSessionFactory(DruidDataSource druidDataSource) throws IOException {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setConfigLocation(new ClassPathResource("applicationContext-mybatis.xml"));
		factory.setMapperLocations(resourcePatternResolver.getResources("classpath:mapper/*.xml"));
		factory.setDataSource(druidDataSource);
		return factory;
	}

	@Bean(name = "dataSourceTransactionManager")
	public DataSourceTransactionManager getTransactionManager(DruidDataSource druidDataSource) {
		DataSourceTransactionManager manager = new DataSourceTransactionManager();
		manager.setDataSource(druidDataSource);
		return manager;
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return getTransactionManager(getDruid());
	}

}