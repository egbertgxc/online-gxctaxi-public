package com.gxc.serviceprice.service;

import com.gax.internalcommon.constent.CommonStatusEnum;
import com.gax.internalcommon.dto.PriceRule;
import com.gax.internalcommon.dto.ResponseResult;
import com.gax.internalcommon.request.ForecastPriceDTO;
import com.gax.internalcommon.responese.DirectionResponse;
import com.gax.internalcommon.responese.ForecastPriceResponse;
import com.gxc.serviceprice.mapper.PriceRuleMapper;
import com.gxc.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class ForecastPriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    private PriceRuleMapper priceRuleMapper;

    public ResponseResult forecastPrice(ForecastPriceDTO forecastPriceDTO) {
//        log.info("出发地的经度" + depLongitude);
//        log.info("出发地的纬度" + depLatitude);
//        log.info("出发地的经度" + destLongitude);
//        log.info("出发地的纬度度" + destLatitude);
        log.info("调用地图服务,查询距离和时长");
        log.info("读取计价规则");
        HashMap<String, Object> queryMap = new HashMap<>();
        queryMap.put("city_code","110000");
        queryMap.put("vehicle_type","1");
        List<PriceRule> priceRules = priceRuleMapper.selectByMap(queryMap);
        if (priceRules.size() == 0) {
            return ResponseResult.fail(CommonStatusEnum.USER_NOT_EXISTS);
        }
        PriceRule priceRule = priceRules.get(0);
        log.info("根据计价规则");
        log.info("根据距离,时长和规则,计算价格");
        ResponseResult<DirectionResponse> direction = serviceMapClient.direction(forecastPriceDTO);
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(12.23);
        return ResponseResult.success(direction);
    }
}
