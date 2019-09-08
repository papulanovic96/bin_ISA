package com.bin448.backend.converter;

import com.bin448.backend.entity.DTOentity.UserDTO;
import com.bin448.backend.entity.Friendship;
import com.bin448.backend.entity.User;
import com.bin448.backend.repository.FriendshipRepository;
import com.bin448.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Component
public class UserConverter extends AbstractConverter{

    private static FriendshipRepository fr;


    public UserConverter(FriendshipRepository f){
        this.fr = f;
    }

    public static UserDTO fromEntity(User e) {
        UserDTO newDTOUser = new UserDTO();
        newDTOUser.setId(e.getId());
        newDTOUser.setName(e.getName());
        newDTOUser.setLastName(e.getLastName());
        newDTOUser.setCity(e.getCity());
        newDTOUser.setEmail(e.getEmail());
        newDTOUser.setTelephone(e.getTelephone());
        newDTOUser.setUsername(e.getUsername());
        newDTOUser.setRole(e.getRole());
        newDTOUser.setPassword(e.getPassword());
        newDTOUser.setActive(e.isActive());


        List<Friendship> listUser = fr.findAll();
        List<String> listUserNames = new ArrayList<>();
        for(Friendship fp : listUser) {
            List<Friendship> nova = fp.getReceiver().getFriends();
            List<Friendship> nova2 = fp.getSender().getRequests();
            if(nova.isEmpty()){
                nova = fp.getSender().getFriends();
            }
            if(nova2.isEmpty()){
                nova2 = fp.getReceiver().getRequests();
            }
            Iterator<Friendship> i = nova.iterator();
            while(i.hasNext()){
                if(!i.next().isAreFriends()) {
                    i.remove();
                }
            }
            Iterator<Friendship> i2 = nova2.iterator();
            while(i2.hasNext()){
                if(i2.next().isAreFriends()){
                    i2.remove();
                }
            }
        }
        User newUser;
        User newUser2;
        for(Friendship f: listUser) {
            if(f.isAreFriends()){
                newUser = f.getReceiver();
                newUser2 = f.getSender();
                if(newUser.getUsername() != e.getUsername()) {
                    listUserNames.add(newUser.getUsername());
                } else {
                    if(newUser2.getUsername() != e.getUsername()){
                        listUserNames.add(newUser2.getUsername());
                    }
                }
            }
        }
        newDTOUser.setUsernameOfFriend(listUserNames);

        List<Friendship> listUserRequests = fr.findAll();
        List<String> listUserReq = new ArrayList<>();
        User newUserR;
        User newUserR2;
        for(Friendship f: listUserRequests) {
            if(!f.isAreFriends()){
                newUserR = f.getSender();
                newUserR2 = f.getReceiver();
                if(newUserR.getUsername() != e.getUsername()) {
                    listUserReq.add(newUserR.getUsername());
                } else
                {
                    if(newUserR2.getUsername() != e.getUsername()){
                        listUserReq.add(newUserR2.getUsername());
                    }

                }
            }
        }
        newDTOUser.setUsernameOfRequests(listUserReq);
        return newDTOUser;
    }

    public static User toEntity(UserDTO d) {

        User newUser = new User();
        newUser.setId(d.getId());
        newUser.setActive(d.isActive());
        newUser.setName(d.getName());
        newUser.setLastName(d.getLastName());
        newUser.setCity(d.getCity());
        newUser.setEmail(d.getEmail());
        newUser.setTelephone(d.getTelephone());
        newUser.setUsername(d.getUsername());
        newUser.setRole(d.getRole());
        newUser.setPassword(d.getPassword());

        List<String> listUserNames = d.getUsernameOfFriend();
        List<Friendship> listUser = new ArrayList<>();
        User userFor = new User();
        Friendship newFriendship = new Friendship();
        if(listUserNames != null) {
            for(String s: listUserNames) {
                userFor.setUsername(s);
                newFriendship.setAreFriends(true);
                newFriendship.setReceiver(userFor);
                listUser.add(newFriendship);
            }
        }
        newUser.setFriends(listUser);

        List<String> listUserRequests = d.getUsernameOfRequests();
        List<Friendship> listUserReq = new ArrayList<>();
        User userForR = new User();
        Friendship newFriendshipR = new Friendship();
        if(listUserNames != null) {
            for(String s: listUserRequests) {
                userForR.setUsername(s);
                newFriendshipR.setAreFriends(false);
                newFriendshipR.setSender(userForR);
                listUserReq.add(newFriendship);
            }
        }
        newUser.setRequests(listUserReq);
        return newUser;

    }
}
