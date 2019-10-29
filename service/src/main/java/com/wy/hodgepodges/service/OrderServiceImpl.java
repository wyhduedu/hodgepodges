package com.wy.hodgepodges.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-10-22 00:05
 */
@FunctionCode(value = "order", descript = "订单相关API")
@Service
public class OrderServiceImpl implements OrderService {


    @FunctionCode(value = "order.placeOrder", descript = "支付成功后的订单处理接口")
    @Override
    public ApiResponse<HashMap<String, Object>> placeOrder(ApiRequest apiRequest) throws Exception {
        ApiResponse<HashMap<String, Object>> apiResponse = null;
        try {
            //如果支付成功则调用此接口，并将订单的数据拆解存储对应的数据表

        } catch (Exception ex) {
            apiResponse = new ApiResponse<HashMap<String, Object>>();
        }
        return apiResponse;
    }

}
