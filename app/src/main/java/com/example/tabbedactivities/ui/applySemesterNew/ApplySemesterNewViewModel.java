package com.example.tabbedactivities.ui.applySemesterNew;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ApplySemesterNewViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ApplySemesterNewViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Form Id: 23456789");
    }

    public LiveData<String> getText() {
        return mText;
    }
}