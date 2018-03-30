package com.bw.com.xm1_week2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.bawei.jane.mxlistview.view.XListView;
import com.bw.com.adapter.MyAdapter;
import com.bw.com.bean.MessAgeBean;
import com.bw.com.presenter.MessAgePresenter;
import com.bw.com.view.IMessAgeView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMessAgeView{
    private XListView xlv;
    private MessAgePresenter presenter;
    private String url1="http://i.jandan.net/?include=url,date,tags,author,title,excerpt,comment_count,comment_status,custom_fields&page=";
    private int page=1;
    private String url2="&custom_fields=thumb_c,views&dev=1&oxwlxojflwblxbsapi=get_recent_posts";
    private String url=url1+page+url2;
    private int type=1;
    private List<MessAgeBean.PostsBean> data=new ArrayList<>();
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        xlv = findViewById(R.id.xlv);
        //实例化presenter获取数据
        presenter = new MessAgePresenter();
        presenter.attachView(this);
        presenter.getGoodsList(page);
        //给XListview进行设置
        xlv.setPullLoadEnable(true);//上拉加载更多
        xlv.setPullRefreshEnable(true);//下拉刷新
        xlv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {//刷新
                page++;
                presenter.getGoodsList(page);
            }
            @Override
            public void onLoadMore() {//加载更多
                page=1;
                presenter.getGoodsList(page);
            }
        });
    }

    private void initView() {
        xlv = findViewById(R.id.xlv);
    }

    @Override
    public void showMessAge(final List<MessAgeBean.PostsBean> list) {
        xlv.setAdapter(new MyAdapter(MainActivity.this,list));
        if(type==1){//代表刷新操作
            data.clear();//清空原有的集合数据
        }
        //增加新的数据集合到原来的集合对象中
        data.addAll(list);
        setAdapter();
        if(type==1){
            xlv.stopRefresh();//关闭头布局
            Date date=new Date(System.currentTimeMillis());
            SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String refreshTime = sf.format(date);
            xlv.setRefreshTime(refreshTime);//设置刷新时间
        }else{
            xlv.stopLoadMore();//关闭尾布局
        }
    }

    private void setAdapter() {
        if(adapter==null){
            adapter = new MyAdapter(MainActivity.this,data);
            xlv.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
    }
}
