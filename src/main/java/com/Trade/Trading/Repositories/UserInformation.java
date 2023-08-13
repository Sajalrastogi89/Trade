package com.Trade.Trading.Repositories;

import com.Trade.Trading.Entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInformation extends JpaRepository<UserDetail,Long> {
    UserDetail findByEmail(String email);
    @Modifying
    @Query("UPDATE UserDetail SET OTP = null, otpRequestTime=null WHERE otpRequestTime < :threshold")
    void updateOtpExpiration(long threshold);
}
