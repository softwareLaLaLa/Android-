package com.example.lalala.ui.search;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lalala.PaperActivity;
import com.example.lalala.R;
import com.example.lalala.entity.PaperSimpleData;
import com.example.lalala.entity.UserHistoryEntity;
import com.example.lalala.http.MessageResponse;
import com.example.lalala.shared_info.SaveUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> implements MessageResponse {
    private List<PaperSimpleData> searchResultPapers = new ArrayList<>();
    private Context context;
    private Intent intent;

    public SearchAdapter(List<PaperSimpleData> searchResultPapers, Context context) {
        this.context = context;
        this.searchResultPapers = searchResultPapers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        final PaperSimpleData item = searchResultPapers.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserHistoryEntity userHistoryEntity = new UserHistoryEntity();
                userHistoryEntity.setUserId(SaveUser.userInfoEntity.getId());
                userHistoryEntity.setPaperId(item.getPaperEntity().getId());
                userHistoryEntity.setBrowseTime(new Date());
                userHistoryEntity.setUncheck(true);
                SaveUser.browseHistory.add(userHistoryEntity);

                SaveUser.currentPaper = item;
            }
        });
        holder.title.setText(item.getPaperEntity().getTitle());
        //holder.author.setText(item.getAuthor());
        List<String> tagSimpleData = item.getTags();
        holder.tag1.setText(tagSimpleData.get(0));
        holder.tag2.setText(tagSimpleData.get(1));
        holder.tag3.setText(tagSimpleData.get(2));
        holder.browseNum.setText("点击量" + item.getPaperEntity().getBrowseNum());
    }

    @Override
    public int getItemCount() {
        return searchResultPapers.size();
    }

    @Override
    public void onReceived(String resJson) {
        context.startActivity(intent);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView tag1, tag2, tag3;
        public TextView browseNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            tag1 = itemView.findViewById(R.id.tag1);
            tag2 = itemView.findViewById(R.id.tag2);
            tag3 = itemView.findViewById(R.id.tag3);
            browseNum = itemView.findViewById(R.id.browseNum);
        }
    }
}
