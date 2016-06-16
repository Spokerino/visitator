package org.spok.visitator.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;


@Configuration
public class DataConfig {

	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
//		ds.setUrl("jdbc:mysql://127.7.78.130:3306/visitator");
		ds.setUrl("jdbc:mysql://localhost:3306/visitator");
		ds.setUsername("root");
		ds.setPassword("vangUar7");
		ds.setInitialSize(5);
		ds.setMaxTotal(10);
		return ds;
	}
	
	@Bean
	public JdbcOperations jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
