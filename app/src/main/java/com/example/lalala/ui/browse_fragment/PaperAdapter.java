package com.example.lalala.ui.browse_fragment;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lalala.PaperActivity;
import com.example.lalala.R;
import com.example.lalala.entity.PaperData;
import com.example.lalala.entity.PaperSimpleData;
import com.example.lalala.entity.TagSimpleData;
import com.example.lalala.http.MessageResponse;
import com.example.lalala.http.PaperDataTask;
import com.example.lalala.shared_info.SaveUser;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PaperAdapter extends RecyclerView.Adapter<PaperAdapter.ViewHolder> implements MessageResponse {
    private List<PaperSimpleData> paperBrowseItems = new ArrayList<>();
    private Context context;
    private Intent intent;

    public PaperAdapter(List<PaperSimpleData> paperBrowseItems, Context context) {
        this.paperBrowseItems = paperBrowseItems;
        this.context = context;
    }

    @Override
    public void onReceived(String resJson) {
        Log.d("PaperAdapter", "End!");
        context.startActivity(intent);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        //public TextView author;
        public TextView tag1, tag2, tag3;
        public TextView browseNum;
        //public TextView citeNum;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            //author = view.findViewById(R.id.author);
            tag1 = view.findViewById(R.id.tag1);
            tag2 = view.findViewById(R.id.tag2);
            tag3 = view.findViewById(R.id.tag3);
            browseNum = view.findViewById(R.id.browseNum);
            //citeNum = view.findViewById(R.id.citeNum);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final PaperSimpleData item = paperBrowseItems.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(SaveUser.Debug){
                    List<TagSimpleData> existTags = new ArrayList<>();
                    existTags.add(new TagSimpleData(1,"tag1"));
                    existTags.add(new TagSimpleData(2,"tag2"));
                    existTags.add(new TagSimpleData(3,"tag3"));
                    existTags.add(new TagSimpleData(4,"tag4"));
                    List<TagSimpleData> recTags = new ArrayList<>();
                    recTags.add(new TagSimpleData(5,"tag5"));
                    recTags.add(new TagSimpleData(6,"tag6"));
                    recTags.add(new TagSimpleData(3,"tag3"));
                    recTags.add(new TagSimpleData(4,"tag4"));
                    SaveUser.curPaper= new PaperData(1,"这是摘要","这是url",existTags,recTags);
                }
                else {
                    //根据点击的论文请求相应的论文详细信息, 并添加历史记录
                    PaperDataTask paperDataTask =new PaperDataTask();
                    paperDataTask.setMessageResponse(PaperAdapter.this);
                    paperDataTask.execute(item.getPaper_id());
                    Log.d("PaperAdapter", "Begin request!");
                    SaveUser.browseHistory.add(item);
                    SaveUser.userInfor.getBrowseHistory().add(item);
                }

                intent = new Intent(context, PaperActivity.class);
                intent.putExtra("title",item.getTitle());
                //更新历史记录

                //intent.putExtra("title", "一个论文标题" + position);
                intent.putExtra("paperId", item.getPaper_id());
                if(SaveUser.Debug){
                    context.startActivity(intent);
                }
            }
        });

        holder.title.setText(item.getTitle());
        //holder.author.setText(item.getAuthor());
        List<String> tagSimpleData = item.getTagList();
        holder.tag1.setText(tagSimpleData.get(0));
        holder.tag2.setText(tagSimpleData.get(1));
        holder.tag3.setText(tagSimpleData.get(2));
        holder.browseNum.setText("点击量" + item.getTotalBrowseNum());
        //holder.citeNum.setText("点赞数" + item.getCite());
    }

    @Override
    public int getItemCount() {
        return paperBrowseItems.size();
    }

}
