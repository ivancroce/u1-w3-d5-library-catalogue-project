package ivancroce;

import ivancroce.dao.ArchiveDAO;
import ivancroce.entities.*;
import ivancroce.enums.Periodicity;
import ivancroce.exceptions.IsbnNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library_catalogue_pu");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        ArchiveDAO dao = new ArchiveDAO(em);
        // --- Users ---
        User user1 = new User("Mario", "Rossi", LocalDate.of(1990, 1, 1), 123L);
        User user2 = new User("Aldo", "Baglio", LocalDate.of(1958, 9, 10), 321L);
        User user3 = new User("Giovanni", "Storti", LocalDate.of(1957, 2, 15), 456L);
        User user4 = new User("Giacomo", "Poretti", LocalDate.of(1958, 5, 3), 654L);
        // dao.save(user1);
        // dao.save(user2);
        // dao.save(user3);
        // dao.save(user4);

        // --- Books and Magazines ---
        Book book1 = new Book("971-0-439-02348-1", "The Lord of the Rings", 1954, 1200, "J.R.R. Tolkien", "Fantasy");
        Book book2 = new Book("972-0-439-02348-2", "The Hitchhiker's Guide to the Galaxy", 1979, 250, "Douglas Adams", "Sci-Fi");
        Book book3 = new Book("973-0-439-02348-3", "Nineteen Eighty-Four", 1949, 328, "George Orwell", "Dystopian");
        Magazine mag1 = new Magazine("974-0-439-02348-4", "Focus", 2023, 100, Periodicity.WEEKLY);
        Magazine mag2 = new Magazine("975-0-439-02348-5", "Java", 2022, 150, Periodicity.MONTHLY);
        Magazine mag3 = new Magazine("976-0-439-02348-6", "AI Magazine", 2020, 200, Periodicity.BIANNUAL);
       /* dao.save(book1);
        dao.save(book2);
        dao.save(book3);
        dao.save(mag1);
        dao.save(mag2);
        dao.save(mag3);*/

        // --- Loans ---
        // here we need the managed user from DB with the findUserById
        User user1FromDb = dao.findUserById(1);
        User user2FromDb = dao.findUserById(2);

        Loan loan1 = new Loan(user1FromDb, book1, LocalDate.now().minusDays(20));      // actualReturnDate is null
        Loan loan2 = new Loan(user2FromDb, book2, LocalDate.now().minusDays(50));      // actualReturnDate is null
        Loan loan3 = new Loan(user1FromDb, mag2, LocalDate.of(2023, 5, 1));   // like that loanDate is 1-5-2023, expectedReturnDate 31-5-2023
        loan3.setActualReturnDate(LocalDate.of(2023, 5, 25));                 // actualReturnDate 25-5-2023
        // dao.save(loan1);
        // dao.save(loan2);
        // dao.save(loan3);

        // test findByIsbn
        try {
            CatalogueItem javaMagFromDb = dao.findByIsbn("975-0-439-02348-5");
            System.out.println(javaMagFromDb);
        } catch (IsbnNotFoundException e) {
            System.out.println("Can't find this ISBN");
        }

        // test removeItemByIsbn
        // dao.removeItemByIsbn("974-0-439-02348-4");

        // test findByPublicationYear
        int yearToFind = 1954;
        try {
            List<CatalogueItem> results = dao.findByPublicationYear(yearToFind);

            if (results.isEmpty()) {
                System.out.println("No item found for the year " + yearToFind);
            } else {
                System.out.println("Found by year: ");
                results.forEach(item -> System.out.println(item));
            }
        } catch (Exception e) {
            System.out.println("Error while finding the year: " + e.getMessage());
        }

        // test findByAuthor
        String authorToFind = "George Orwell";
        try {
            List<Book> foundBook = dao.findByAuthor(authorToFind);

            if (foundBook.isEmpty()) {
                System.out.println("No book found for this author '" + authorToFind + "'");
            } else {
                System.out.println("Found by author: ");
                foundBook.forEach(book -> System.out.println(book));
            }
        } catch (Exception e) {
            System.out.println("Error while finding the author: " + e.getMessage());
        }

        // test findByTitleContaining
        String titlePart = "in";
        try {
            List<CatalogueItem> foundTitle = dao.findByTitleContaining(titlePart);

            if (foundTitle.isEmpty()) {
                System.out.println("No item found containing '" + titlePart + "' in title.");
            } else {
                System.out.println("Found title containing '" + titlePart + "': ");
                foundTitle.forEach(catalogueItem -> System.out.println(catalogueItem));
            }
        } catch (Exception e) {
            System.out.println("Error while finding a title: " + e.getMessage());
        }

        // test findLoanedItemsByUserCardNum
        long cardNumber = 321;
        try {
            List<CatalogueItem> foundLoanedItem = dao.findLoanedItemsByUserCardNum(cardNumber);

            if (foundLoanedItem.isEmpty()) {
                System.out.println("No loaned item found with Card Number '" + cardNumber + "'");
            } else {
                System.out.println("Found loaned item associated with Card Number'" + cardNumber + "':");
                foundLoanedItem.forEach(catalogueItem -> System.out.println(catalogueItem));
            }
        } catch (Exception e) {
            System.out.println("Error while finding a loaned item: " + e.getMessage());
        }


        // test findOverdueLoans
        try {
            List<Loan> overdueLoans = dao.findOverdueLoans();

            if (overdueLoans.isEmpty()) {
                System.out.println("No overdue loans found.");
            } else {
                System.out.println("Overdue loans found: " + overdueLoans);
            }
        } catch (Exception e) {
            System.out.println("Errore while finding overdue loans: " + e.getMessage());
        }

        System.out.println("Grazie e arrivederci! 👋");

        em.close();
        emf.close();
    }
}

