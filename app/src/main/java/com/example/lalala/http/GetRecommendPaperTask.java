package com.example.lalala.http;

import android.os.AsyncTask;

import com.example.lalala.shared_info.SaveUser;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class GetRecommendPaperTask extends AsyncTask<Void, Void, String> {

    private MessageResponse messageResponse;

    public void setMessageResponse(MessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }

    @Override
    protected String doInBackground(Void... voids) {
        Map<String, Integer> map = new HashMap<>();
        map.put("userId", SaveUser.userInfoEntity.getId());
        SaveUser.pageNum += 1;
        map.put("pageNum", SaveUser.pageNum);
        Gson gson = new Gson();
        String json = gson.toJson(map);
        return HttpHandler.doGet(HttpHandler.paperUrl + "/recommendPaper?userId=" + SaveUser.userInfoEntity.getId()+"&pageNum="+SaveUser.pageNum, json);
    }

    @Override
    protected void onPostExecute(String res) {
        messageResponse.onReceived(res);
    }
}