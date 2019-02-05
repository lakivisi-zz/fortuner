package com.andia.loice.fortuner.model.db.api;

import com.andia.loice.fortuner.model.data.FortuneResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("fortune")
    Observable<FortuneResponse> getFortune();
}
