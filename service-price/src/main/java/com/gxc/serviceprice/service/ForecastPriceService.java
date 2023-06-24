package com.gxc.serviceprice.service;

import com.gax.internalcommon.dto.ResponseResult;
import com.gax.internalcommon.responese.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ForecastPriceService {

    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        log.info("出发地的经度" + depLongitude);
        log.info("出发地的纬度" + depLatitude);
        log.info("出发地的经度" + destLongitude);
        log.info("出发地的纬度度" + destLatitude);
        log.info("调用地图服务,查询距离和时长");
        log.info("读取计价规则");
        log.info("根据计价规则");
        log.info("根据距离,时长和规则,计算价格");
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.23);
        return ResponseResult.success(forecastPriceResponse);
    }
}
