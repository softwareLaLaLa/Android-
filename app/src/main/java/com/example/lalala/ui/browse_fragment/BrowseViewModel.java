package com.example.lalala.ui.browse_fragment;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class BrowseViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public BrowseViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is browse fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
