package com.dvdlibrary.dto;
import java.util.AbstractMap.SimpleEntry;

public class DVD {
    private String title;
    private String director;
    private String releaseDate;
    private String studio;
    private String MPAArating;
    private SimpleEntry<Integer,String> userRating;

    
    public DVD(String title, String director, String releaseDate, String studio, String MPAArating,
            SimpleEntry<Integer, String> userRating) {
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.studio = studio;
        this.MPAArating = MPAArating;
        this.userRating = userRating;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    public void setStudio(String studio) {
        this.studio = studio;
    }
    public void setMPAArating(String MPAArating) {
        this.MPAArating = MPAArating;
    }
    public void setUserRating(Integer rating, String note) {
        this.userRating = new SimpleEntry<Integer,String>(rating, note);
    }
    public void setUserRating(Integer rating) {
        this.userRating = new SimpleEntry<Integer,String>(rating, "");
    }

    public String getTitle() {
        return this.title;
    }
    public String getDirector() {
        return this.director;
    }
    public String getReleaseDate() {
        return this.releaseDate;
    }
    public String getStudio() {
        return this.studio;
    }
    public String getMPAArating() {
        return this.MPAArating;
    }
    public SimpleEntry<Integer, String> getUserRating() {
        return this.userRating;
    }
    
    
}
