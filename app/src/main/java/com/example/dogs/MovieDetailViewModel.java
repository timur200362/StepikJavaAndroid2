package com.example.dogs;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dogs.db.MovieDao;
import com.example.dogs.db.MovieDatabase;
import com.example.dogs.responseClass.Movie;
import com.example.dogs.responseClass.Review;
import com.example.dogs.responseClass.ReviewResponse;
import com.example.dogs.responseClass.Trailer;
import com.example.dogs.responseClass.TrailerResponse;
import com.example.dogs.retrofit.ApiFactory;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailViewModel extends AndroidViewModel {
    private static final String TAG="MovieDetailViewModel";

    private final CompositeDisposable compositeDisposable=new CompositeDisposable();
    private MutableLiveData<List<Trailer>> trailers=new MutableLiveData<>();
    private MutableLiveData<List<Review>> reviews=new MutableLiveData<>();

    private final MovieDao movieDao;

    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        movieDao= MovieDatabase.getInstance(application).movieDao();
    }
    public LiveData<Movie> getFavouriteMovie(int movieId){
        return movieDao.getFavouriteMovie(movieId);
    }

    public LiveData<List<Trailer>> getTrailers() {
        return trailers;
    }

    public LiveData<List<Review>> getReviews() {
        return reviews;
    }

    public void loadReviews(int id){
        Disposable disposable= ApiFactory.apiService.loadReviews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ReviewResponse>() {
                    @Override
                    public void accept(ReviewResponse reviewResponse) throws Throwable {
                        reviews.setValue(reviewResponse.getReviews());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void insertMovie(Movie movie){
        Disposable disposable=movieDao.insertMovie(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        compositeDisposable.add(disposable);
    }
    public void removeMovie(int movieId){
        Disposable disposable=movieDao.removeMovie(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    public void loadTrailers(int id){
        Disposable disposable=ApiFactory.apiService.loadTrailers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<TrailerResponse, List<Trailer>>() {
                    @Override
                    public List<Trailer> apply(TrailerResponse trailerResponse) throws Throwable {
                        return trailerResponse.getTrailersList().getTrailer();
                    }
                })
                .subscribe(new Consumer<List<Trailer>>() {
                    @Override
                    public void accept(List<Trailer> trailersList) throws Throwable {
                        trailers.setValue(trailersList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
