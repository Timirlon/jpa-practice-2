package service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Genre;
import model.Movie;


import java.util.Scanner;

public class GenreService {
    private static final Scanner scanner = new Scanner(System.in);

    public static void findAll(EntityManager manager) {
        TypedQuery<Genre> query = manager.createQuery("SELECT g FROM Genre g", Genre.class);
        query.getResultList().forEach(genre -> System.out.println(genre.getId() + ". " + genre.getName()));
    }

    public static void deleteByMovieId(int filmId, EntityManager manager) {

    }

    public static void addByMovieId(int filmId, EntityManager manager) {
        Movie movie = manager.find(Movie.class, filmId);

        System.out.print("Введите id жанра, который вы хотите добавить: ");
        int genreId = Integer.parseInt(scanner.nextLine());

        Genre genre = manager.find(Genre.class, genreId);

    }

    public static void findAllByMovieId(int filmId, EntityManager manager) {
        Movie movie = manager.find(Movie.class, filmId);


        try {
            System.out.println(movie.getTitle());

            manager.createQuery("SELECT g FROM Genre g", Genre.class)
                    .getResultList()
                    .stream()
                    .filter(genre -> genre.getMovies().contains(movie))
                    .forEach(genre -> System.out.println(genre.getId() + " - " + genre.getName()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
