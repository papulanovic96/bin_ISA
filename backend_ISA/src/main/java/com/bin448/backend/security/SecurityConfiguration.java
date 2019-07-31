package com.bin448.backend.security;

import com.bin448.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private IsaAuthProvider isaAuth;
    @Autowired
    private UserService us;




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.authenticationProvider(isaAuth);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
                http.httpBasic().and().authorizeRequests()
                        .antMatchers("/user/getAirlines").permitAll()
                        .antMatchers("/Car/add").hasAnyRole("CAR_ADMIN","SYSTEM_ADMIN")
                        .antMatchers("/Car/remove/**").hasAnyRole("CAR_ADMIN","SYSTEM_ADMIN")
                        .antMatchers("/Car/reserve").hasAnyRole("USER","CAR_ADMIN")
                        .antMatchers("/Car/unreserve").hasAnyRole("CAR_ADMIN","USER")
                        .antMatchers("/Car/find/**","Car/findAll/**").permitAll()
                        .antMatchers("/carService/add").hasAnyRole("CAR_ADMIN","SYSTEM_ADMIN")
                        .antMatchers("/carService/remove/**").hasAnyRole("CAR_ADMIN","SYSTEM_ADMIN")
                        .antMatchers("/carService/find/**").permitAll()
                .and()
                .formLogin()
                .successHandler(new Redirect(us))
                        .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                 .deleteCookies("JSESSIONID")
                .logoutUrl("/logout")
                .permitAll();




    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/user/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void setIsaAuth(IsaAuthProvider customAuthProvider) {
        this.isaAuth = customAuthProvider;
    }
}
