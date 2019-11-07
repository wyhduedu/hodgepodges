package com.wy.hodgepodges.common.design.handler;

/**
 * @author wy
 * @version V1.0
 * @desc
 * @date 2019-11-06 23:28
 */
public abstract class AbstractHandler {

    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

}