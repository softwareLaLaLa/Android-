package com.example.lalala.discussion_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lalala.DiscussionActivity;
import com.example.lalala.R;
import com.example.lalala.model.CommendItem;
import com.example.lalala.model.PaperBrowseItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommendAdapter extends RecyclerView.Adapter<CommendAdapter.ViewHolder>{
    private List<CommendItem> commendItems = new ArrayList<>();
    private Context context;

    public CommendAdapter(List<CommendItem> commendItems, Context context) {
        this.commendItems = commendItems;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView usrName;
        TextView commentContent;
        Button likeButton;
        Button replyButton;

        public ViewHolder(View view){
            super(view);
            usrName = view.findViewById(R.id.usrName);
            commentContent = view.findViewById(R.id.commendContent);
            likeButton = view.findViewById(R.id.like);
            replyButton = view.findViewById(R.id.reply);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.commenditem,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.likeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(commendItems.get(holder.getAdapterPosition()).like){
                    commendItems.get(holder.getAdapterPosition()).like = false;
                    holder.likeButton.setBackgroundResource(R.drawable.like_false);
                    commendItems.get(holder.getAdapterPosition()).decLikeNum();
                }else {
                    commendItems.get(holder.getAdapterPosition()).like = true;
                    holder.likeButton.setBackgroundResource(R.drawable.like_true);
                    commendItems.get(holder.getAdapterPosition()).addLikeNum();
                }
            }
        });
        holder.replyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((DiscussionActivity) context).showPopupCommnet(commendItems.get(holder.getAdapterPosition()));
                Toast.makeText(context, "回复", Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommendItem item = commendItems.get(position);
        holder.usrName.setText(item.getUserID());
        holder.commentContent.setText(item.getCommendContent());
    }

    @Override
    public int getItemCount() {
        return commendItems.size();
    }
}
