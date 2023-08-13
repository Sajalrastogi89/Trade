package com.Trade.Trading.Utils;

import com.Trade.Trading.Repositories.UserInformation;
import com.Trade.Trading.Service.Impl.RegistrationImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final RegistrationImpl registration;

    public Scheduler(RegistrationImpl registration) {
        this.registration = registration;
    }


    @Scheduled(fixedDelay = 1000,initialDelay = 1000)
public void scheduleTask(){
registration.performUpdateWithTransaction();
}
}
