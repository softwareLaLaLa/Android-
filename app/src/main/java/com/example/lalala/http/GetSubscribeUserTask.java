package com.example.lalala.http;

import android.os.AsyncTask;

public class GetSubscribeUserTask extends AsyncTask<Void, Void, String> {
    private MessageResponse messageResponse;

    public void setMessageResponse(MessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return HttpHandler.doGet(HttpHandler.accountUrl + "/userSubscribe", "");
    }

    @Override
    protected void onPostExecute(String res) {
        messageResponse.onReceived(res);
    }
}
