package service;

import jakarta.persistence.EntityManager;
import model.Genre;
import model.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MovieService {
    private static final Scanner scanner = new Scanner(System.in);

    public static void create(EntityManager manager) {

        System.out.print("Введите id фильма: "); //Временно (убрать)
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите название фильма: ");
        String title = scanner.nextLine();

        System.out.print("Введите год выпуска: ");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите рейтинг: ");
        double rating = Double.parseDouble(scanner.nextLine());


        GenreService.findAll(manager);

        System.out.println("Выберите жанры: ");
        String strGenres = scanner.nextLine();
        List<Integer> genresId = Arrays.stream(strGenres.split(", "))
                .map(Integer::parseInt)
                .toList();

        List<Genre> genreList = new ArrayList<>();

        try {
            genreList = manager.createQuery("SELECT g FROM Genre g WHERE g.id IN (:id)", Genre.class)
                    .setParameter("id", genresId)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Указаны некорректные жанры");
        }

        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setReleaseYear(year);
        movie.setRating(rating);
        movie.addGenres(genreList);


        try {
            manager.getTransaction().begin();
            manager.persist(movie);
            manager.getTransaction().commit();

            System.out.println("Фильм создан.");

        } catch (Exception e) {
            System.out.println("Ошибка при создании фильма.");

            manager.getTransaction().rollback();
        }
    }
}
