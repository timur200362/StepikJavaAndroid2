package com.example.dogs.responseClass;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailersList {
    @SerializedName("trailers")
    private List<Trailer> trailerList;

    public List<Trailer> getTrailer() {
        return trailerList;
    }

    public TrailersList(List<Trailer> trailerList) {

        this.trailerList = trailerList;
    }
    @Override
    public String toString() {
        return "TrailersList{" +
                "trailerList=" + trailerList +
                '}';
    }
}
