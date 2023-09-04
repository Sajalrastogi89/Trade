package com.Trade.Trading.Service.Impl;

import com.Trade.Trading.Service.EmailService;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {
   private final JavaMailSender javaMailSender;
    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;

    public EmailServiceImpl(JavaMailSender javaMailSender, RestTemplate restTemplate, WebClient.Builder webClientBuilder) {
        this.javaMailSender = javaMailSender;
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
    }


    @Override
    public void sendEmail(String to_Email, String Subject, String body) {

        SimpleMailMessage message=new SimpleMailMessage();
            message.setFrom("Sajalrastogii89@gmail.com");
            message.setTo(to_Email);
            message.setSubject(Subject);
            message.setText(body);
            javaMailSender.send(message);
            System.out.println("Mail sent successfully");
    }

    @Override
    public String generateInviteLink() {
        try {
            String apiToken = "6539260018:AAFc6CA1Jmt8SJ1nVGXFkXvZBsUUbnEZjAA";
            String chatId = "-1001966345221";
            String result = createChatInviteLink(apiToken, chatId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String createChatInviteLink(String apiToken, String chatId) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 12);
        long expireDate = calendar.getTimeInMillis() * 1000;

        Map<String, String> data = new HashMap<>();
        data.put("chat_id", chatId);
        data.put("member_limit", "1");
        data.put("expire_date", "6737613761723");

        String url = "/bot" + apiToken + "/createChatInviteLink";

        // Use WebClient to make the POST request


        return webClientBuilder
                .build()
                .post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(data)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    return Mono.error(new RuntimeException("Client Error"));
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    return Mono.error(new RuntimeException("Server Error"));
                })
                .bodyToMono(String.class)
                .block(Duration.ofSeconds(10));

    }
}
