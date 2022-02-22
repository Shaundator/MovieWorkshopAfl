package com.example.movieworkshopafl.services;

import java.util.Comparator;

public class PopularityComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie o1, Movie o2) {
        return Integer.compare(o1.getPopularity(), o2.getPopularity());
    }
}
