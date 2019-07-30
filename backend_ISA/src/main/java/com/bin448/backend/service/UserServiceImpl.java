package com.bin448.backend.service;

import com.bin448.backend.converter.UserConverter;
import com.bin448.backend.entity.Airline;
import com.bin448.backend.entity.DTOentity.UserDTO;
import com.bin448.backend.entity.User;
import com.bin448.backend.exception.NotFoundException;
import com.bin448.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserServiceImpl implements UserService {
     private UserRepository ur;

    public UserServiceImpl(UserRepository ur){
        this.ur=ur;
    }
    @Override
    public boolean modify(UserDTO oldUser) {
        User newUser = null;
        return false;
    }

    @Override
    public void deleteFriend(UserDTO friend) {

    }

    @Override
    public User addFriend(UserDTO friend) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return ur.getUserByUsername(username);

    }

    @Override
    public String addUser(UserDTO u) {
        User user = UserConverter.toEntity(u);
        user.setRole("ROLE_USER");
        PasswordEncoder p = new BCryptPasswordEncoder();
        String pass = p.encode(user.getPassword());
        user.setPassword(pass);
        String ret="Username alreadty in use!";
        try {
            ur.save(user);
            ret = "Success";
        }catch (Exception e){


        }finally {
            return ret;
        }

    }

    @Override
    public boolean logIn(String username, String password) {
        PasswordEncoder pe = new BCryptPasswordEncoder();
        String pass = pe.encode(password);
        User u =  ur.getByUsernameAndPassword(username,pass);
        if(u==null)
            return false;
        else
            return true;

    }

    @Override
    public User getByEmail(String mail) {
        return null;
    }

    @Override
    public void modifyUser(boolean active,String user) {
      ur.modifyByUsername(active,user);
    }
}
