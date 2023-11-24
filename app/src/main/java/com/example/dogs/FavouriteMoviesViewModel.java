package com.example.dogs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dogs.db.MovieDao;
import com.example.dogs.db.MovieDatabase;
import com.example.dogs.responseClass.Movie;

import java.util.List;

public class FavouriteMoviesViewModel extends AndroidViewModel {
    private final MovieDao movieDao;

    public FavouriteMoviesViewModel(@NonNull Application application) {
        super(application);
        movieDao= MovieDatabase.getInstance(application).movieDao();
    }
    public LiveData<List<Movie>> getMovies(){
        return movieDao.getAllFavouriteMovies();
    }
}
