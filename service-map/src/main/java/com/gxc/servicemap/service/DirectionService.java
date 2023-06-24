package com.gxc.servicemap.service;

import com.gax.internalcommon.dto.ResponseResult;
import com.gax.internalcommon.responese.DirectionResponse;
import com.gax.internalcommon.responese.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DirectionService {

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
        log.info("出发地的经度" + depLongitude);
        log.info("出发地的纬度" + depLatitude);
        log.info("出发地的经度" + destLongitude);
        log.info("出发地的纬度度" + destLatitude);
        DirectionResponse directionResponse = new DirectionResponse();
        directionResponse.setDistance(123);
        directionResponse.setDuration(11);
        return ResponseResult.success(directionResponse);
    }
}
