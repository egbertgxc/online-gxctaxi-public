package com.gxc.apipassenger.request;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

@Data
public class VerificationDTO {

    private String passengerPhone;

    private String verificationCode;

}
