package com.example.lalala.ui.browse_fragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lalala.PaperActivity;
import com.example.lalala.R;
import com.example.lalala.entity.PaperSimpleData;
import com.example.lalala.entity.SquarePaperData;
import com.example.lalala.entity.UserHistoryEntity;
import com.example.lalala.shared_info.SaveUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SquareAdapter extends RecyclerView.Adapter<SquareAdapter.ViewHolder>{
    private List<SquarePaperData> paperBrowseItems = new ArrayList<>();
    private List<PaperSimpleData> paperSimpleDataList = new ArrayList<>();
    private Context context;
    private Intent intent;
    //分辨广场列表或是论文列表
    private int type;

    public SquareAdapter(List<SquarePaperData> paperBrowseItems, Context context) {
        this.paperBrowseItems = paperBrowseItems;
        this.context = context;
        this.type = 1;
    }

    public SquareAdapter(List<PaperSimpleData> paperSimpleData, Context context, int type) {
        this.paperSimpleDataList = paperSimpleData;
        this.context = context;
        this.type = type;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout userData;
        public TextView userName;
        public Button subscribe;
        public LinearLayout paperData;
        public TextView title;
        public TextView abst;
        public TextView browseNum;
        public Button dislike;
        //public TextView citeNum;

        public ViewHolder(View view) {
            super(view);
            userData = view.findViewById(R.id.user_data);
            userName = view.findViewById(R.id.user_name);
            subscribe = view.findViewById(R.id.subscribe);
            paperData = view.findViewById(R.id.paper_data);
            title = view.findViewById(R.id.title);
            abst = view.findViewById(R.id.abst);
            browseNum = view.findViewById(R.id.browseNum);
            dislike = view.findViewById(R.id.dislike);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.square_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final PaperSimpleData paperSimpleData;
        //若是广场列表，需要初始化用户数据区
        if(type == 1){
            final SquarePaperData squarePaperData = paperBrowseItems.get(position);
            paperSimpleData = new PaperSimpleData();
            paperSimpleData.setPaperEntity(squarePaperData.getPaperEntity());
            paperSimpleData.setTags(squarePaperData.getSquarePaperRecommendData().getTags());
            holder.userData.setVisibility(View.VISIBLE);
            holder.userName.setText(squarePaperData.getSquarePaperRecommendData().getUserSubscribeData().getUserName());
            holder.subscribe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(paperBrowseItems.get(position).getSquarePaperRecommendData().getUserSubscribeData().isSubscribe()){
                        paperBrowseItems.get(position).getSquarePaperRecommendData().getUserSubscribeData().setSubscribe(false);
                        SaveUser.userSubscribe.remove(squarePaperData.getSquarePaperRecommendData().getUserSubscribeData().getUserId());
                    }else{
                        paperBrowseItems.get(position).getSquarePaperRecommendData().getUserSubscribeData().setSubscribe(true);
                        SaveUser.userSubscribe.add(squarePaperData.getSquarePaperRecommendData().getUserSubscribeData().getUserId());
                    }
                }
            });
            if(paperBrowseItems.get(position).getSquarePaperRecommendData().getUserSubscribeData().isSubscribe()) {
                holder.subscribe.setBackgroundResource(R.drawable.subscribe);
            }else{
                holder.subscribe.setBackgroundResource(R.drawable.unsubscribe);
            }
        }else {
            System.out.println("onBindViewHolder: position = " + position);
            paperSimpleData = paperSimpleDataList.get(position);
            holder.userData.setVisibility(View.GONE);
        }
        if(type==3){
            System.out.println("hotPaperFragment");
        }
        holder.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveUser.userDislike.add(paperSimpleData.getPaperEntity().getId());
                paperBrowseItems.remove(position);
                notifyItemRemoved(position);
            }
        });
        //进入论文详细界面，并添加历史记录
        holder.paperData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SaveUser.currentPaper = paperSimpleData;
                UserHistoryEntity userHistoryEntity = new UserHistoryEntity();
                userHistoryEntity.setUserId(SaveUser.userInfoEntity.getId());
                userHistoryEntity.setPaperId(paperSimpleData.getPaperEntity().getId());
                userHistoryEntity.setBrowseTime(new Date());
                userHistoryEntity.setUncheck(true);
                SaveUser.browseHistory.add(userHistoryEntity);
                intent = new Intent(context, PaperActivity.class);
                context.startActivity(intent);
            }
        });

        holder.title.setText(paperSimpleData.getPaperEntity().getTitle());
        holder.abst.setText(paperSimpleData.getPaperEntity().getAbst());
        holder.browseNum.setText("浏览量 " + paperSimpleData.getPaperEntity().getBrowseNum() + " · 热度 " + paperSimpleData.getPaperEntity().getRecentBrowseNum());
        if(position == getItemCount()){

        }
    }

    @Override
    public int getItemCount() {
        if(type == 1){
            return paperBrowseItems.size();
        }else{
            return paperSimpleDataList.size();
        }
    }

}
