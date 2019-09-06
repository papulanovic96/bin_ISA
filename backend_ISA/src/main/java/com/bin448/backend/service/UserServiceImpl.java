package com.bin448.backend.service;

import com.bin448.backend.converter.FriendshipConverter;
import com.bin448.backend.converter.UserConverter;
import com.bin448.backend.entity.DTOentity.FrendshipDTO;
import com.bin448.backend.entity.DTOentity.UserDTO;
import com.bin448.backend.entity.Friendship;
import com.bin448.backend.entity.User;
import com.bin448.backend.repository.FriendshipRepository;
import com.bin448.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private FriendshipService friendshipService;

    private UserRepository ur;

    public UserServiceImpl(UserRepository ur){
        this.ur = ur;
    }

    @Transactional
    @Override
    public boolean modify(UserDTO oldUser) {
        User newUser = UserConverter.toEntity(oldUser);
        User newUserUseIt = ur.getUserByUsername(newUser.getUsername());
        if(newUserUseIt == null) {
            return false;
        } else {
            ur.modifyUser(newUserUseIt.getCity(), newUserUseIt.getEmail(),
                    newUserUseIt.getLastName(), newUserUseIt.getName(),
                    newUserUseIt.getTelephone(), newUserUseIt.getUsername());
            return true;
        }
    }

    @Override
    public void deleteFriend(UserDTO user, String friendsUsername) {
        /*User userReal = UserConverter.toEntity(user);
        List<User> users = userReal.getFriends();
        for(User u : users) {
            if(u.getUsername().equals(friendsUsername)) {
                ur.delete(u);
                break;
            }
        }*/
    }

    @Override
    public void sendRequest(User newUser) {
        Friendship newFriendShip = new Friendship();
        newFriendShip.setReceiver(newUser);
        User sender;
        sender = ur.findByUsername("nebica996");
        newFriendShip.setSender(sender);
        newFriendShip.setAreFriends(false);
        friendshipService.save(newFriendShip);
    }
    @Transactional
    @Override
    public void acceptFriendship() {

        System.out.println("usao u accept" +
                "!");
        List<Friendship> friendships = friendshipService.findAllEntities();
        User newUser = ur.findByUsername("ivica996");
        for(Friendship f: friendships) {
            if(f.getReceiver() == (newUser)) {
                f.setAreFriends(true);
                friendshipService.save(f);
                break;
            }
        }
    }

    @Override
    public List<FrendshipDTO> getMyFriends(UserDTO user) {
        List<Friendship> novaLista = friendshipService.findAllEntities();
        List<Friendship> newList = new ArrayList<>();
        User newUser = UserConverter.toEntity(user);
        for(Friendship f: novaLista){
            if(f.isAreFriends()) {
                if(newUser.getUsername() == f.getSender().getUsername()){
                    newList.add(f);
                }
            }
        }
        List<FrendshipDTO> dtoList = FriendshipConverter.fromEntityList(newList, e -> FriendshipConverter.fromEntity(e));
        return dtoList;
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

    @Override
    public User getById(Long id) {
        return ur.getUserById(id);
    }
}
