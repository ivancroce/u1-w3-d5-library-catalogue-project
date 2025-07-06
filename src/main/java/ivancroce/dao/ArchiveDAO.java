package ivancroce.dao;

import ivancroce.entities.Book;
import ivancroce.entities.CatalogueItem;
import ivancroce.entities.User;
import ivancroce.exceptions.IsbnNotFoundException;
import ivancroce.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ArchiveDAO {
    private final EntityManager em;
    int yearToFind = 1954;

    public ArchiveDAO(EntityManager em) {
        this.em = em;
    }

    // generic save for any entity
    public void save(Object object) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(object);
            transaction.commit();

            System.out.println("Object saved.");
        } catch (Exception ex) {
            System.err.println("Error while saving: " + ex.getMessage());
        }
    }

    // findUserById
    public User findUserById(long id) {
        User found = em.find(User.class, id);
        if (found == null) throw new NotFoundException(id);
        return found;
    }

    // findByIsbn
    public CatalogueItem findByIsbn(String isbn) {
        CatalogueItem item = em.find(CatalogueItem.class, isbn);
        if (item == null) {
            throw new IsbnNotFoundException("Item with ISBN " + isbn);
        }
        return item;
    }

    // removeItemByIsbn
    public void removeItemByIsbn(String isbn) {
        try {
            EntityTransaction t = em.getTransaction();
            CatalogueItem itemToRemove = this.findByIsbn(isbn);

            t.begin();
            em.remove(itemToRemove);
            t.commit();
            System.out.println("Item '" + itemToRemove.getTitle() + "' deleted.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // findByPublicationYear with NamedQuery
    public List<CatalogueItem> findByPublicationYear(int year) {
        TypedQuery<CatalogueItem> query = em.createNamedQuery("findByYear", CatalogueItem.class);
        query.setParameter("yearValue", year);
        return query.getResultList();
    }

    // findByAuthor with NamedQuery
    public List<Book> findByAuthor(String author) {
        TypedQuery<Book> query = em.createNamedQuery("findByAuthor", Book.class);
        query.setParameter("authorValue", author);
        return query.getResultList();
    }

    // findByTitleContaining with TypedQuery
    public List<CatalogueItem> findByTitleContaining(String titlePart) {
        TypedQuery<CatalogueItem> query = em.createQuery("Select c FROM CatalogueItem c WHERE LOWER(c.title) LIKE LOWER(:title)", CatalogueItem.class);
        query.setParameter("title", "%" + titlePart + "%");
        return query.getResultList();
    }
}


