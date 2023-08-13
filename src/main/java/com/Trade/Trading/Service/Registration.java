package com.Trade.Trading.Service;

import com.Trade.Trading.DTO.UserDetailDTO;
import com.Trade.Trading.Utils.UserSignUp;

public interface Registration {
    UserSignUp addUser(UserDetailDTO userDetailDTO);

    String generateOTP(String Email);
   boolean validateOTP(String Email,String OTP);
   UserSignUp login(UserDetailDTO userDetailDTO);
}
