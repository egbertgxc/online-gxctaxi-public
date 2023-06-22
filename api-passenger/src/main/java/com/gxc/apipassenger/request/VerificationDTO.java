package com.gxc.apipassenger.request;

import org.springframework.web.bind.annotation.GetMapping;


public class VerificationDTO {

    private String passengerPhone;

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }
}
