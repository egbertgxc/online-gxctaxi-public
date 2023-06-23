package com.gxc.servicepassengeruser.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class PassengerUser {
    private Long id;
    private LocalTime gmtCreate;
    private LocalTime gmtModified;
    private String passengerPhone;
    private String passengerName;
    private byte passengerGender;
    private byte state;

}
