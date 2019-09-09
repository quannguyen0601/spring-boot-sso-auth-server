package com.quan.demo.sso.demo.sso.configuration;

import com.quan.demo.sso.demo.sso.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

//    @Override
//    @Bean(BeanIds.USER_DETAILS_SERVICE)
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return super.userDetailsServiceBean();
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/h2-console/**","/oauth/**","/login/**","/login-page","/static/**")
                .permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                        .loginPage("/sso-login")
                        .permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean("userPasswordEncoder")
    PasswordEncoder userPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // BCryptPasswordEncoder(4) is used for users.password column
        auth.userDetailsService(userService).passwordEncoder(userPasswordEncoder());
    }
}
