package com.example.dogs.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.dogs.responseClass.Movie;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM favourite_movies")
    LiveData<List<Movie>> getAllFavouriteMovies();

    @Query("SELECT * FROM favourite_movies WHERE id = :movieId")
    LiveData<Movie> getFavouriteMovie(int movieId);

    @Insert
    Completable insertMovie(Movie movie);
    @Query("DELETE FROM favourite_movies WHERE id = :movieId")
    Completable removeMovie(int movieId);
}
