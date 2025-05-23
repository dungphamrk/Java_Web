package com.data.ss11.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class Movie {
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;

    @NotBlank(message = "Director cannot be empty")
    @Size(max = 50, message = "Director must not exceed 50 characters")
    private String director;

    @NotNull(message = "Release date is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @NotBlank(message = "Genre cannot be empty")
    @Size(max = 30, message = "Genre must not exceed 30 characters")
    private String genre;

    private String poster;

    private MultipartFile posterFile;

    public Movie() {}

    public Movie(Long id, String title, String director, Date releaseDate, String genre, String poster) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.poster = poster;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }
    public MultipartFile getPosterFile() { return posterFile; }
    public void setPosterFile(MultipartFile posterFile) { this.posterFile = posterFile; }
}