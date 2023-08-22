package com.etz.MPB.portal.config;

import com.etz.MPB.portal.filter.CORSFilter;
import com.etz.MPB.portal.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@Slf4j
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter
{
    @Autowired
    // @Qualifier("myPasswordEncoder")
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private DataSource dataSource;

//    @Autowired
//    private CustomeAccessTokenConverter customeAccessTokenConverter;

  /*  @Value("${app.security.client-id}")
    private String clientId;
    @Value("${app.security.client-secret}")
    private String secret;
    @Value("${app.security.resource-id}")
    private String resourceId;
    @Value("${app.security.validity.access-token}")
    private int accessTokenValidity;
    @Value("${app.security.validity.refresh-token}")
    private int refreshTokenValidity;*/

    private final String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEA1226PnAaDUCg4vFIWUEtTQWedjolHPaVStGjeUBXBdO/fVaV\n" +
            "xHxO0THqlaiU1Vyz3A/ZMSYfE4l/J0QwCpwhOHyQEMJs0El5eSQhR6ytWyDld0Ng\n" +
            "NyPbQ2RH2sIelEMkWxm2J2BONMKd4L48NAMPv9pBsB6sIlZ8dVDuOQaSm7KOYfIa\n" +
            "vn+fhG0cbm5MXHJGrUDVHUD4qbIoke+pyOLOgA8EAHFosFQDocSFFuUYYVHK4Tvf\n" +
            "/ytoWv/qfu6gIbPF6f5dbAvVzV7vDmxne6UjGCZX6STl+dxMH0ylxK+bPijTrrAS\n" +
            "a4P+Ko51sbYbEsJ5a0h8NVMZ1IxVV80HEsk2wwIDAQABAoIBAEZvxhlsJdh3bSNG\n" +
            "qNUmDl3EnI8ja8cQiLex27VlaA6rsUyV5tobmtmjbIQKXr90VAp71BPR1w9YQY7h\n" +
            "KKQPcH07nQ8dwt5HZ8RIH+mDeBr3oo9gJDFjM+nQ+/12wvDPEzAGNByjzG5Lw7sb\n" +
            "+Cs5ZQjhn+Cp5rZDeOYS1BEpKP5mxbNpD9/wbcClMSe7TvCJ9NFofYQRxVf6BF1N\n" +
            "d4z4pzfKX9oU1U60Dgh7DeAdNBre+woFDtcxe2ycjhVfzwHKzf2T9oxmdaVal8uP\n" +
            "cqN8ahsnOPf8IRf6t41izKbHOjE7LTOWe+oiH8yCD+C/+ps4lysYo7gBoWkBIgfY\n" +
            "JrXEUiECgYEA8DTseMYb8DkMHfya01RrfksBCxGX3kVtRwIPd5LxyfzKXFdnQUa4\n" +
            "KemrJddj6ANMYxdckkRm4yMf8ltsTrqtiCSWALNYFVhzj+1iyHAw6ZVWVwMXB1SM\n" +
            "xTO1BncLIXlARjAVErl70BQ8UsV3BVBFKEdx5d1GbDhcIXkunCWLoI8CgYEA5Ze/\n" +
            "TCGUN5KksBvyEqvhcbQ81tiVcgONzSopQPKzj2EUrhsyZhFse9ty/nzHoDCuaJbJ\n" +
            "KuOfW54kLaNd8mGB0tEwHUE4YvxY+8CQEfzLXu8hxGiZA+tGQwMr4kh7mvnvyZQp\n" +
            "XXKwRmE66ChpxG8oLZbp9J8wjMvNb7x436ZVuI0CgYEAmInjSWb1Qv3XNgu9/knz\n" +
            "4UWcKf1IJfyMwrUKQXLtXMnNcAqJJajVNriO2P8JEMNXdO3W+YO6mq8pkGyCQRTH\n" +
            "MleZcmRxoUyERngHbSXhydPt+l+Z9iu5IVoOsUZTqTQonunV2Ag2GhNfo2iGhrdM\n" +
            "06FbMQ/tsrevugoMkVg+bP0CgYEAzwnvpRymxxKDVphszG+ZJmsNbgTzh1QzHG9H\n" +
            "slzqlYJLHtJBuY8+z9ZONbWaYxdjP8ex8u4+92R9ZXZrsrZBVzXy+H6m9YRwTd4R\n" +
            "TVc0ohBs1+wnCESzIJ0MhsssQ83dcV8dijpxQZZOg6pl7pD6n1n28fRX2K+0O22v\n" +
            "Zqddp8UCgYAYWljgTzO9sVh9YLNRiMIQozkUQYEZ1kR8h9ngvHKxcpv44SYi3lG2\n" +
            "zD6sCBv0UCtBag9kFgCZNDhaCljOKJlA6kHPmpGpRhCCLjc8YYX0sAYMTFoTT3Pl\n" +
            "eAmX+Sgp1B5CUYjXhfZmyCQAJguJi0zpQ0j82f8seVeXnWspgdyr7w==\n" +
            "-----END RSA PRIVATE KEY-----";
    private final String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1226PnAaDUCg4vFIWUEt\n" +
            "TQWedjolHPaVStGjeUBXBdO/fVaVxHxO0THqlaiU1Vyz3A/ZMSYfE4l/J0QwCpwh\n" +
            "OHyQEMJs0El5eSQhR6ytWyDld0NgNyPbQ2RH2sIelEMkWxm2J2BONMKd4L48NAMP\n" +
            "v9pBsB6sIlZ8dVDuOQaSm7KOYfIavn+fhG0cbm5MXHJGrUDVHUD4qbIoke+pyOLO\n" +
            "gA8EAHFosFQDocSFFuUYYVHK4Tvf/ytoWv/qfu6gIbPF6f5dbAvVzV7vDmxne6Uj\n" +
            "GCZX6STl+dxMH0ylxK+bPijTrrASa4P+Ko51sbYbEsJ5a0h8NVMZ1IxVV80HEsk2\n" +
            "wwIDAQAB\n" +
            "-----END PUBLIC KEY-----";


    @Bean
    CORSFilter corsFilter(){
        CORSFilter cors = new CORSFilter();
        return cors;
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security)  {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }




    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }

    //    // ...
    private AuthenticationManager authenticationManagerBean;

    @Autowired
    public void setAuthenticationManagerBean(AuthenticationConfiguration authenticationManagerBean) throws Exception {
        this.authenticationManagerBean = authenticationManagerBean.getAuthenticationManager();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManagerBean).tokenStore(tokenStore()).accessTokenConverter(tokenEnhancer());
    }

    @Bean("myPasswordEncoder")
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public PasswordEncoder passwordEncoder(){
        DelegatingPasswordEncoder delegatingPasswordEncoder =(DelegatingPasswordEncoder)PasswordEncoderFactories.createDelegatingPasswordEncoder();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(bCryptPasswordEncoder);
        return delegatingPasswordEncoder;
    }

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new CustomeAccessTokenConverter(userRepository);
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

}
