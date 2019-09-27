package com.bin448.backend.converter;

import com.bin448.backend.entity.DTOentity.UserDTO;
import com.bin448.backend.entity.Friendship;
import com.bin448.backend.entity.PlaneSeat;
import com.bin448.backend.entity.PlaneTicket;
import com.bin448.backend.entity.User;
import com.bin448.backend.repository.FriendshipRepository;
import com.bin448.backend.repository.PlaneTicketRepository;
import com.bin448.backend.repository.SeatRepository;
import com.bin448.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Component
public class UserConverter extends AbstractConverter{

    private static FriendshipRepository fr;
    private static PlaneTicketRepository tr;
    private static SeatRepository sr;

    public UserConverter(FriendshipRepository f, PlaneTicketRepository t, SeatRepository s){
        this.fr = f;
        this.tr = t;
        this.sr = s;
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
        List<Friendship> novaList = new ArrayList<>();
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
                e.setRequests(nova2);
                e.setFriends(nova);
            } break;
        }
        User newUser;
        User newUser2;
        List<Friendship> listOfFriendsFromUser = e.getFriends();

        if(listOfFriendsFromUser != null) {
            for(Friendship f: listOfFriendsFromUser) {
                if(f.getSender().getUsername() != e.getUsername()){
                    listUserNames.add(f.getSender().getUsername());
                } else {
                    listUserNames.add(f.getReceiver().getUsername());
                }
            }
        }
        newDTOUser.setUsernameOfFriend(listUserNames);
        List<Friendship> listUserReq = e.getRequests();
        List<String> newRequests = new ArrayList<>();
        if(listUserReq != null) {
            for(Friendship f: listUserReq) {
                if(f.getSender().getUsername() != e.getUsername()){
                    newRequests.add(f.getSender().getUsername());
                } else {
                    newRequests.add(f.getReceiver().getUsername());
                }
            }
        }

        newDTOUser.setUsernameOfRequests(newRequests);

        List<Long> rezervisaneKarte = new ArrayList<>();
        List<PlaneTicket> karte = e.getReservedTicket();
        Long newTicket;
        for(PlaneTicket k: karte){
            newTicket = k.getId();
            rezervisaneKarte.add(newTicket);
        }
        newDTOUser.setPlaneTicket(rezervisaneKarte);
        if(e.getSeatForUser() != null) {
            newDTOUser.setSeat(e.getSeatForUser().getSeatId());
        }
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

        List<Long> rezerved = d.getPlaneTicket();
        List<PlaneTicket> newReserved = new ArrayList<>();
        PlaneTicket newTicket;
        if(rezerved != null){
            for(Long key: rezerved){
                newTicket = tr.getOne(key);
                newReserved.add(newTicket);
            }
        }
        if(d.getSeat() != null) {
            PlaneSeat nSeat = sr.getOne(d.getId());
            newUser.setSeatForUser(nSeat);
        }
        newUser.setReservedTicket(newReserved);
        return newUser;

    }
}
