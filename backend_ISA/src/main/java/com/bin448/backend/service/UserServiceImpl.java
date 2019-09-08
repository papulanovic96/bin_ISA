package com.bin448.backend.service;

import com.bin448.backend.entity.DTOentity.UserDTO;
import com.bin448.backend.entity.User;

public class UserServiceImpl implements UserService {
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
}
