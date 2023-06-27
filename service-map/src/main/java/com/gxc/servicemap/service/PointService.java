package com.gxc.servicemap.service;

import com.gxc.internalcommon.dto.ResponseResult;
import com.gxc.internalcommon.request.PointRequest;
import com.gxc.servicemap.remote.PointClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {

    @Autowired
    PointClient pointClient;

    public ResponseResult upload(PointRequest pointRequest){

        return pointClient.upload(pointRequest);
    }
}
