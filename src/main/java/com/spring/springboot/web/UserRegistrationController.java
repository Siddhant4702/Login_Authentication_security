package com.spring.springboot.web;

import com.spring.springboot.service.UserService;
import com.spring.springboot.web.dto.UserRegistrationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationController {

    @Autowired
    private UserService userService;


    //User when fill the form then this method handle the request
    @PostMapping
    public  String registerUserAccount(@ModelAttribute("user_project")UserRegistrationDto userRegistrationDto){
        userService.save(userRegistrationDto);
        return "redirect:/registration?success";  //return success if request is right
    }

    @GetMapping
    public  String showRegistrationForm(){

        return "registration";
    }
    @ModelAttribute("user_project")
    public  UserRegistrationDto userRegistrationDto(){

        return  new UserRegistrationDto();
    }



    //api to send mail
//    private EmailService emailService;
//    @RequestMapping(value = "/sendmail",method = RequestMethod.POST)
//    public ResponseEntity<?>sendEmail(){
//        this.userService.sendEmail();
//    }
}
