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
                .antMatchers("/user/invite/**").permitAll()
                .antMatchers("/ticket/findAll").permitAll()
                .antMatchers("/ticket/create").permitAll()
                .antMatchers("/ticket/reserve/**").permitAll()
                .antMatchers("/user/getUser/**").permitAll()
                .antMatchers("/user/getFriends/**").permitAll()
                .antMatchers("/user/getRequests/**").permitAll()
                .antMatchers("/user/getReceivedRequests/**").permitAll()
                .antMatchers("/user/getAirlines").permitAll()
                .antMatchers("/Car/add").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/Car/remove/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/Car/rateCar").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN", "USER")
                .antMatchers("/Car/reserve").hasAnyRole("USER", "CAR_ADMIN")
                .antMatchers("/Car/tryToUnreserve/**").hasAnyRole("CAR_ADMIN", "USER")
                .antMatchers("/Car/getDiscount").hasAnyRole("CAR_ADMIN", "USER", "SYSTEM_ADMIN")

                .antMatchers("/Car/deleteRes/**").hasAnyRole("CAR_ADMIN", "USER")
                .antMatchers("/Car/modifyCar/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/Car/setDiscount").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/Car/IsUserReservedCar/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN", "USER")
                .antMatchers("/Car/IsUserRated/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN", "USER")
                .antMatchers("/Car/getReservedCars/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN", "USER")
                .antMatchers("/carService/tryToRate/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN", "USER")
                .antMatchers("/Car/tryToRate/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN", "USER")
                .antMatchers("/Car/getAvgGrade/**").permitAll()
                .antMatchers("/Car/find/**").permitAll()
                .antMatchers("/Car/getAvailable/**").permitAll()

                .antMatchers("/carService/add").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/carService/rateCarService").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN", "USER")
                .antMatchers("/carService/addItem").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/carService/deleteItem/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/carService/modifyItem/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/carService/office/add").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/carService/office/delete/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/carService/office/modify/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/carService/updateProfit/**").hasAnyRole("USER")

                //mozda cak staviti u webignoring
                .antMatchers("/carService/remove/**").hasAnyRole("CAR_ADMIN", "SYSTEM_ADMIN")
                .antMatchers("/carService/totalPrice/**").hasRole("USER")
                .antMatchers("/carService/getPrice/**").hasRole("USER")

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
                .antMatchers("/airline/rate/**").hasRole("USER")
                .antMatchers("/flight/rate/**").hasRole("USER")
                .antMatchers("/flight/findAll").permitAll()
                .antMatchers("/flight/create").permitAll()
                .antMatchers("/flight/delete/**").permitAll()
                .antMatchers("/flight/check/**").permitAll()
                .antMatchers("/reservation").hasAnyRole("USER")
                .antMatchers("/reservation/check/**").hasAnyRole("USER")
                .antMatchers(HttpMethod.DELETE, "/room/**").hasAnyRole("HOTEL_ADMIN")
                .antMatchers(HttpMethod.POST, "/room/setReservation").permitAll()
                .antMatchers(HttpMethod.PUT, "/room/**").hasAnyRole("HOTEL_ADMIN")
                .antMatchers(HttpMethod.GET, "/room/rate/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/room/**").permitAll()
                .antMatchers(HttpMethod.POST, "/room/addRoom").hasAnyRole("HOTEL_ADMIN")
                .antMatchers(HttpMethod.POST, "/hotel/**").hasAnyRole("HOTEL_ADMIN")
                .antMatchers(HttpMethod.PUT, "/hotel/**").hasAnyRole("HOTEL_ADMIN")
                .antMatchers(HttpMethod.GET, "/hotel/addMenuItem/**").hasAnyRole("HOTEL_ADMIN")
                .antMatchers(HttpMethod.GET, "/hotel/removeMenuItem/**").hasAnyRole("HOTEL_ADMIN")
                .antMatchers(HttpMethod.POST, "/newRoomPrice/**").hasAnyRole("HOTEL_ADMIN")
                .antMatchers(HttpMethod.PUT, "/newRoomPrice/validDiscounts").permitAll()
                .anyRequest()
                .fullyAuthenticated();


    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/Car/getAllCars");
        web.ignoring().antMatchers("/Car/findAll/**");
        web.ignoring().antMatchers("/carService/office/getOne/**");
        web.ignoring().antMatchers("/carService/office/getAll/**");
        web.ignoring().antMatchers("/carService/getAllCarServices/**");
        web.ignoring().antMatchers("/user/register");
        web.ignoring().antMatchers("/user/register/**");
        web.ignoring().antMatchers("/user/register/");
        web.ignoring().antMatchers("/user/validateLogin/**");
        web.ignoring().antMatchers("/Car/search/**");
        web.ignoring().antMatchers("/Car/types");
        web.ignoring().antMatchers("/carService/getAllItems/**");

        web.ignoring().antMatchers("/carService/search/**");
        web.ignoring().antMatchers("/user/get/**");
        web.ignoring().antMatchers("/user/confirm-account?token=**");
        web.ignoring().antMatchers("/user/confirm-account?**");
        web.ignoring().antMatchers("/user/confirm-account**");
        web.ignoring().antMatchers(HttpMethod.GET, "/hotel/**");
//        web.ignoring().antMatchers(HttpMethod.PUT, "/hotel/findHotels/**");
//        web.ignoring().antMatchers(HttpMethod.PUT, "/hotel/date");

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
