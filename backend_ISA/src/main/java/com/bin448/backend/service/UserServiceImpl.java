package com.bin448.backend.service;

import com.bin448.backend.converter.FriendshipConverter;
import com.bin448.backend.converter.InvitationConverter;
import com.bin448.backend.converter.UserConverter;
import com.bin448.backend.entity.DTOentity.FrendshipDTO;

import com.bin448.backend.entity.DTOentity.InvitationDTO;
import com.bin448.backend.entity.DTOentity.UserDTO;
import com.bin448.backend.entity.Friendship;
import com.bin448.backend.entity.Invitation;
import com.bin448.backend.entity.User;
import com.bin448.backend.repository.FriendshipRepository;
import com.bin448.backend.repository.SeatRepository;
import com.bin448.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class UserServiceImpl implements UserService {

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private InvitationService invitationService;

    private SeatRepository sr;

    private UserRepository ur;

    public UserServiceImpl(UserRepository ur, SeatRepository sr){
        this.ur = ur;
        this.sr = sr;
    }


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public boolean modify(UserDTO oldUser) {
        User newUserUseIt = UserConverter.toEntity(oldUser);
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
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void sendRequest(String usernameSender, String usernameReceiver) {
        Friendship newFriendShip = new Friendship();
        newFriendShip.setReceiver(ur.findByUsername(usernameReceiver));
        newFriendShip.setSender(ur.findByUsername(usernameSender));
        newFriendShip.setAreFriends(false);
        friendshipService.save(newFriendShip);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void invitation(String usernameSender, String usernameReceiver, Long seat) {
        Invitation newInvitation = new Invitation();
        newInvitation.setInviting(ur.findByUsername(usernameSender));
        newInvitation.setReceiving(ur.findByUsername(usernameReceiver));
        newInvitation.setSeat(sr.getOne(seat));
        newInvitation.setInvitationStatus(false);
        invitationService.saveInivitation(newInvitation);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void declineInvitation(String usernameReceiver) {
        List<InvitationDTO> invitations = invitationService.findAll();
        for(InvitationDTO a: invitations) {
            if(a.getUserReceive().equals(usernameReceiver)) {
                invitationService.delete(a);
                break;
            }
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void acceptInvitation(String usernameReceiver) {
        List<InvitationDTO> invitations = invitationService.findAll();
        for(InvitationDTO a: invitations) {
            if(a.getUserReceive().equals(usernameReceiver)) {
                a.setStatus(true);
                a.setUserReceive(usernameReceiver);
                a.setUserInivte(a.getUserInivte());
                a.setSeatId(a.getSeatId());
                invitationService.delete(a);
                invitationService.saveInivitation(InvitationConverter.toEntity(a));
            }
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void acceptFriendship(String usernameSender, String usernameReceiver) {
        List<Friendship> friendships = friendshipService.findAllEntities();
        User receiver = ur.findByUsername(usernameReceiver);
        User sender = ur.findByUsername(usernameSender);
        for(Friendship f: friendships) {
            if(f.getReceiver() == (sender) && f.getSender() == receiver) {
                f.setAreFriends(true);
                friendshipService.save(f);
                break;
            } else if(f.getReceiver() == receiver && f.getSender() == sender) {
                f.setAreFriends(true);
                friendshipService.save(f);
                break;
            }
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void declineFriendship(String usernameSender, String usernameReceiver) {
        List<Friendship> friendships = friendshipService.findAllEntities();
        User receiver = ur.findByUsername(usernameReceiver);
        User sender = ur.findByUsername(usernameSender);
        for(Friendship f: friendships) {
            if(f.getReceiver() == (sender)) {
                friendshipService.delete(f);
                break;
            } else if(f.getReceiver() == receiver) {
                friendshipService.delete(f);
                break;
            } else if(f.getSender() == receiver) {
                friendshipService.delete(f);
                break;
            } else if(f.getSender() == sender) {
                friendshipService.delete(f);
                break;
            }
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void deleteFriendship(String usernameSender, String usernameReceiver) {
        List<Friendship> list = friendshipService.findAllEntities();
        User u = ur.findByUsername(usernameSender);
        User u2 = ur.findByUsername(usernameReceiver);

        for(Friendship f: list){
            if(f.getSender() == u){
                if(f.getReceiver() == u2) {
                    friendshipService.delete(f);
                    break;
                }
            } else if(f.getReceiver() == u){
                if(f.getSender() == u2){
                    friendshipService.delete(f);
                    break;
                }
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return ur.getUserByUsername(username);

    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getMyReceivedRequests(String username){
        User newUser = ur.findByUsername(username);
        List<Friendship> listRequests = newUser.getRequests();
        List<String> listaPrava = new ArrayList<>();
        for(Friendship f: listRequests){
            if(!f.isAreFriends()){
                if(f.getSender().getUsername() != username){
                    listaPrava.add(f.getSender().getUsername());
                } else {
                    listaPrava.add(f.getReceiver().getUsername());
                }
            }

        }
        return listaPrava;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
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
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
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
    @Transactional(readOnly = true)
    public List<UserDTO> findAll(String username) {
        List<User> novaLista = ur.findAll();
        List<UserDTO> dtoList = UserConverter.fromEntityList(novaLista, e -> UserConverter.fromEntity(e));
        User newUser = ur.findByUsername(username);
        UserDTO userDTO = UserConverter.fromEntity(newUser);
        List<String> listaFrsh = userDTO.getUsernameOfFriend();
        if(listaFrsh == null || listaFrsh.isEmpty()) {
            return dtoList;
        }
        Iterator<UserDTO> i = dtoList.iterator();
        if(listaFrsh != null) {
            while(i.hasNext()) {
                for(String un: listaFrsh) {
                    if(i.next().getUsername() == un) {
                        i.remove();
                        break;
                    }
                }
            }
        }


        return dtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public User getByEmail(String mail) {
        return null;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void modifyUser(boolean active,String user) {
      ur.modifyByUsername(active,user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return ur.getUserById(id);
    }
}
