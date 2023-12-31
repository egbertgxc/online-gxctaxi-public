package com.gxc.apipassenger.remoto;

import com.gxc.internalcommon.dto.ResponseResult;
import com.gxc.internalcommon.request.ForecastPriceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-price")
public interface ServicePriceClient {

    @RequestMapping(method = RequestMethod.POST,value = "/forecast-price")
    public ResponseResult forecast(@RequestBody ForecastPriceDTO forecastPriceDTO);
}
