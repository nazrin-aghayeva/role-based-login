package com.employee.app.controller;


import com.employee.app.entities.ConfirmationToken;
import com.employee.app.entities.Mail;
import com.employee.app.entities.Users;
import com.employee.app.repos.ConfirmationTokenRepo;
import com.employee.app.repos.UserRepo;
import com.employee.app.services.EmailService;
import com.employee.app.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@Log4j2
public class UserController {

    @Autowired
    ConfirmationTokenRepo confirmationTokenRepo;

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepo userRepo;

    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registrationForm")
    public Users registrationForm(){
        return new Users();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView model= new ModelAndView();
        model.addObject("login");
        return model;
    }

    @RequestMapping(value = "/user_page", method = RequestMethod.GET)
    public ModelAndView userPage(){
        ModelAndView model= new ModelAndView();
        model.addObject("user_page");
        return model;
    }

    @RequestMapping(value = "/admin_page", method = RequestMethod.GET)
    public ModelAndView adminPage(){
        ModelAndView model= new ModelAndView();
        model.addObject("admin_page");
        return model;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.addObject("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser( @ModelAttribute("registrationForm") @Valid Users user, BindingResult binding, HttpServletRequest httpRequest){
        ModelAndView model= new ModelAndView();
        Optional<Users> isExist= userService.findUserByEmail(user.getEmail());
        if (user.getFull_name().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty() || user.getConfirm_password().isEmpty()){
            binding.rejectValue("full_name", "error.user", "Each field is mandatory");
        }
        if (isExist.isPresent()){
            binding.rejectValue("email", "error.user", "User with such mail address exists");
        }
        if (!user.getPassword().equals(user.getConfirm_password())){
            binding.rejectValue("password", "error.user", "Passwords mismatched");
        }
        if (binding.hasErrors()){
            model.setViewName("login");
        }
        else{
            userService.saveUser(user);
            ConfirmationToken confirmationToken= new ConfirmationToken(user);
            confirmationTokenRepo.save(confirmationToken);

            Mail mail= new Mail();
            mail.setFrom("nazrin.agha12@gmail.com");
            mail.setTo(user.getEmail());
            mail.setSubject("Complete your registration");
            mail.setContent("Dear "+ user.getFull_name()+" to complete your registration follow: "+
                    httpRequest.getScheme()+ "://" + httpRequest.getServerName() + ":" +httpRequest.getServerPort()+
                    "/confirm-token?token="+confirmationToken.getConfirmToken());
            emailService.sendEmail(mail);
        }
        model.setViewName("registration");
        return model;
    }

    @RequestMapping(value = "/confirm-token", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmMail(ModelAndView model, @RequestParam("token") String confirmToken){
        ConfirmationToken confirmationToken= confirmationTokenRepo.findByConfirmToken(confirmToken);

        if (confirmationToken!=null){
            Users user= userRepo.findByEmailIgnoreCase(confirmationToken.getUsers().getEmail());
            user.setEnabled(true);
            userService.saveUser(user);
            model.setViewName("login");
        }
        else
        {
            model.addObject("message", "Invalid Link");
            model.setViewName("error");
        }
        return model;
    }
}















