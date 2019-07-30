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

    public ModelAndView mailSendWhenRegister(ModelAndView modelAndView, UserDTO user){

        User existingUser = us.getUserByUsername(user.getUsername());
        if(existingUser != null)
        {
            modelAndView.addObject("message","This email already exists!");
            modelAndView.setViewName("error");
        }
        else
        {
            us.addUser(user);
            User u = UserConverter.toEntity(user);
            User izbaze = us.getUserByUsername(user.getUsername());
            ConfirmationToken confirmationToken = new ConfirmationToken(izbaze);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(izbaze.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("goodbyetravelagency.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/user/confirm-account?token="+confirmationToken.getToken());

            sendEmail(mailMessage);

            modelAndView.addObject("emailId", user.getUsername());

            modelAndView.setViewName("successfulRegisteration");
        }

        return modelAndView;

    }
    public ModelAndView accountConfimation(ModelAndView modelAndView, String tokenConf){
        ConfirmationToken token = confirmationTokenRepository.findByToken(tokenConf);

        if(token != null)
        {
            User user = us.getUserByUsername(token.getUser().getUsername());
            UserDTO u = UserConverter.fromEntity(user);
            us.modifyUser(true,token.getUser().getUsername());

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
