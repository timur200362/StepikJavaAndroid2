package com.example.dogs;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dogs.responseClass.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{

    private List<Movie> movies=new ArrayList<>();
    private OnReachEndListener onReachEndListener;

    public void setOnReachEndListener(OnReachEndListener onReachEndListener){
        this.onReachEndListener=onReachEndListener;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();//обновляем список фильмов
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//из макета создаем View
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_item,
                parent,
                false
        );
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie=movies.get(position);
        Glide.with(holder.itemView)
                .load(movie.getPoster().getUrl())
                .into(holder.imageViewPoster);
        double rating=movie.getRating().getKp();
        int backgroundId;
        if (rating>7){
            backgroundId=R.drawable.circle_green;
        } else if (rating>5){
            backgroundId=R.drawable.circle_orange;
        } else {
            backgroundId=R.drawable.circle_red;
        }
        Drawable background = ContextCompat.getDrawable(holder.itemView.getContext(), backgroundId);
        holder.textViewRating.setBackground(background);
        holder.textViewRating.setText(String.valueOf(rating));

        if(position==movies.size()-1 && onReachEndListener!=null){
            onReachEndListener.onReachEnd();
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();//размер списка
    }

    interface OnReachEndListener{
        void onReachEnd();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imageViewPoster;
        private final TextView textViewRating;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster=itemView.findViewById(R.id.imageViewPoster);
            textViewRating=itemView.findViewById(R.id.textViewRating);
        }
    }
}
