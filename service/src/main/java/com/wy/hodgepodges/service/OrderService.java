package com.wy.hodgepodges.service;


import java.util.HashMap;
import com.wy.hodgepodges.api.*;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-10-22 00:05
 */
public interface OrderService {

    ApiResponse<HashMap<String, Object>> placeOrder(ApiRequest apiRequest) throws Exception;

}
