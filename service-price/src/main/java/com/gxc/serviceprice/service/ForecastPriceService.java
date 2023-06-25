package com.gxc.serviceprice.service;

import com.gxc.internalcommon.constant.CommonStatusEnum;
import com.gxc.internalcommon.dto.PriceRule;
import com.gxc.internalcommon.dto.ResponseResult;
import com.gxc.internalcommon.request.ForecastPriceDTO;
import com.gxc.internalcommon.responese.DirectionResponse;
import com.gxc.internalcommon.responese.ForecastPriceResponse;
import com.gxc.internalcommon.util.BigDecimalUtils;
import com.gxc.serviceprice.mapper.PriceRuleMapper;
import com.gxc.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();
        double price = getPrice(distance, duration, priceRule);
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(price);
        return ResponseResult.success(forecastPriceResponse);
    }

    /**
     * 根据距离和时长,计价规则,计算最终价格
     * @param distance
     * @param duration
     * @param priceRule
     * @return
     */
    public static double getPrice(Integer distance , Integer duration,PriceRule priceRule){
        double price = 0;

        // 起步价
        double startFare = priceRule.getStartFare();
        price = BigDecimalUtils.add(price,startFare);

        // 里程费
        // 总里程 m
        double distanceMile = BigDecimalUtils.divide(distance,1000);
        // 起步里程
        double startMile = (double)priceRule.getStartMile();
        double distanceSubtract = BigDecimalUtils.substract(distanceMile,startMile);
        // 最终收费的里程数 km
        double mile = distanceSubtract<0?0:distanceSubtract;
        // 计程单价 元/km
        double unitPricePerMile = priceRule.getUnitPricePerMile();
        // 里程价格
        double mileFare = BigDecimalUtils.multiply(mile,unitPricePerMile);
        price = BigDecimalUtils.add(price,mileFare);

        // 时长费
        // 时长的分钟数
        double timeMinute = BigDecimalUtils.divide(duration,60);
        // 计时单价
        double unitPricePerMinute = priceRule.getUnitPricePerMinute();

        // 时长费用
        double timeFare = BigDecimalUtils.multiply(timeMinute,unitPricePerMinute);
        price = BigDecimalUtils.add(price,timeFare);

        BigDecimal priceBigDecimal = new BigDecimal(price);
        priceBigDecimal = priceBigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);

        return priceBigDecimal.doubleValue();
    }

//    public static void main(String[] args) {
//        PriceRule priceRule = new PriceRule();
//        priceRule.setUnitPricePerMile(1.8);
//        priceRule.setUnitPricePerMinute(0.5);
//        priceRule.setStartFare(10.0);
//        priceRule.setStartMile(3);
//
//        System.out.println(getPrice(6500,1800,priceRule));
//    }
}
