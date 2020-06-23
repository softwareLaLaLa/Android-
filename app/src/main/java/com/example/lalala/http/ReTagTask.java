package com.example.lalala.http;

import android.os.AsyncTask;
import android.util.Log;

import com.example.lalala.entity.TagSimpleData;
import com.example.lalala.shared_info.SaveUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ReTagTask extends AsyncTask<Void, Void, String> {
    private ResponseRecTags responseRecTags;

    public void setResponseRecTags(ResponseRecTags responseRecTags) {
        this.responseRecTags = responseRecTags;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return HttpHandler.doGet(HttpHandler.url + "/tag-data", "");

    }

    @Override
    protected void onPostExecute(String res) {
        Gson gson = new Gson();
        Log.d("tagRela", res);
        ArrayList<TagSimpleData> tags = gson.fromJson(res, new TypeToken<ArrayList<TagSimpleData>>() {
        }.getType());

        SaveUser.reTags.addAll(tags);
        responseRecTags.receiveRecTags();
    }
}
