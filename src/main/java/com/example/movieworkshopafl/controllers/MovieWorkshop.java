package com.example.movieworkshopafl.controllers;

import com.example.movieworkshopafl.services.DataHandler;
import com.example.movieworkshopafl.services.Movie;
import com.example.movieworkshopafl.services.PopularityComparator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MovieWorkshop {
    DataHandler dataH = new DataHandler();

    @GetMapping("/")
    public String intro(){
        return "Welcome to the movie workshop:" +
                "\nCommands:" +
                "\n1: /getFirst" +
                "\n2: /getRandom" +
                "\n3: /getTenSortByPopularity" +
                "\n4: /HowManyWonAnAward" +
                "\n5: /filter?char?='x'amount='n'" +
                "\n6: /longest?g1='x'g2='y'";
    }

    @GetMapping("/getFirst")
    public String getFirst(){
        return dataH.getFirst();
    }

    @GetMapping("/getRandom")
    public String getRandom(){
        return dataH.getRandom();
    }

    @GetMapping("/getTenSortByPopularity")
    public String getTenSortByPopularity(){
        ArrayList<Movie> list = dataH.getTenSortByPopularity();
        list.sort(new PopularityComparator());
        String result = "";
        for(int i = 0; i < list.size(); i++){
            result += list.get(i) + " | \n\n | ";
        }
        return result;
    }

    @GetMapping("/howManyWonAnAward")
    public String howManyWonAwards(){
        return "A total of " + dataH.howManyWonAwards() + " movies won an award";
    }

    @GetMapping("/filter")
    public String filter(@RequestParam char letter, @RequestParam int amount){
        ArrayList<Movie> list = dataH.filterMovies(letter, amount);
        String result = "";
        for(Movie movie : list){
            result += movie + " | ";
        }
        return result;
    }

    @GetMapping("/longest")
    public String longest(@RequestParam String g1, @RequestParam String g2){
        return dataH.compareGenres(g1, g2);
    }

}
