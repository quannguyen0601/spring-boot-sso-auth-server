package com.quan.demo.sso.demo.sso.configuration;

import com.quan.demo.sso.demo.sso.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    DataSource ds;

    @Autowired
    AuthenticationManager authMgr;

    @Autowired
    private UserService userService;

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(ds);
    }

    @Bean("clientPasswordEncoder")
    PasswordEncoder clientPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer cfg) throws Exception {

        //FIXME if you want to checkTokenAccess free
        //cfg.checkTokenAccess("permitAll()");

        // BCryptPasswordEncoder(12) is used for oauth_client_details.user_secret
        cfg.passwordEncoder(clientPasswordEncoder());

        // This will enable /oauth/check_token need Authenticated
        //
        cfg.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

                //clients.configure(clientDetailService);
       clients.jdbc(ds);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore());
        endpoints.authenticationManager(authMgr);
        endpoints.userDetailsService(userService);
    }


}
