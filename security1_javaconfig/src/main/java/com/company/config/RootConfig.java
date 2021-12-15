package com.company.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/* <!-- task 패키지 : @Scheduled 활성화-->
<task:annotation-driven/> */
@EnableScheduling

/* <!--  스프링 트랜잭션 매니저 등록 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	<property name="dataSource" ref="ds"/>
</bean> */
@EnableTransactionManagement

/* <!-- mybatis 사용하는 Mapper interface, Mapper xml 활성화 -->
<mybatis-spring:scan base-package="com.company.mapper" /> */
@MapperScan("com.company.mapper")

/* <!-- @Autowired, @Inject, @Component, @Service, @Controller, @Repository 활성화 -->
<!-- 해당 annotation이 사용된 패키지를 SCAN하는 구문이 필요하다 -->
<context:component-scan base-package="com.company.service" /> 

<!-- task 패키지 : @Component 활성화-->
	<context:component-scan base-package="com.company.task"/> */
@ComponentScan({"com.company.service","com.company.task"})

@Configuration // 환경설정을 담당하는 파일임을 명시
public class RootConfig {

/* <!-- @Transactional 활성화 -->
<tx:annotation-driven/> */

@Bean //인스턴스(객체)를 생성하고 스프링 컨테이너가 관리
   public DataSourceTransactionManager txManager() {
      return new DataSourceTransactionManager(dataSource());
   }
	
 /* <bean id="sqlSessionFactory"
	      class="org.mybatis.spring.SqlSessionFactoryBean">
	      <property name="dataSource" ref="ds"></property>
	   </bean> */

@Bean //객체 생성  (영상 다시 보기: 의미 정리)
public SqlSessionFactory sqlSessionFactory() throws Exception {
	SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
	sqlSessionFactoryBean.setDataSource(dataSource());
	return sqlSessionFactoryBean.getObject();	
}


/* <!-- DBCP(DataBaseConnectionPulling):데이터베이스 커넥션 풀링 >> HikariCP -->
   <bean id="hikariConfig" class="com.zaxxer.hikari.HikariConfig"><!--외부 객체 불러오는 방식 -->
      <property name="driverClassName"
         value="oracle.jdbc.OracleDriver" />
      <property name="jdbcUrl"
         value="jdbc:oracle:thin:@localhost:1521:xe" />
      <property name="username" value="c##java" />
      <property name="password" value="12345" />
   </bean> => Hikari */

@Bean()
public DataSource dataSource() {
	HikariConfig hikariConfig = new HikariConfig();
	hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
	hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
	hikariConfig.setUsername("c##java");
	hikariConfig.setPassword("12345");
	
	HikariDataSource dataSource = new HikariDataSource(hikariConfig);
	return dataSource;
}

}
