package com.gxc.apiDriver.remote;

import com.gxc.internalcommon.dto.ResponseResult;
import com.gxc.internalcommon.request.PointRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-map")
public interface ServiceMapClient {

    @RequestMapping(method = RequestMethod.POST, value = "/point/upload")
    public ResponseResult upload(@RequestBody PointRequest pointRequest);

}
