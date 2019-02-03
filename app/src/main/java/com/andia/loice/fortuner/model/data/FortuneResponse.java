package com.andia.loice.fortuner.model.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FortuneResponse {

    @SerializedName("fortune")
    private List<String> mFortune;

    public List<String> getmFortune() {
        return mFortune;
    }

    public void setmFortune(List<String> mFortune) {
        this.mFortune = mFortune;
    }

    public FortuneResponse(List<String> mFortune) {
        this.mFortune = mFortune;
    }
}
