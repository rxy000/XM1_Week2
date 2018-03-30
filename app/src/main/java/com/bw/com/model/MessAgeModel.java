package com.bw.com.model;

import com.bw.com.bean.MessAgeBean;
import com.bw.com.utils.GsonCallBack;
import com.bw.com.utils.OkHttpUtil;

/**
 * Created by ruixuyao on 2018/03/30.
 */

public class MessAgeModel implements IMessAgeModel{
    @Override
    public void getRightData(String url, GsonCallBack<MessAgeBean> callBack) {
        OkHttpUtil.getInstance().doGet(url,callBack);
    }
}
