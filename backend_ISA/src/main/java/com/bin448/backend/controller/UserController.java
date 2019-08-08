package com.bin448.backend.controller;

import com.bin448.backend.converter.UserConverter;
import com.bin448.backend.entity.ConfirmationToken;
import com.bin448.backend.entity.DTOentity.AirlineDTO;
import com.bin448.backend.entity.DTOentity.FlightDTO;
import com.bin448.backend.entity.DTOentity.UserDTO;
import com.bin448.backend.entity.Flight;
import com.bin448.backend.entity.User;
import com.bin448.backend.repository.ConfirmationTokenRepository;
import com.bin448.backend.service.AirlineService;
import com.bin448.backend.service.EmailSenderService;
import com.bin448.backend.service.UserService;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@CrossOrigin
public class UserController {

    private UserService us;
    private AirlineService as;
    private ConfirmationTokenRepository confirmationTokenRepository;
    private EmailSenderService emailSenderService;


    public UserController(AirlineService as, UserService us,EmailSenderService em,ConfirmationTokenRepository c) {
        this.as = as;
        this.us = us;
        this.confirmationTokenRepository = c;
        this.emailSenderService = em;

    }




    @RequestMapping(value = { "/validateLogin/{username},{password}" },method = POST)
    public boolean validateLogin(@PathVariable String username, @PathVariable String password)
    {
        User u = us.getUserByUsername(username);
        if(u!=null){
            PasswordEncoder bCrypt = new BCryptPasswordEncoder();
            if (bCrypt.matches(password,u.getPassword()) && u.isActive()==true){
                return true;
            }
        }
        return false;
    }


    @RequestMapping(value="/user/register", method = RequestMethod.POST)
    public boolean registerUser(ModelAndView modelAndView, @RequestBody  UserDTO user)
    {

           return emailSenderService.mailSendWhenRegister(modelAndView,user);

    }


    @RequestMapping(value="/user/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
       return emailSenderService.accountConfimation(modelAndView,confirmationToken);
    }

    @RequestMapping(value = "/user/getAirlines", method = GET)
    public ResponseEntity<List<AirlineDTO>> getAirline(){

        return ResponseEntity.ok(as.findAll());

    }
    @RequestMapping(value = "/user/getUser/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username){
        return ResponseEntity.ok(UserConverter.fromEntity(us.getUserByUsername(username)));
    }




}


