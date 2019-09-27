package com.bin448.backend.service;

import com.bin448.backend.entity.ConfirmationToken;
import com.bin448.backend.entity.DTOentity.UserDTO;
import com.bin448.backend.entity.User;
import com.bin448.backend.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Service
@Transactional
public class EmailInviteService {
    private JavaMailSender javaMailSender;
    private UserService us;
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public EmailInviteService(JavaMailSender javaMailSender,UserService us,ConfirmationTokenRepository confirmationTokenRepository) {
        this.us = us;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public boolean mailForInvitation(ModelAndView modelAndView, UserDTO user){

        User existingUser = us.getUserByUsername(user.getUsername());
        if(existingUser == null)
        {
            return false;
        }

        else
        {

            String uid = UUID.randomUUID().toString();
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("You've been invited to travel with a friend!");
            mailMessage.setFrom("goodbyetravelagency.com");
            mailMessage.setText("Hi "+user.getName()+", to confirm your invitation, please click here : "
                    +"http://localhost:4200/#/invite/"
            );

            sendEmail(mailMessage);

            modelAndView.addObject("emailId", user.getUsername());


            modelAndView.setViewName("successfulInvitation");

            return true;
        }



    }
}
