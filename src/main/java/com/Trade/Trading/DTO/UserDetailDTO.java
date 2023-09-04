package com.Trade.Trading.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDTO {
    private Long id;
    private String Name;
    private String Email;
    private String password;
    private String OTP;
    private Long otpRequestTime;
    private boolean Verification=false;
}
