package com.gxc.apipassenger.service;

import com.gax.internalcommon.dto.ResponseResult;
import com.gax.internalcommon.responese.ForecastPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ForecastPriceService {

    /**
     * 根据出发地到目的地,计算预估价格
     *
     * @param depLongitude
     * @param depLatitude
     * @param destLongitude
     * @param destLatitude
     * @return
     */
    public ResponseResult forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        log.info("出发地的经度" + depLongitude);
        log.info("出发地的纬度" + depLatitude);
        log.info("出发地的经度" + destLongitude);
        log.info("出发地的纬度度" + destLatitude);
        log.info("调用计价服务,计算价格");
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.23);
        return ResponseResult.success(forecastPriceResponse);
    }
}
