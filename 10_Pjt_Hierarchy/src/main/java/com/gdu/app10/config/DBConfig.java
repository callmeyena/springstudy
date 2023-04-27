package com.gdu.app10.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@MapperScan(basePackages={"com.gdu.app10.mapper"})					// @Mapper가 존재하는 패키지를 작성한다.
@PropertySource(value={"classpath:application.properties"})			// 프로퍼티 파일을 읽어온다 => application.properties 파일의 속성을 읽어오자!
@EnableTransactionManagement										// 트랜잭션을 하려면 트랜잭션매니저의 허용이 필요하다 ==> 트랜잭션 처리를 허용한다.
@Configuration
public class DBConfig {
	
	@Autowired
	private Environment env;	// Spring이 가지고 있는 Bean
	
	// HikariConfig Bean (application properties 값을 불러와서 여기로 두겠다/ DB property를 분리한 이유는 gitignore파일을 쓰지 않기 위해서(application.properties는 깃에 안올릴거임)
	@Bean
	public HikariConfig hikariConfig() {				
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(env.getProperty("spring.datasource.hikari.driver-class-name"));		// application.properties의 1번라인 oracle.jdbc.OracleDriver 값을 받아옴
		hikariConfig.setJdbcUrl(env.getProperty("spring.datasource.hikari.jdbc-url"));
		hikariConfig.setUsername(env.getProperty("spring.datasource.hikari.username"));
		hikariConfig.setPassword(env.getProperty("spring.datasource.hikari.password"));
		return hikariConfig;
	}
	
	// DriverManagerDataSource ==> HikariDataSource Bean으로 사용
	@Bean(destroyMethod = "close")
	public HikariDataSource hikariDataSource() {
		return new HikariDataSource(hikariConfig());
	}
	
	// SqlSessionFactory Bean
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(hikariDataSource());
		bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(env.getProperty("mybatis.config-location")));
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
		return bean.getObject();
	}
	
	
	// SqlSessionTemplate Bean	(기존의 SqlSession)
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	// TransactionManager Bean
	@Bean
	public TransactionManager transactionManager() {
		return new DataSourceTransactionManager(hikariDataSource());
	}

}
