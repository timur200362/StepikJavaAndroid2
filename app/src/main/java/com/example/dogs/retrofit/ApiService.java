package com.example.dogs.retrofit;

import com.example.dogs.responseClass.MovieResponse;
import com.example.dogs.responseClass.ReviewResponse;
import com.example.dogs.responseClass.TrailerResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie?token=9S5E3D1-2NVM9QY-MZTDBVR-GBEHFMP&field=rating.kp&search=7-10&sortField=votes.kp&sortTipe=-1&limit=30")
    Single<MovieResponse> loadMovies(@Query("page") int page);

    @GET("movie?token=9S5E3D1-2NVM9QY-MZTDBVR-GBEHFMP&field=id")
    Single<TrailerResponse> loadTrailers(@Query("search") int id);

    @GET("review?token=9S5E3D1-2NVM9QY-MZTDBVR-GBEHFMP&field=movieId")
    Single<ReviewResponse> loadReviews(@Query("search") int id);
}