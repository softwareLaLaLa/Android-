package com.example.lalala.http;

import android.os.AsyncTask;
import android.util.Log;


public class ReTopicTask extends AsyncTask<Void, Void, String> {
    private ResponseRecTags responseRecTags;

    public void setResponseRecTags(ResponseRecTags responseRecTags) {
        this.responseRecTags = responseRecTags;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String topicData = HttpHandler.doGet(HttpHandler.recommendUrl + "/getTopics", "");
        Log.d(topicData, "ReTopicTask");
        return topicData;
    }

    @Override
    protected void onPostExecute(String res) {

        //SaveUser.reTags.addAll(tags);
        responseRecTags.receiveRecTags(res);
    }
}
