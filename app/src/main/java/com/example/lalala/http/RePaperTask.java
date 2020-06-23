package com.example.lalala.http;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.example.lalala.UserActivity;
import com.example.lalala.entity.PaperSimpleData;
import com.example.lalala.shared_info.SaveUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RePaperTask extends AsyncTask<Map<Integer, Integer>, Void, String> {
    private MessageResponse messageResponse;

    public void setMessageResponse(MessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }

    @Override
    protected String doInBackground(Map<Integer, Integer>... maps) {
        Gson gson = new Gson();
        String json = gson.toJson(maps[0]);
        return HttpHandler.doPost(HttpHandler.url + "/recommend-paper", json);
    }

    @Override
    protected void onPostExecute(String res) {
        Gson gson = new Gson();
        //兴趣和可能感兴趣论文的总列表，每个groupid对应5篇
        Map<Integer,ArrayList<PaperSimpleData>> groupPapers = gson.fromJson(res, new TypeToken<Map<Integer,ArrayList<PaperSimpleData>>>() {
        }.getType());

//        ArrayList<PaperSimpleData> allpapers = gson.fromJson(res, new TypeToken<ArrayList<PaperSimpleData>>() {
//        }.getType());

        int size1 = SaveUser.userInfor.getGroupID().size();
        int i =1;

        for(Integer groupId : groupPapers.keySet()){
            List<PaperSimpleData> papers = groupPapers.get(groupId);
            //如果请求到的论文不足五篇，就将页数设为0
            if(papers.size()<5){
                SaveUser.groupPage.put(groupId,0);
            }
            if(i<=size1){
                SaveUser.rePapers.addAll(papers);
            }else{
                SaveUser.rePapersC.addAll(papers);
            }
            i++;
        }


//        int size1 = SaveUser.userInfor.getGroupID().size() * 5;
//        int size2 = allpapers.size();
//        Log.d("Browse", "Size: " + size1 + "  " + size2);
//        int i = 1;
//        for (; i <= size1; i++) {
//            //将感兴趣的论文加入感兴趣列表
//            SaveUser.rePapers.add(allpapers.get(i - 1));
//        }
//        for (; i <= size2; i++) {
//            //将可能感兴趣的论文加入可能列表
//            SaveUser.rePapersC.add(allpapers.get(i - 1));
//        }
        //打乱顺序
        Collections.shuffle(SaveUser.rePapers);
        Collections.shuffle(SaveUser.rePapersC);
        messageResponse.onReceived(res);
    }
}
