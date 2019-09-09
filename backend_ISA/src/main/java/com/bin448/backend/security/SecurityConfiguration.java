package com.bin448.backend.security;

import com.bin448.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private IsaAuthProvider isaAuth;
    @Autowired
    private UserService us;
    @Autowired
    private CustomAuthenticationEntryPoint authenticationEntryPoint;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.authenticationProvider(isaAuth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // http.addFilterBefore(new CustomFilter(), ChannelProcessingFilter.class);

        http.httpBasic().authenticationEntryPoint(authenticationEntryPoint).and().authorizeRequests();
        http.httpBasic().and().authorizeRequests()
                .antMatchers("/user/sendFriendRequest/**").permitAll()
                .antMatchers("/user/acceptFriendRequest/**").permitAll()
                .antMatchers("/user/declineFriendRequest/**").permitAll()
                .antMatchers("/user/deleteFriendRequest/**").permitAll()
                .antMatchers("/user/findAll/**").permitAll()
                .antMatchers("/user/modify/**").permitAll()
                .antMatchers("/user/getUser/**").permitAll()
                .antMatchers("/user/getFriends/**").permitAll()
                .antMatchers("/user/getRequests/**").permitAll()
                .antMatchers("/user/getReceivedRequests/**").permitAll()
                .antMatchers("/user/getAirlines").permitAll()
                .antMatchers("/Car/add").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/Car/remove/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/Car/rateCar").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN", "USER")
                .antMatchers("/Car/reserve").hasAnyRole("USER", "CAR_ADMIN")
                .antMatchers("/Car/getAllCars").hasAnyRole("CAR_ADMIN", "USER")
                .antMatchers("/Car/tryToUnreserve/**").hasAnyRole("CAR_ADMIN", "USER")
                .antMatchers("/Car/deleteRes/**").hasAnyRole("CAR_ADMIN", "USER")
                .antMatchers("/Car/modifyCar/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/Car/IsUserReservedCar/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN", "USER")
                .antMatchers("/Car/IsUserRated/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN", "USER")
                .antMatchers("/Car/getReservedCars/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN", "USER")
                .antMatchers("/carService/tryToRate/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN", "USER")
                .antMatchers("/Car/tryToRate/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN", "USER")
                .antMatchers("/Car/getAvgGrade/**").permitAll()
                .antMatchers("/Car/find/**", "Car/findAll/**").permitAll()
                .antMatchers("/carService/add").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/carService/rateCarService").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN", "USER")
                .antMatchers("/carService/addItem").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/carService/deleteItem/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/carService/modifyItem/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                //mozda cak staviti u webignoring
                .antMatchers("/carService/getAllItems/**").hasAnyRole("CAR_ADMIN", "USER", "SYSTEM_ADMIN")
                .antMatchers("/carService/remove/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/carService/getItem/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/carService/modifyCarService/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/carService/find/**").permitAll()
                .antMatchers("/carService/getAvgGrade/**").permitAll()
                .antMatchers("/seats/findAll").permitAll()
                .antMatchers("/seats/findById/**").permitAll()
                .antMatchers("/seats/addSeat").permitAll()
                .antMatchers("/seats/deleteSeat/**").permitAll()
                .antMatchers("/seats/modifySeat/**").permitAll()
                .antMatchers("/airline/findAll").permitAll()
                .antMatchers("/airline/findById/**").permitAll()
                .antMatchers("/airline/create").permitAll()
                .antMatchers("/airline/delete/**").permitAll()
                .antMatchers("/airline/modify/**").permitAll()
                .antMatchers("/flight/findAll").permitAll()
                .antMatchers("/flight/create").permitAll()
                .antMatchers("/reservation").hasAnyRole("USER")
                .antMatchers(HttpMethod.DELETE, "/room/**").hasAnyRole("HOTEL_ADMIN")
                .antMatchers(HttpMethod.POST, "/room/setReservation").permitAll()
                .antMatchers(HttpMethod.PUT, "/room/**").hasAnyRole("HOTEL_ADMIN")
                .antMatchers(HttpMethod.GET, "/room/**").permitAll()
                .antMatchers(HttpMethod.POST, "/room/addRoom").hasAnyRole("HOTEL_ADMIN")
                .antMatchers(HttpMethod.POST, "/hotel/**").hasAnyRole("HOTEL_ADMIN")
                .antMatchers(HttpMethod.PUT, "/hotel/**").hasAnyRole("HOTEL_ADMIN")
                .antMatchers(HttpMethod.GET, "/hotel/addMenuItem/**").hasAnyRole("HOTEL_ADMIN")
                .antMatchers(HttpMethod.GET, "/hotel/removeMenuItem/**").hasAnyRole("HOTEL_ADMIN")
                .antMatchers(HttpMethod.GET, "/hotel/**").permitAll()
                .antMatchers(HttpMethod.POST, "/newRoomPrice/checkExistence").hasAnyRole("HOTEL_ADMIN")
                .antMatchers(HttpMethod.POST, "/newRoomPrice/**").hasAnyRole("HOTEL_ADMIN")
                .anyRequest()
                .fullyAuthenticated()
        ;


    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/user/register");
        web.ignoring().antMatchers("/user/register/**");
        web.ignoring().antMatchers("/user/register/");
        web.ignoring().antMatchers("/user/validateLogin/**");
        web.ignoring().antMatchers("/Car/search/**");
        web.ignoring().antMatchers("/carService/search/**");
        web.ignoring().antMatchers("/user/get/**");
        web.ignoring().antMatchers("/user/confirm-account?token=**");
        web.ignoring().antMatchers("/user/confirm-account?**");
        web.ignoring().antMatchers("/user/confirm-account**");
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
