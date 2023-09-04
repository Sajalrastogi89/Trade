package com.Trade.Trading.Controller;

import com.Trade.Trading.Service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class EmailController {

    public final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/hi")
    void sendEmailFunction(){

        emailService.sendEmail("sajalrastogii8910@gmail.com",
                "Regarding Trading",
                "This is the premium telegram channel joining link. click below link to join. 😊😊 \n"+emailService.generateInviteLink());
    }
}
