package com.example.lalala.http;

import android.os.AsyncTask;

import com.example.lalala.shared_info.SaveUser;
import com.google.gson.Gson;

public class GetUserDislikeTask extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
        return HttpHandler.doGet(HttpHandler.recommendUrl + "/getUserHistory?userId="+ SaveUser.userInfoEntity.getId(), "");
    }

    @Override
    protected void onPostExecute(String res) {
        Gson gson = new Gson();
        SaveUser.browsePaperData = gson.fromJson(res, SaveUser.browsePaperData.getClass());
    }
}
