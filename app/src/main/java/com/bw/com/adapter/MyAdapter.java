package com.bw.com.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bw.com.bean.MessAgeBean;
import com.bw.com.utils.MyImageLoader;
import com.bw.com.xm1_week2.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by ruixuyao on 2018/03/30.
 */

public class MyAdapter extends BaseAdapter{
    private Context context;
    private List<MessAgeBean.PostsBean> list;

    public MyAdapter(Context context, List<MessAgeBean.PostsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view=View.inflate(context, R.layout.item_layout,null);
            holder=new ViewHolder();
            holder.text_title=view.findViewById(R.id.text_title);
            holder.text_name=view.findViewById(R.id.text_name);
            holder.image_view=view.findViewById(R.id.image_view);
            holder.text_ping=view.findViewById(R.id.text_ping);
            view.setTag(holder);
        }else{
            holder=(ViewHolder) view.getTag();
        }
        holder.text_title.setText(list.get(i).getTitle());
        holder.text_name.setText(list.get(i).getAuthor().getName());
        holder.text_ping.setText("评论"+list.get(i).getComment_count());
        String s = list.get(i).getCustom_fields().getThumb_c().get(0);
        String s1 = s.substring(0, s.length());
        ImageLoader.getInstance().displayImage(s1,holder.image_view, MyImageLoader.getImageOptin(context));
        return view;
    }
    class ViewHolder{
        TextView text_title;
        TextView text_name;
        TextView text_ping;
        ImageView image_view;
    }
}
