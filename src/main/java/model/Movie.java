package model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    @Column(name = "release_year")
    int releaseYear;

    double rating;

    @ManyToMany
    @JoinTable (
            name = "movies_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    final List<Genre> genres = new ArrayList<>();


    public void addGenres(Collection<Genre> genres) {
        this.genres.addAll(genres);
    }
}
