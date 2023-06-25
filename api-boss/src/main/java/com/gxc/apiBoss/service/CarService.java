package com.gxc.apiBoss.service;

import com.gxc.apiBoss.remote.ServiceDriverUserClient;
import com.gxc.internalcommon.dto.Car;
import com.gxc.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult addCar(Car car){
        return serviceDriverUserClient.addCar(car);
    }
}
