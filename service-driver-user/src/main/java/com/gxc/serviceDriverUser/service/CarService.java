package com.gxc.serviceDriverUser.service;

import com.gxc.internalcommon.dto.Car;
import com.gxc.internalcommon.dto.ResponseResult;
import com.gxc.internalcommon.responese.TerminalResponse;
import com.gxc.internalcommon.responese.TrackResponse;
import com.gxc.serviceDriverUser.mapper.CarMapper;
import com.gxc.serviceDriverUser.remote.ServiceMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ServiceMapClient serviceMapClient;

    public ResponseResult addCar(Car car){
        LocalDateTime now = LocalDateTime.now();
        car.setGmtModified(now);
        car.setGmtCreate(now);
        // 保存车辆
        carMapper.insert(car);

//        // 获得此车辆的终端id：tid
//        ResponseResult<TerminalResponse> responseResult = serviceMapClient.addTerminal(car.getVehicleNo(), car.getId()+"");
//        String tid = responseResult.getData().getTid();
//        car.setTid(tid);
//
//        // 获得此车辆的轨迹id：trid
//        ResponseResult<TrackResponse> trackResponseResponseResult = serviceMapClient.addTrack(tid);
//        String trid = trackResponseResponseResult.getData().getTrid();
//        String trname = trackResponseResponseResult.getData().getTrname();
//
//        car.setTrid(trid);
//        car.setTrname(trname);
//
//        carMapper.updateById(car);


        return ResponseResult.success("");
    }

    public ResponseResult<Car> getCarById(Long id){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);

        List<Car> cars = carMapper.selectByMap(map);

        return ResponseResult.success(cars.get(0));

    }

}
