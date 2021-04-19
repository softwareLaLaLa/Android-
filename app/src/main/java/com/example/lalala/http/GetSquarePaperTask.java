package com.example.lalala.http;

import android.os.AsyncTask;

import com.example.lalala.shared_info.SaveUser;
import com.example.lalala.ui.browse_fragment.SquareAdapter;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class GetSquarePaperTask extends AsyncTask<Void, Void, String> {

    private MessageResponse messageResponse;

    public void setMessageResponse(MessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }

    @Override
    protected String doInBackground(Void... voids) {
        ++SaveUser.squarePaperPageNum;
        return HttpHandler.doGet(HttpHandler.paperUrl + "/squarePaper?userId="+SaveUser.userInfoEntity.getId()+"&pageNum="+SaveUser.squarePaperPageNum, "");
    }

    @Override
    protected void onPostExecute(String res) {
        messageResponse.onReceived(res);
    }
}
