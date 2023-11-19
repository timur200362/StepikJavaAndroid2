package com.example.dogs;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("movie?token=9S5E3D1-2NVM9QY-MZTDBVR-GBEHFMP&field=rating.kp&search=7-10&sortField=votes.kp&sortTipe=-1&limit=5")
    Single<MovieResponse> loadMovies(@Query("page") int page);
}
