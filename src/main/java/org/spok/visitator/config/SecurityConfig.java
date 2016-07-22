package org.spok.visitator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 //       auth.inMemoryAuthentication().withUser("student").password("student").roles("STUDENT");
 //       auth.inMemoryAuthentication().withUser("teacher").password("teacher").roles("TEACHER");
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/**/new").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/**/edit").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and().formLogin()
                 .defaultSuccessUrl("/", false)
                	.loginPage("/login")
                		.and().logout().logoutSuccessUrl("/")
                	 .and().csrf().disable();

    }
}
