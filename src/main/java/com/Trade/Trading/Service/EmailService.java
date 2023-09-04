package com.Trade.Trading.Service;

import org.springframework.stereotype.Service;


public interface EmailService {
    void sendEmail(String to_Email,
                   String Subject,
                   String body);
    String generateInviteLink();
    String createChatInviteLink(String apiToken, String chatId);
}
