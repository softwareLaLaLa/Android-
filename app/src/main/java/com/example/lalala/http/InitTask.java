package com.example.lalala.http;

import android.os.AsyncTask;
import android.util.Log;

import com.example.lalala.entity.InitialUserTagData;
import com.example.lalala.shared_info.SaveUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class InitTask extends AsyncTask<Void, Void, String> {
    private RecInit recInit;

    public void setRecInit(RecInit recInit) {
        this.recInit = recInit;
    }
    @Override
    protected String doInBackground(Void... voids) {
        Gson gson = new Gson();
        String json = gson.toJson(SaveUser.initialUserTagData, InitialUserTagData.class);
        return HttpHandler.doPost(HttpHandler.url+"/user-tag",json);
    }

    @Override
    protected void onPostExecute(String res){
        SaveUser.initialUserTagData=null;
        Gson gson = new Gson();
        List<Integer> groupId = gson.fromJson(res, new TypeToken<ArrayList<Integer>>(){}.getType());
        SaveUser.userInfor.setGroupID(groupId);

        recInit.recInit();
        //Log.d("InitTask", "size: "+SaveUser.userInfor.getGroupID().size());
        //Log.d("initTask", "onPostExecute: TaskExecuted!");
    }

}
