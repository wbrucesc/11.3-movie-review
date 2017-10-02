package com.will.moviereview.models;

import javax.persistence.*;


@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    private String author;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    private String notes;
    private int rating;


    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", author=" + author +
                ", notes='" + notes + '\'' +
                ", rating=" + rating +
                ", movie=" + movie +
                '}';
    }
}
