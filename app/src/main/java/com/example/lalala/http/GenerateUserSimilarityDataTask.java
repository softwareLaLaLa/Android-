package com.example.lalala.http;

import android.os.AsyncTask;
import android.util.Log;

import com.example.lalala.shared_info.SaveUser;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GenerateUserSimilarityDataTask extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
        return HttpHandler.doGet(HttpHandler.recommendUrl + "/generateSimilarityData?userId=" + SaveUser.userInfoEntity.getId(), "");
    }

    @Override
    protected void onPostExecute(String res) {
    }
}
