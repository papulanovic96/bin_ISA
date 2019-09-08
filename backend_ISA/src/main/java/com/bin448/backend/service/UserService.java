package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.FrendshipDTO;
import com.bin448.backend.entity.DTOentity.UserDTO;
import com.bin448.backend.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    boolean modify(UserDTO oldUser);
    void sendRequest(String usernameSender, String usernameReceiver);
    void acceptFriendship(String usernameSender, String usernameReceiver);
    void declineFriendship(String usernameSender, String usernameReceiver);
    void deleteFriendship(String usernameSender, String usernameReceiver);
    List<String> getMyReceivedRequests(String username);
    List<UserDTO> findAll(String username);
    User getUserByUsername(String username);
    String addUser(UserDTO u);
    boolean logIn(String username,String password);
    User getByEmail(String mail);
    void modifyUser(boolean active,String user);
    User getById(Long id);

}
