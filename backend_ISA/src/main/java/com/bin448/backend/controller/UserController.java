package com.bin448.backend.controller;

import com.bin448.backend.converter.UserConverter;
import com.bin448.backend.entity.DTOentity.AirlineDTO;
import com.bin448.backend.entity.DTOentity.UserDTO;
import com.bin448.backend.entity.User;
import com.bin448.backend.repository.ConfirmationTokenRepository;
import com.bin448.backend.service.AirlineService;
import com.bin448.backend.service.EmailSenderService;
import com.bin448.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@CrossOrigin
@RequestMapping("/user")
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


    @RequestMapping(value="/register", method = RequestMethod.POST)
    public boolean registerUser(ModelAndView modelAndView, @RequestBody  UserDTO user)
    {

        return emailSenderService.mailSendWhenRegister(modelAndView,user);

    }


    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        return emailSenderService.accountConfimation(modelAndView,confirmationToken);
    }

    @RequestMapping(value = "/getAirlines", method = GET)
    public ResponseEntity<List<AirlineDTO>> getAirline(){

        return ResponseEntity.ok(as.findAll());

    }
    @RequestMapping(value = "/getUser/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username){
        return new ResponseEntity<>(UserConverter.fromEntity(us.getUserByUsername(username)), HttpStatus.OK);
    }

    @RequestMapping(value = "/sendFriendRequest/{username}", method = POST)
    public ResponseEntity<String> sendFriendRequest(@PathVariable String username){
        User u = us.getUserByUsername(username);
        us.sendRequest(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/acceptFriendRequest", method = POST)
    public ResponseEntity<String> acceptFriendship(){
        us.acceptFriendship();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
