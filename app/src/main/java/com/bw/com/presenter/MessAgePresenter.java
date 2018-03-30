package com.bw.com.presenter;

import android.util.Log;
import com.bw.com.bean.MessAgeBean;
import com.bw.com.model.MessAgeModel;
import com.bw.com.utils.GsonCallBack;
import com.bw.com.view.IMessAgeView;
import java.io.IOException;
import java.lang.ref.WeakReference;
import okhttp3.Call;

/**
 * Created by ruixuyao on 2018/03/30.
 */

public class MessAgePresenter {
    private WeakReference<IMessAgeView> reference;
    private MessAgeModel model;
    public MessAgePresenter(){
        model=new MessAgeModel();
    }
    public void attachView(IMessAgeView view) {
        reference = new WeakReference<IMessAgeView>(view);
    }

    public void detachView(IMessAgeView view) {
        if (reference != null) {
            reference.clear();
            reference = null;
        }
    }
    public void getGoodsList(int page){


        model.getRightData("http://i.jandan.net/?include=url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields&page="+page+"&custom_fields=thumb_c,views&dev=1&oxwlxojflwblxbsapi=get_recent_posts", new GsonCallBack<MessAgeBean>() {
            @Override
            public void onUi(MessAgeBean goodsBean) {
                Log.i("+++",goodsBean.toString());
                if (goodsBean != null && goodsBean.getPosts() != null) {
                    reference.get().showMessAge(goodsBean.getPosts());
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }
}
