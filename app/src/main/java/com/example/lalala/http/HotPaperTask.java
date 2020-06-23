package com.example.lalala.http;

import android.os.AsyncTask;

import com.example.lalala.entity.PaperSimpleData;
import com.example.lalala.shared_info.SaveUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class HotPaperTask extends AsyncTask<Void,Void,String> {
    RecHotPapers recHotPapers;

    public void setRecHotPapers(RecHotPapers recHotPapers) {
        this.recHotPapers = recHotPapers;
    }

    @Override
    protected String doInBackground(Void... voids) {

        return HttpHandler.doGet(HttpHandler.url+"/hot-paper","");
    }

    @Override
    protected void onPostExecute(String res){
        Gson gson = new Gson();
        ArrayList<PaperSimpleData> hotPapers=gson.fromJson(res, new TypeToken<ArrayList<PaperSimpleData>>(){}.getType());

        SaveUser.hotPapers.addAll(hotPapers);
        recHotPapers.recHotPapers();
    }
}
