package com.Trade.Trading.Controller;

import com.Trade.Trading.DTO.UserDetailDTO;
import com.Trade.Trading.Service.Impl.PaymentServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PaymentController {

    private final PaymentServiceImpl paymentService;

    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping("/orderId")
    public UserDetailDTO orderId(@RequestBody UserDetailDTO userDetailDTO){
        paymentService.orderId(userDetailDTO);
        return userDetailDTO;
    }
}
