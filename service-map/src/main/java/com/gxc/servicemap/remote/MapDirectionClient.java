package com.gxc.servicemap.remote;

import com.gxc.internalcommon.constant.AmapConfigConstants;
import com.gxc.internalcommon.responese.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class MapDirectionClient {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;

    public DirectionResponse direction(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        //  组装请求调用url
        /**
         * https://restapi.amap.com/v3/direction/driving?origin=116.481028,39.989643&destination=116.465302,40.004717&extensions=all&output=json&key=7a90a823f2e7245d276028af9f4e76dd
         */
        StringBuilder urlBuild = new StringBuilder();

        urlBuild.append(AmapConfigConstants.DIRECTION_URL);
        urlBuild.append("?origin=" + depLongitude + "," + depLatitude + "&destination=" + destLongitude + "," + destLatitude);
        urlBuild.append("&extensions=base&output=json");
        urlBuild.append("&key=" + amapKey);
        log.info(urlBuild.toString());

        //  调用高德接口
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(urlBuild.toString(), String.class);
        String directionString = directionEntity.getBody();
        log.info("高德地图.路径规划,返回信息" + directionString);
        //  解析接口

        DirectionResponse responseResult = paresDirectionEntity(directionString);

        return responseResult;
    }

    private DirectionResponse paresDirectionEntity(String directionString) {

        DirectionResponse directionResponse = null;
        try {
            //最外层
            JSONObject result = JSONObject.fromObject(directionString);
            if (result.has(AmapConfigConstants.STATUS)) {
                int status = result.getInt(AmapConfigConstants.STATUS);
                if (status == 1 && result.has(AmapConfigConstants.ROUTE)) {
                    JSONObject routeObject = result.getJSONObject(AmapConfigConstants.ROUTE);
                    JSONArray pathsArray = routeObject.getJSONArray(AmapConfigConstants.PATHS);
                    JSONObject pathObject = pathsArray.getJSONObject(0);
                    directionResponse = new DirectionResponse();
                    if (pathObject.has(AmapConfigConstants.DISTANCE)) {
                        int distance = pathObject.getInt(AmapConfigConstants.DISTANCE);
                        directionResponse.setDistance(distance);
                    }
                    if (pathObject.has(AmapConfigConstants.DURATION)) {
                        int duration = pathObject.getInt(AmapConfigConstants.DURATION);
                        directionResponse.setDuration(duration);
                    }
                }
            }
        } catch (Exception e) {

        }
        return directionResponse;
    }
}
