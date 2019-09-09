package com.bin448.backend.controller;

import com.bin448.backend.converter.UserConverter;
import com.bin448.backend.entity.DTOentity.AirlineDTO;
import com.bin448.backend.entity.DTOentity.FrendshipDTO;
import com.bin448.backend.entity.DTOentity.UserDTO;
import com.bin448.backend.entity.Friendship;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
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

    @GetMapping("/get/{username}")
    public UserDTO findUser(@PathVariable String username){
        return UserConverter.fromEntity(us.getUserByUsername(username));
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

    @RequestMapping(value = "/getFriends/{username}", method = GET)
    public ResponseEntity<List<String>> getFriends(@PathVariable String username){
        User u = us.getUserByUsername(username);
        UserDTO uD = UserConverter.fromEntity(u);
        List<String> fList = uD.getUsernameOfFriend();
        return new ResponseEntity<>(fList, HttpStatus.OK);
    }

    @RequestMapping(value = "/getRequests/{username}", method = GET)
    public ResponseEntity<List<String>> getRequests(@PathVariable String username){
        us.getMyReceivedRequests(username);
        return new ResponseEntity<>(us.getMyReceivedRequests(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/sendFriendRequest/{username1}, {username2}", method = POST)
    public ResponseEntity<String> sendFriendRequest(@PathVariable String username1, @PathVariable String username2){
        us.sendRequest(username1, username2);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/acceptFriendRequest/{username1}, {username2}", method = POST)
    public ResponseEntity<String> acceptFriendship(@PathVariable String username1, @PathVariable String username2){
        us.acceptFriendship(username1, username2);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/declineFriendRequest/{username1}, {username2}", method = POST)
    public ResponseEntity<String> declineFriendship(@PathVariable String username1, @PathVariable String username2){
        us.declineFriendship(username1, username2);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteFriendRequest/{username1}, {username2}", method = POST)
    public ResponseEntity<List<String>> deleteFriendship(@PathVariable String username1, @PathVariable String username2){
        us.deleteFriendship(username1, username2);
        User u = us.getUserByUsername(username1);
        UserDTO uD = UserConverter.fromEntity(u);
        List<String> fList = uD.getUsernameOfFriend();
        return new ResponseEntity<>(fList, HttpStatus.OK);
    }

    @RequestMapping(value = "/findAll/{username}", method = GET)
    public ResponseEntity<List<UserDTO>> findAll(@PathVariable String username){
        List<UserDTO> listaDTO = us.findAll(username);
        return new ResponseEntity<>(listaDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/modify/{username}", method = PUT)
    public ResponseEntity<String> modify(@PathVariable String username, @RequestBody UserDTO userDTO) {
        userDTO.setUsername(username);
        boolean yes = us.modify(userDTO);
        if(yes){
            return new ResponseEntity<>("Successfully modified!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
}
