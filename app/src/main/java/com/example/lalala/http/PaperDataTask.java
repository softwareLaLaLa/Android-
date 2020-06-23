package com.example.lalala.http;

import android.os.AsyncTask;
import android.util.Log;

import com.example.lalala.entity.PaperData;
import com.example.lalala.shared_info.SaveUser;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class PaperDataTask extends AsyncTask<Integer,Void,String> {
    private MessageResponse messageResponse;

    public void setMessageResponse(MessageResponse messageResponse) {
        this.messageResponse = messageResponse;
    }

    //用paper_id请求paper_data
    @Override
    protected String doInBackground(Integer... integers) {
//        Gson gson =new Gson();
//        Map<String, Integer> paperID=new HashMap<>();
//        paperID.put("id",integers[0]);
//        String json = gson.toJson(paperID);

        return HttpHandler.doGet(HttpHandler.url+"/paperData?paper_id="+integers[0],"");
    }

    //把获取到的论文存到当前论文中
    @Override
    protected void onPostExecute(String res){
        Log.d("PaperDataTask", "After request!");
        Gson gson = new Gson();
        SaveUser.curPaper =gson.fromJson(res,PaperData.class);
        messageResponse.onReceived(res);
    }
}
