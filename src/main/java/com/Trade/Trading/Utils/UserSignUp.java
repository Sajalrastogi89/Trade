package com.Trade.Trading.Utils;

import com.Trade.Trading.DTO.UserDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUp {
    private String Message;
    private UserDetailDTO userDetailDTO;
}
