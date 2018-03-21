package com.example.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.bookstore.services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final UserService userService;

    @Autowired
    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http
            .authorizeRequests() /* swagger ресурсы */
            .antMatchers(
                    "/swagger-ui.html", 
                    "/swagger-resources/**", 
                    "/webjars/**", 
                    "/v2/api-docs/**")
            .permitAll()
        .and()
            .authorizeRequests() /* регистрация */
            .antMatchers(
                    "/security/signin",
                    "/security/signup")
            .permitAll()
        .and()
            .authorizeRequests() /* авторы, книги */
            .antMatchers(HttpMethod.GET,
                    "/authors/*",
                    "/books", 
                    "/books/*")
            .permitAll()
        .and()
            .authorizeRequests() /* создание заказа */
            .antMatchers("/basket/submit")
            .authenticated()
        .and()
            .authorizeRequests() /* работа с корзиной */
            .antMatchers("/basket/**")
            .permitAll()
        .and()
            .authorizeRequests() /* все остальное */
            .antMatchers("/**")
            .authenticated()
        .and()
            .logout()
            .logoutUrl("/logout")
            .permitAll();
    }
}
