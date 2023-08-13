package com.Trade.Trading.Repositories;

import com.Trade.Trading.Entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInformation extends JpaRepository<UserDetail,Long> {
    UserDetail findByEmail(String email);
}
