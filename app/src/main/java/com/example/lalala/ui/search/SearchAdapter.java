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
import com.example.lalala.http.MessageResponse;
import com.example.lalala.http.PaperDataTask;
import com.example.lalala.http.SearchTask;
import com.example.lalala.shared_info.SaveUser;
import com.example.lalala.ui.browse_fragment.PaperAdapter;

import java.util.ArrayList;
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
                PaperDataTask paperDataTask = new PaperDataTask();
                paperDataTask.setMessageResponse(SearchAdapter.this);
                paperDataTask.execute(item.getPaper_id());
                intent = new Intent(context, PaperActivity.class);
                intent.putExtra("title", item.getTitle());

                SaveUser.browseHistory.add(item);
                SaveUser.userInfor.getBrowseHistory().add(item);
                intent.putExtra("paperId", item.getPaper_id());
            }
        });
        holder.title.setText(item.getTitle());
        //holder.author.setText(item.getAuthor());
        List<String> tagSimpleData = item.getTagList();
        holder.tag1.setText(tagSimpleData.get(0));
        holder.tag2.setText(tagSimpleData.get(1));
        holder.tag3.setText(tagSimpleData.get(2));
        holder.browseNum.setText("点击量" + item.getTotalBrowseNum());
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
