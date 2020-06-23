package com.example.lalala.http;

import android.os.AsyncTask;

import com.example.lalala.entity.BrowseHistory;
import com.example.lalala.entity.EvalEntity;
import com.example.lalala.shared_info.SaveUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FinalTask extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... voids) {
        Gson gson = new Gson();
        //发送并清空浏览记录
        if (SaveUser.browseHistory.size() > 0) {
            BrowseHistory browseHistory = new BrowseHistory(SaveUser.userInfor.getUsr_id(), SaveUser.browseHistory);
            String jsonBrowse = gson.toJson(browseHistory, BrowseHistory.class);
            HttpHandler.doPost(HttpHandler.url + "/browse-history", jsonBrowse);
            SaveUser.browseHistory.clear();
        }
        //发送并清空评估信息
        if (SaveUser.evalPapers.size() > 0) {
            Type type = new TypeToken<List<EvalEntity>>() {
            }.getType();
            String jsonEvaluation = gson.toJson(SaveUser.evalPapers, type);
            System.out.println("发送评估信息：");
            System.out.println(jsonEvaluation);
            HttpHandler.doPost(HttpHandler.url + "/evaluation-data", jsonEvaluation);
            SaveUser.evalPapers.clear();
        }
        //发送并清空添加tag信息
        if (SaveUser.paperTag.size() > 0) {
            Type type = new TypeToken<Map<Integer, Set<Integer>>>() {
            }.getType();
            String jsonPaperTag = gson.toJson(SaveUser.paperTag, type);
            HttpHandler.doPost(HttpHandler.url + "/paper-tag", jsonPaperTag);
        }
        if (SaveUser.paperNewTag.size() > 0) {
            Type type = new TypeToken<Map<Integer, Set<String>>>() {
            }.getType();
            String jsonPaperNewTag = gson.toJson(SaveUser.paperTag, type);
            HttpHandler.doPost(HttpHandler.url + "/paper-new-tag", jsonPaperNewTag);
        }
        //发送用户tag是否要更新
        if (SaveUser.updateUserTag) {
            Map<String, Integer> userId = new HashMap<>();
            userId.put("id", SaveUser.userInfor.getUsr_id());
            Type type = new TypeToken<Map<String, Integer>>() {
            }.getType();
            String json = gson.toJson(userId, type);
            System.out.println("更新用户tag：");
            System.out.println(json);
            HttpHandler.doPost(HttpHandler.url + "/user-data", json);
            SaveUser.updateUserTag = false;
        }

        return null;
    }
}
