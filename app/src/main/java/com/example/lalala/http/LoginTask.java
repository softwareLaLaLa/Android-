package com.example.lalala.http;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class LoginTask extends AsyncTask<String, Void, String> {
    private MessageResponse messageResponse;

    public void setMessageResponse(MessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }

    @Override
    protected String doInBackground(String... strings) {
        Gson gson = new Gson();
        Map<String,String> userData = new HashMap<>();
        userData.put("name", strings[0]);
        userData.put("password", strings[1]);
        String json = gson.toJson(userData);
        return HttpHandler.doPostWithForm(HttpHandler.accountUrl + "/login", userData);
    }

    @Override
    protected void onPostExecute(String result) {
        messageResponse.onReceived(result);
    }
}
