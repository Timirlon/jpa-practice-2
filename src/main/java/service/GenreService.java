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

    public static void deleteByMovie(Movie movie, EntityManager manager) {
        System.out.print("Введите id жанра, который вы хотите удалить: ");
        int genreId = Integer.parseInt(scanner.nextLine());

        Genre genre = manager.find(Genre.class, genreId);
        movie.getGenres().remove(genre);


        try {
            manager.getTransaction().begin();
            manager.merge(genre);
            manager.getTransaction().commit();

            System.out.println("Жанр удален.");
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public static void addByMovie(Movie movie, EntityManager manager) {
        System.out.print("Введите id жанра, который вы хотите добавить: ");
        int genreId = Integer.parseInt(scanner.nextLine());

        Genre genre = manager.find(Genre.class, genreId);
        movie.getGenres().add(genre);

        try {
            manager.getTransaction().begin();
            manager.merge(genre);
            manager.getTransaction().commit();

            System.out.println("Жанр добавлен.");
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    public static void findAllByMovie(Movie movie, EntityManager manager) {
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
