package com.gxc.servicemap.service;

import com.gxc.internalcommon.dto.ResponseResult;
import com.gxc.internalcommon.responese.DirectionResponse;
import com.gxc.servicemap.remote.MapDirectionClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DirectionService {

    @Autowired
    private MapDirectionClient mapDirectionClient;
    /**
     *
     *  根据起点经纬度和终点经纬度获取距离(米)和时长(分钟)
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return
     */
    public ResponseResult driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        //地图接口的调用
        DirectionResponse direction = mapDirectionClient.direction(depLongitude, depLatitude, destLongitude, destLatitude);
        return ResponseResult.success(direction);
    }
}
