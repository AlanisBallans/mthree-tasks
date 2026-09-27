package org.example.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Dvd {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private String title;
    private LocalDate releaseDate;
    private String rating;
    private String director;
    private String studio;
    private String userNote;

    public Dvd(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getFormattedReleaseDate() {
        return releaseDate.format(DATE_FORMATTER);
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setReleaseDateFromString(String stringRelease) {
        LocalDate release = LocalDate.parse(stringRelease, DATE_FORMATTER);
        this.releaseDate = release;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dvd dvd = (Dvd) o;
        return Objects.equals(title, dvd.title) && Objects.equals(releaseDate, dvd.releaseDate) && Objects.equals(rating, dvd.rating) && Objects.equals(director, dvd.director) && Objects.equals(studio, dvd.studio) && Objects.equals(userNote, dvd.userNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseDate, rating, director, studio, userNote);
    }

    @Override
    public String toString() {
        return "Dvd{title=" + title + ", releaseDate=" + releaseDate + ", rating=" + rating + ", director=" + director + ", studio=" + studio + ", userNote=" + userNote + "}";
    }
}
