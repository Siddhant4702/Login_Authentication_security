package com.spring.springboot.web;

import com.spring.springboot.model.User_project;
import com.spring.springboot.repository.UserRepository;
import com.spring.springboot.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class ForgetController {

    Random random=new Random(1000);
    @Autowired
   private EmailService emailService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encodepass;

    //email id form open
    @RequestMapping("/forget")
    public  String openEmailForm(){

        return  "forget_email_form";
    }

    @PostMapping("/send-otp")
    public String sendOTP(@RequestParam("email")String email, HttpSession session){

        System.out.println("Email : "+ email);
        //generate otp of four digit

       int otp= random.nextInt(99999);
       System.out.println("OTP  "+ otp);


       //Write code for send otp to email
        String subject="OTP from DKTE";
//        String message= ""
//                + "<div style='border:1px solid black;padding:20px '>"
//                + "<h1>"
//                + "OTP : "
//                +  "<b>"+ otp
//                + "</b>"
//                + "</h1>"
//                + "</div>";
        String message="OTP : " + otp;
        String to=email;
      boolean flag=this.emailService.sendEmail(subject, message , to);
      if(flag){
          session.setAttribute("match_otp", otp);
          session.setAttribute("email",email);
          return "verify_otp";

      }
      else{
          session.setAttribute("message", "Check your Email Id!!!");
          return  "forget_email_form";
      }
    }

    @PostMapping("/verify-otp")
    public  String verifyOtp(@RequestParam("otp") int otp, HttpSession session ){
            int match_opt=(int)session.getAttribute("match_otp");
            String email=(String)session.getAttribute("email");
            if(match_opt==otp){
                //show password change form
                //fetch email by user email
                User_project user_project= this.userRepository.findByEmail(email);
                    if(user_project==null){
                        //send error message
                        session.setAttribute("message", "User does not exits with this Email!");
                        return  "forget_email_form";
                    }
                    else{
                        //send change password
                        return "password_change_form";
                    }


            }else{
                session.setAttribute("message", "You have entered wrong opt");
                return "verify_otp";
            }
    }

    // step : 4 change password

    @PostMapping("/change-password")
    public  String changePassword(@RequestParam("newpassword") String newpassword, HttpSession session){
        String email=(String)session.getAttribute("email");
     User_project user_project =   this.userRepository.findByEmail(email);
     user_project.setPassword(this.encodepass.encode(newpassword));
     this.userRepository.save(user_project);

        return  "redirect:/login?change";
    }

}
