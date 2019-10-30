package com.example.lalala.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lalala.R;

public class FollowFieldAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    public FollowFieldAdapter(Context context){
        mContext=context;
        mLayoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return 9;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView=mLayoutInflater.inflate(R.layout.follow_field_item,null);
            holder=new ViewHolder();
            holder.imageView=convertView.findViewById(R.id.iv_follow_field_type);
            holder.textView=convertView.findViewById(R.id.tv_follow_field_name);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.textView.setText("领域"+position);
        return convertView;
    }
}
