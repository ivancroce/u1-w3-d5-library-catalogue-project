package ivancroce.dao;

import ivancroce.entities.CatalogueItem;
import ivancroce.entities.User;
import ivancroce.exceptions.IsbnNotFoundException;
import ivancroce.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ArchiveDAO {
    private final EntityManager em;

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
}


