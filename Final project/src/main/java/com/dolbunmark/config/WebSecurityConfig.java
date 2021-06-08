package com.dolbunmark.config;

import com.dolbunmark.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http
                .authorizeRequests()
//                .antMatchers("/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/").hasAnyRole(Role.Manager.name(), Role.Manager.name()) //
                .antMatchers(HttpMethod.POST, "/").hasAnyRole(Role.Manager.name(), Role.Manager.name()) //
                .anyRequest().authenticated()
                .and()
                .httpBasic()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .passwordParameter("password")
//                .usernameParameter("customer")
//                .defaultSuccessUrl("/login", false)
                .and().logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login");
    }

//    @Bean
//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return new InMemoryUserDetailsManager(
//                User.builder()
//                        .username("v@gmail.com")
//                        .password("1Asdf!")
//                        .roles(Role.Manager.name())
//                        .build()
//        );
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("*");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select email, password, true from user where email=?")
                .authoritiesByUsernameQuery("select email, 'ROLE_USER' from user where email =?");
    }
}