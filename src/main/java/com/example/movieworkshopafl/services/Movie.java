package com.example.movieworkshopafl.services;

public class Movie {
    private int year;
    private int length;
    private String title;
    private String subject;
    private int popularity;
    private boolean awards;

    public Movie(int year, int length, String title, String subject, int popularity, boolean awards){
        this.year=year;
        this.length=length;
        this.title=title;
        this.subject=subject;
        this.popularity=popularity;
        this.awards=awards;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public int getPopularity() {
        return popularity;
    }

    public int getLength() {
        return length;
    }

    public boolean isAwards() {
        return awards;
    }

    public int compareTo(Movie o2) {
        return o2.popularity;
    }

    public String toString(){
        return "Movie: " + title + ", released year " + year +
                "\nLength: " + length + ", Subject: " + subject + ", Popularity: " + popularity + ", Awards: " + awards;
    }
}
