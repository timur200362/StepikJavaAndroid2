package com.example.dogs;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiService {

    @GET("image/random")//конечная часть
    Single<DogImage> loadDogImage();
}
