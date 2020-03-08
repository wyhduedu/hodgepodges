package com.wy.hodgepodges.service;


import com.wy.hodgepodges.sdk.request.ApiRequest;
import com.wy.hodgepodges.sdk.response.ApiResponse;

import java.util.HashMap;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-10-22 00:05
 */
public interface OrderService {

    ApiResponse<HashMap<String, Object>> placeOrder(ApiRequest apiRequest) throws Exception;

}
