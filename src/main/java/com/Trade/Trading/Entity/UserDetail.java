package com.Trade.Trading.Entity;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class UserDetail {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String Name;
    @Column(unique = true)
    @Email(message = "Please provide a valid email address")
private String email;
private String password;
private String OTP;
private Long otpRequestTime;
private boolean Verification=false;


    public boolean getVerification() {
        return Verification;
    }
}
