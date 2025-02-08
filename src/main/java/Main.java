import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Movie;
import service.MovieService;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();

//        TypedQuery<Movie> query = entityManager.createQuery("SELECT m FROM Movie m WHERE m.id = 2", Movie.class);
//        Movie movie = query.getSingleResult();
//
//        System.out.println(movie.getTitle());
//        movie.getGenres().forEach(genre -> System.out.println(genre.getId() + ". " + genre.getName()));

        MovieService.create(entityManager);

        entityManager.close();
    }
}
