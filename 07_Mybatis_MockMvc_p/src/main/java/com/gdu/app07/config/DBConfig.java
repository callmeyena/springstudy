package com.gdu.app07.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
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

@Configuration						// 스프링 설정 파일
@PropertySource(value={"classpath:application.properties"}) 	// Environment 인터페이스에 application.properties값이 자동으로 저장된다.
@EnableTransactionManagement		// 1) @transactional라는 애너테이션을 달고있는 클래스(외부클래스)를 찾아서 선언적 트랜잭션 처리를 허용하고, 
									// 2) 같은 클래스 내부에서 transactionManager 메소드를 사용한 곳에 트랜잭션을 허용한다.
public class DBConfig {
	
	@Autowired						// 클래스간의 의존관계를 스프링 컨테이너가 자동으로 연결해주는 애너테이션
	private Environment env;		// @PropertySource를 통해 env에 자동으로 프로퍼티와 밸류가 저장된다
		
	@Bean							// HikariConfig: DB와 스프링 연결해주기 => DB Connection Pool작업
	public HikariConfig hikariConfig() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(env.getProperty("spring.datasource.hikari.driver-class-name"));
		hikariConfig.setJdbcUrl(env.getProperty("spring.datasource.hikari.jdbc-url"));
		hikariConfig.setUsername(env.getProperty("spring.datasource.hikari.username"));
		hikariConfig.setPassword(env.getProperty("spring.datasource.hikari.password"));
		return hikariConfig;
	}
	
	@Bean(destroyMethod="close")	// 빈 객체의 역할이 끝나면 close하겠다 => hikariConfig()메소드의 set메소드가 끝나면 close한다. => hikariConfig()를 관리하고 자원 반납까지 하겠다
	public HikariDataSource hikariDataSource() {
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean 							// 마이바티스와 스프링 연결해주기
	public SqlSessionFactory sqlSessionFactory() throws Exception {		// 데이터 접속 실패시 발생하는 예외를 throws Exception으로 미리 처리한다
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();		// sqlSessionfactory랑 이름만 다르나 같은 역할 => spring만을 위한 클래스
		bean.setDataSource(hikariDataSource());							// 마이바티스 + 스프링 + DB와도 연결 완
		bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(env.getProperty("mybatis.config-location"))); 		// 아래 주석 참고
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));	// 아래 주석 참고
		// pathMatchingResourcePatternResolver()메소드로 인해 마이바티스 configLocation과 mapperLocations 경로 분석 완 => 마이바티스 설정 파일이 됨
		return bean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception { 	// sqlSession과 이름만 다르나 같은 역할 => spring만을 위한 클래스2
		return new SqlSessionTemplate(sqlSessionFactory());				// DAO로 호출 할 수 있다
		}
	
	@Bean
	public TransactionManager transactionManager() {					// transactionManager = transaction 처리 해주는 메소드
		return new DataSourceTransactionManager(hikariDataSource());	// jdbc 연동모듈이 ojdbc8 => maven에서 다운 받았음 => 데이터베이스에 접근할 수 있는 hikaridatasource를 통해 트랜잭션 처리
	}
}
