package com.Trade.Trading.Service.Impl;

import com.Trade.Trading.DTO.UserDetailDTO;
import com.Trade.Trading.Entity.UserDetail;
import com.Trade.Trading.Repositories.UserInformation;
import com.Trade.Trading.Response.UserSignUp;
import com.Trade.Trading.Service.Registration;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
//import static javax.swing.text.rtf.RTFAttributes.BooleanAttribute.True;

@Service
public class RegistrationImpl implements Registration {

    private final ModelMapper modelMapper;

    private final UserInformation userInformation;


    public RegistrationImpl(ModelMapper modelMapper, UserInformation userInformation) {
        this.modelMapper = modelMapper;
        this.userInformation = userInformation;
    }

    @Override
    public UserSignUp addUser(UserDetailDTO userDetailDTO) {
        UserDetail userDetail=DtoToUser(userDetailDTO);
        UserSignUp userSignUp;
     if(userInformation.findByEmail(userDetail.getEmail())!=null && userDetail.getVerification()){
//         userSignUp.setMessage("Already Registered");
//         userSignUp.setUserDetailDTO(userDetailDTO);
         userSignUp=new UserSignUp("Already Registered",userDetailDTO);

     }
      else {
         UserDetailDTO user = UserToDto(userInformation.save(DtoToUser(userDetailDTO)));
//     userSignUp.setMessage("Successfully registered");
//     userSignUp.setUserDetailDTO(user);}
         userSignUp = new UserSignUp("Successfully registered", user);
     }
        return userSignUp;
    }

    @Override
    public String generateOTP(String Email) {
        UserDetail user=userInformation.findByEmail(Email);
        String OTP = RandomString.make(8);
//        String encodedOTP = passwordEncoder.encode(OTP);
        user.setOTP(OTP);
        user.setOtpRequestTime(System.currentTimeMillis());
        userInformation.save(user);
        return OTP;
    }

    @Override
    public boolean validateOTP(String Email,String OTP) {
        UserDetail user=userInformation.findByEmail(Email);
        if(user.getOTP().equals(OTP) && user.getOTP()!=null && System.currentTimeMillis()<user.getOtpRequestTime()+600000){
            user.setVerification(true);
            user.setOTP(null);
            user.setOtpRequestTime(null);
            userInformation.save(user);
        }
        return user.getVerification();
    }

    @Override
    public UserSignUp login(UserDetailDTO userDetailDTO) {
        UserSignUp userSignUp;
UserDetail userDetail=userInformation.findByEmail(userDetailDTO.getEmail());
if(
        userDetail.getEmail().equals(userDetailDTO.getEmail()) &&
                userDetail.getPassword().equals(userDetailDTO.getPassword()) &&
                   userDetail.getVerification()
){
//    userSignUp.setMessage("Valid user");
//    userSignUp.setUserDetailDTO(userDetailDTO);
    userSignUp=new UserSignUp("Valid user",userDetailDTO);
}
else{
//    userSignUp.setMessage("Invalid User");
//    userSignUp.setUserDetailDTO(userDetailDTO);
    userSignUp=new UserSignUp("Invalid User",userDetailDTO);
}
        return userSignUp;
    }


    private UserDetail DtoToUser(UserDetailDTO userDetailDTO) {
        return this.modelMapper.map(userDetailDTO,UserDetail.class);
    }

    private UserDetailDTO UserToDto(UserDetail userDetail) {
        return this.modelMapper.map(userDetail,UserDetailDTO.class);
    }

}
