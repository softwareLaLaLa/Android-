package com.example.lalala.http;

import android.os.AsyncTask;

public class SearchTask extends AsyncTask<String, Void, String> {
    private MessageResponse messageResponse;

    public void setMessageResponse(MessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }

    @Override
    protected String doInBackground(String... strings) {
        return HttpHandler.doGet(HttpHandler.url + "/search?sentence=" + strings[0], "");
    }

    @Override
    protected void onPostExecute(String res){
        messageResponse.onReceived(res);
    }
}
