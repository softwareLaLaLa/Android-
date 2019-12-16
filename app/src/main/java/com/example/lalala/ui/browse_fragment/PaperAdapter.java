package com.example.lalala.ui.browse_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lalala.R;
import com.example.lalala.model.PaperBrowseItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PaperAdapter extends RecyclerView.Adapter<PaperAdapter.ViewHolder>{
    private List<PaperBrowseItem> paperBrowseItems = new ArrayList<>();
    private Context context;

    public PaperAdapter(List<PaperBrowseItem> paperBrowseItems, Context context) {
        this.paperBrowseItems = paperBrowseItems;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView author;
        public TextView type1, type3;
        public TextView commentNum;
        public TextView citeNum;

        public ViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.title);
            author = view.findViewById(R.id.author);
            type1 = view.findViewById(R.id.type1);
            type3 = view.findViewById(R.id.type3);
            commentNum = view.findViewById(R.id.commentNum);
            citeNum = view.findViewById(R.id.citeNum);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "根据DOI发出请求进入讨论界面", Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PaperBrowseItem item = paperBrowseItems.get(position);
        holder.title.setText(item.getTitle());
        holder.author.setText(item.getAuthor());
        holder.type1.setText(item.getSubjects().get(0));
        holder.type3.setText(item.getSubjects().get(2));
        holder.type3.setVisibility(View.VISIBLE);
        holder.commentNum.setText("评论数"+item.getComment());
        holder.citeNum.setText("点赞数" + item.getCite());
    }

    @Override
    public int getItemCount() {
        return paperBrowseItems.size();
    }
}
