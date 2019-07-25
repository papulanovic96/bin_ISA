package com.bin448.backend.converter;

import com.bin448.backend.entity.DTOentity.UserDTO;
import com.bin448.backend.entity.User;

import java.util.List;

public abstract class UserConverter extends AbstractConverter{

    public UserDTO fromEntity(User e) {
        UserDTO newDTOUser = new UserDTO();
        newDTOUser.setName(e.getName());
        newDTOUser.setLastName(e.getLastName());
        newDTOUser.setCity(e.getCity());
        newDTOUser.setEmail(e.getEmail());
        newDTOUser.setTelephone(e.getTelephone());
        newDTOUser.setUsername(e.getUsername());
        return newDTOUser;
    }

    public User toEntity(UserDTO d) {
        User newUser = new User();
        newUser.setName(d.getName());
        newUser.setLastName(d.getLastName());
        newUser.setCity(d.getCity());
        newUser.setEmail(d.getEmail());
        newUser.setTelephone(d.getTelephone());
        newUser.setUsername(d.getUsername());
        return newUser;
    }
}
