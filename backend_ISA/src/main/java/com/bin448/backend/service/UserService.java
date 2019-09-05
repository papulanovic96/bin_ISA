package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.UserDTO;
import com.bin448.backend.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {

    boolean modify(UserDTO oldUser);
    void deleteFriend(UserDTO user, String friendsUsername);
    User addFriend(UserDTO user, String friendsUsername);
    User getUserByUsername(String username);
    String addUser(UserDTO u);
    boolean logIn(String username,String password);
    User getByEmail(String mail);
    void modifyUser(boolean active,String user);

}
