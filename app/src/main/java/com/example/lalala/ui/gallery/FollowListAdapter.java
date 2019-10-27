package com.example.lalala.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lalala.R;

public class FollowListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public FollowListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        public ImageView imageView;
        public TextView author_name;
        public TextView author_disp;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.follow_list_item, null);
            holder=new ViewHolder();
            holder.imageView=convertView.findViewById(R.id.follow_iv_author);
            holder.author_disp=convertView.findViewById(R.id.author_disp);
            holder.author_name=convertView.findViewById(R.id.author_name);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.author_name.setText("作者名"+position);
        holder.author_disp.setText("作者简介"+position);
        return convertView;
    }
}
