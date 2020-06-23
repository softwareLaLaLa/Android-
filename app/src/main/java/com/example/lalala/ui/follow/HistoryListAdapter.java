package com.example.lalala.ui.follow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lalala.R;
import com.example.lalala.entity.PaperSimpleData;
import com.example.lalala.shared_info.SaveUser;

public class HistoryListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;


    public HistoryListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int size = SaveUser.userInfor.getBrowseHistory().size();
        if (size > 10) {
            size = 10;
        }
        return size;
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
        public TextView paperTitle;
        //public TextView author_disp;
        public TextView tag1;
        public TextView tag2;
        public TextView tag3;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PaperSimpleData paper = SaveUser.userInfor.getBrowseHistory().get(position);

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.follow_list_item, null);
            holder = new ViewHolder();
            //holder.author_disp=convertView.findViewById(R.id.author_disp);
            holder.paperTitle = convertView.findViewById(R.id.tv_title);
            holder.paperTitle.setText(paper.getTitle());
            holder.tag1 = convertView.findViewById(R.id.browse_tag1);
            holder.tag1.setText(paper.getTagList().get(0));
            holder.tag2 = convertView.findViewById(R.id.browse_tag2);
            holder.tag2.setText(paper.getTagList().get(1));
            holder.tag3 = convertView.findViewById(R.id.browse_tag3);
            holder.tag3.setText(paper.getTagList().get(2));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        holder.paperTitle.setText("记录" + position);
//        //holder.author_disp.setText("作者简介"+position);
//        holder.tag1.setText("tag1");
//        holder.tag2.setText("tag2");
//        holder.tag3.setText("tag3");

//        holder.paperTitle.setText(paper.getTitle());
//        //holder.author_disp.setText("作者简介"+position);
//        holder.tag1.setText(paper.getTagList().get(0));
//        holder.tag2.setText(paper.getTagList().get(1));
//        holder.tag3.setText(paper.getTagList().get(2));
        return convertView;
    }
}
