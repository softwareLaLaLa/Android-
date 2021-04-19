package com.example.lalala.http;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class RegisterTask extends AsyncTask<String, Void, String> {
    private MessageResponse messageResponse;

    public void setMessageResponse(MessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }

    @Override
    protected String doInBackground(String... params) {
        Gson gson = new Gson();
        //UserData userData = new UserData(params[0], params[1]);
        Map<String,String> userData=new HashMap<>();
        userData.put("name",params[0]);
        userData.put("password",params[1]);
        String json = gson.toJson(userData);
        return HttpHandler.doPost(HttpHandler.accountUrl + "/signin", json);
    }

    @Override
    protected void onPostExecute(String result){
        messageResponse.onReceived(result);
    }
}
