import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Movie;
import service.GenreService;
import service.MovieService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();

        boolean isLooping = true;


        System.out.print("Введите id фильма: ");
        int movieId = Integer.parseInt(scanner.nextLine());

        Movie movie = entityManager.find(Movie.class, movieId);

        printMenu();
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                GenreService.deleteByMovie(movie, entityManager);
                break;
            case 2:
                GenreService.addByMovie(movie, entityManager);
                break;
            case 3:
                GenreService.findAllByMovie(movie, entityManager);
                break;
            default:
                System.out.println("Некорректная операция");
        }


        entityManager.close();
        scanner.close();
    }

    private static void printMenu() {
        System.out.print("""
                
                1. Удаление жанра
                2. Добавление жанра
                3. Вывод всех жанров
                
                """);
    }
}
