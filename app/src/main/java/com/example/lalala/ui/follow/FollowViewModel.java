package com.example.lalala.ui.follow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FollowViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FollowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is follow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}