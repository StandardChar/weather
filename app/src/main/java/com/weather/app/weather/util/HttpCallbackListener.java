package com.weather.app.weather.util;

/**
 * Created by andy on 2016/9/11.
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
