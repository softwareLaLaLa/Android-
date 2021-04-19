package com.example.lalala.http;

import android.os.AsyncTask;

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
            Map<String, Object> data = new HashMap<>();
            data.put("userId", SaveUser.userInfoEntity.getId());
            data.put("userHistories", SaveUser.browseHistory);

            HttpHandler.doPost(HttpHandler.recommendUrl + "/updateHistory", gson.toJson(data));
            SaveUser.browseHistory.clear();
        }
        //发送并清空添加tag信息
        if (SaveUser.paperTagData.size() > 0) {
            HttpHandler.doPost(HttpHandler.tagUrl + "/addPaperTag", gson.toJson(SaveUser.paperTagData));
        }

        return null;
    }
}
