package com.bw.com.model;

import com.bw.com.bean.MessAgeBean;
import com.bw.com.utils.GsonCallBack;

/**
 * Created by ruixuyao on 2018/03/30.
 */

public interface IMessAgeModel {
    void getRightData(String url, GsonCallBack<MessAgeBean> callBack);
}
