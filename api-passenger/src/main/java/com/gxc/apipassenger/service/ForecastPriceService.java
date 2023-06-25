package com.gxc.apipassenger.service;

import com.gax.internalcommon.dto.ResponseResult;
import com.gax.internalcommon.request.ForecastPriceDTO;
import com.gax.internalcommon.responese.ForecastPriceResponse;
import com.gxc.apipassenger.remoto.ServicePriceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ForecastPriceService {


    @Autowired
    ServicePriceClient servicePriceClient;
    /**
     * 根据出发地到目的地,计算预估价格
     *
     * @param
     * @param
     * @param
     * @param
     * @return
     */
    public ResponseResult forecastPrice(ForecastPriceDTO forecastPriceDTO) {
//        log.info("出发地的经度" + depLongitude);
//        log.info("出发地的纬度" + depLatitude);
//        log.info("出发地的经度" + destLongitude);
//        log.info("出发地的纬度度" + destLatitude);
        log.info("调用计价服务,计算价格");

//        ResponseResult<ForecastPriceResponse> forecast = servicePriceClient.forecast(forecastPriceDTO);
//        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
//        forecastPriceResponse.setPrice(forecast.getData().getPrice());
        return servicePriceClient.forecast(forecastPriceDTO);
    }
}
