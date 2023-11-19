package com.example.dogs;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiService {

    @GET("movie?token=9S5E3D1-2NVM9QY-MZTDBVR-GBEHFMP&field=rating.kp&search=7-10&sortField=votes.kp&sortTipe=-1&page=2&limit=5")
    Single<MovieResponse> loadMovies();
}
