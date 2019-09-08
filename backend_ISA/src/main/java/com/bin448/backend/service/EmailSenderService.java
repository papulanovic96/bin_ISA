package com.bin448.backend.service;

import com.bin448.backend.converter.UserConverter;
import com.bin448.backend.entity.ConfirmationToken;
import com.bin448.backend.entity.DTOentity.UserDTO;
import com.bin448.backend.entity.User;
import com.bin448.backend.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Service
public class EmailSenderService {
    private JavaMailSender javaMailSender;
    private UserService us;
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender,UserService us,ConfirmationTokenRepository confirmationTokenRepository) {
        this.us = us;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    public boolean mailSendWhenRegister(ModelAndView modelAndView, UserDTO user){

        User existingUser = us.getUserByUsername(user.getUsername());
        if(existingUser != null)
        {/*
            modelAndView.addObject("message","This email already exists!");
            modelAndView.setViewName("error");
            */
            return false;
        }

        else
        {
     /*       us.addUser(user);
            User u = UserConverter.toEntity(user);
            User izbaze = us.getUserByUsername(user.getUsername());
            ConfirmationToken confirmationToken = new ConfirmationToken(izbaze);

            confirmationTokenRepository.save(confirmationToken);
*/
            String uid = UUID.randomUUID().toString();
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("goodbyetravelagency.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/user/confirm-account?token="+uid+"'"+
                    user.getCity()+"'"+user.getEmail()+"'"+user.getLastName()+"'"+user.getName()+"'"+user.getPassword()+
                    "'"+user.getRole()+"'"+user.getTelephone()+"'"+user.getUsername()
                    );

            sendEmail(mailMessage);

            modelAndView.addObject("emailId", user.getUsername());


            modelAndView.setViewName("successfulRegisteration");

            return true;
        }



    }
    public ModelAndView accountConfimation(ModelAndView modelAndView, String tokenConf){

        UserDTO newUser = new UserDTO();
        String []parts = tokenConf.split("'");

        String Token =parts[0];
        newUser.setCity(parts[1]);
        newUser.setActive(true);
        newUser.setPassword(parts[5]);
        newUser.setRole("ROLE_USER");
        newUser.setEmail(parts[2]);
        newUser.setLastName(parts[3]);
        newUser.setName(parts[4]);
        newUser.setTelephone(parts[7]);
        newUser.setUsername(parts[8]);


        if(Token != null)
        {
            us.addUser(newUser);
            User baseUser = us.getUserByUsername(newUser.getUsername());
            ConfirmationToken ct = new ConfirmationToken(baseUser);
            confirmationTokenRepository.save(ct);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            // modelAndView.setViewName("error");
        }

        return modelAndView;
    }

}
