package ivancroce;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library_catalogue_pu");

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
