package com.gxc.servicepassengeruser.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class PassengerUser {
    private Long id;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private String passengerPhone;
    private String passengerName;
    private byte passengerGender;
    private byte state;

}
