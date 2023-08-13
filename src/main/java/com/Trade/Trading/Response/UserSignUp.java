package com.Trade.Trading.Response;

import com.Trade.Trading.DTO.UserDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;


@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUp {
    private String Message;
    private UserDetailDTO userDetailDTO;
}
