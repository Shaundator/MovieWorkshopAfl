package com.example.movieworkshopafl.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DataHandler {
    File file = new File("data/imdb-data.csv");
    private final int movieCount;

    public DataHandler(){
        movieCount = movieCount();
    }

    //Functions
    public String getFirst(){
        try {
            Scanner sc = new Scanner(file);
            String movie = sc.nextLine();
            System.out.println(movie);
            movie = sc.nextLine();
            return String.valueOf(convertMovie(movie));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "didn't work?";
    }
    public String getRandom(){
        try {
            Scanner sc = new Scanner(file);
            Random random = new Random();
            int numberMovie = random.nextInt(movieCount);
            String randomMovie = "Null";
            while(numberMovie>0){
                randomMovie = sc.nextLine();
                numberMovie--;
            }
            return String.valueOf(convertMovie(randomMovie));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "???";
    }
    public ArrayList<Movie> getTenSortByPopularity(){
        ArrayList<Movie> result = new ArrayList<>();
        ArrayList<Integer> used = new ArrayList<>();
        try{
            Random random = new Random();
            for(int i = 0; i < 10; i++){
                Scanner sc = new Scanner(file);
                int number = random.nextInt(movieCount);
                while(used.contains(number)){
                    number = random.nextInt(movieCount);
                }
                String movie = "nuller";
                used.add(number);
                while(number>0){
                    movie=sc.nextLine();
                    number--;
                }
                result.add(convertMovie(movie));
            }
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int howManyWonAwards(){
        try{
            Scanner sc = new Scanner(file);
            int amount = 0;
            sc.nextLine();
            while(sc.hasNextLine()){
                Movie temp = convertMovie(sc.nextLine());
                if(temp.isAwards()){
                    amount++;
                }
            }
            return amount;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public ArrayList<Movie> filterMovies(char x, int n){
        ArrayList<Movie> list = new ArrayList<>();
        try{
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while(sc.hasNextLine()){
                Movie temp = convertMovie(sc.nextLine());
                String[] title = temp.getTitle().split("");
                int count = n;
                for(String s : title){
                    if(s.equalsIgnoreCase(String.valueOf(x))){
                        count--;
                    }
                }
                if(count == 0){
                    list.add(temp);
                }

            }
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("wtf?");
        return null;
    }
    public String compareGenres(String g1, String g2){
        ArrayList<Movie> list1 = new ArrayList<>();
        ArrayList<Movie> list2 = new ArrayList<>();
        try{
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while(sc.hasNextLine()){
                Movie temp = convertMovie(sc.nextLine());
                if(temp.getSubject().equalsIgnoreCase(g1)){
                    list1.add(temp);
                } else if(temp.getSubject().equalsIgnoreCase(g2)){
                    list2.add(temp);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int average1 = getAverage(list1);
        int average2 = getAverage(list2);
        if(average1 > average2){
            return "The genre (" + g1 + ") has the longest average length(" + average1 +"),"+
                    " while the subject (" + g2 + ") has an average length of (" + average2 + ")";
        } else if(average2 > average1){
            return "The genre (" + g2 + ") has the longest average length(" + average2 +"),"+
                    " while the subject (" + g1 + ") has an average length of (" + average1 + ")";
        } else {
            return "they are same? average 1 = " + average1 + ", average 2 = " + average2;
        }
    }



    //Tools
    private int movieCount(){
        try {
            Scanner sc = new Scanner(file);
            int count = 0;
            while(sc.hasNextLine()){
                sc.nextLine();
                count++;
            }
            return count;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }
    private Movie convertMovie(String movie){
        String[] movieData = movie.split(";");
        int year = Integer.parseInt(movieData[0]);
        int length = Integer.parseInt(movieData[1]);
        String title = movieData[2];
        String subject = movieData[3];
        int popularity = Integer.parseInt(movieData[4]);
        boolean awards = yesOrNo(movieData[5]);

        return new Movie(year, length, title, subject, popularity, awards);
    }
    private boolean yesOrNo(String input){
        if(input.equalsIgnoreCase("yes")){
            return true;
        } else if (input.equalsIgnoreCase("no")){
            return false;
        } else {
            System.out.println("wtf?");
            return false;
        }
    }
    private int getAverage(ArrayList<Movie> list){
        int amount = 0;
        for(Movie movie : list){
            amount += movie.getLength();
        }
        return amount/list.size();
    }
}
