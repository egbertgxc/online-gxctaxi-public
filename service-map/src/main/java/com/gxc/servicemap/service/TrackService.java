package com.gxc.servicemap.service;

import com.gxc.internalcommon.dto.ResponseResult;
import com.gxc.internalcommon.responese.TrackResponse;
import com.gxc.servicemap.remote.TrackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackService {

    @Autowired
    TrackClient trackClient;

    public ResponseResult<TrackResponse> add(String tid){

        return trackClient.add(tid);
    }
}
