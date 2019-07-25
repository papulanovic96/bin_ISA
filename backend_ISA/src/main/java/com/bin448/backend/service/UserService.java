package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.UserDTO;
import com.bin448.backend.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean modify(UserDTO oldUser);
    void deleteFriend(UserDTO friend);
    User addFriend(UserDTO friend);
}
