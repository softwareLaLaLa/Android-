package com.example.lalala.http;

import android.os.AsyncTask;

import com.example.lalala.shared_info.SaveUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InitTask extends AsyncTask<Set<String>, Void, String> {
    private RecInit recInit;

    public void setRecInit(RecInit recInit) {
        this.recInit = recInit;
    }


    @Override
    protected String doInBackground(Set<String>... lists) {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", SaveUser.userInfoEntity.getId());
        map.put("tags", lists[0]);
        return HttpHandler.doPost(HttpHandler.recommendUrl+"/initUserTagData",gson.toJson(map));
    }

    @Override
    protected void onPostExecute(String res){
        recInit.recInit();
    }

}
