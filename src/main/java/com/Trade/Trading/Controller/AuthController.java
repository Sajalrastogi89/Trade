package com.Trade.Trading.Controller;

import com.Trade.Trading.DTO.UserDetailDTO;
import com.Trade.Trading.Utils.UserSignUp;
import com.Trade.Trading.Service.EmailService;
import com.Trade.Trading.Service.Registration;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final Registration register;
    private final EmailService emailService;

    public AuthController(Registration register, EmailService emailService) {
        this.register = register;
        this.emailService = emailService;
    }

    @PostMapping("/signup")
   public UserSignUp signUp(@RequestBody UserDetailDTO user){
        return register.addUser(user);
    }

    @PostMapping("/login")
  public UserSignUp login(@RequestBody UserDetailDTO userDetailDTO){
        return register.login(userDetailDTO);
  }

  @PostMapping("/otp/{otp}/email/{email}")
  boolean  verification(@PathVariable String email,@PathVariable String otp){
//       return register.validateOTP(email,otp);
      if(register.validateOTP(email,otp)){
          emailService.sendEmail(email,"Successful registration","You are successfully registered😊😊\n"+emailService.generateInviteLink());
      return true;
      }
      else return false;
  }
    @GetMapping("/otp/{email}")
  public void sendOTP(@PathVariable String email){

         emailService.sendEmail(email,"hello","This is your OTP"+register.generateOTP(email)+"\n"+"It will expire in 10 minutes");
    }






}
