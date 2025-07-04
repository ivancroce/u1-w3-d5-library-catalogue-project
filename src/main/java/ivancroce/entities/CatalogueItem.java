package ivancroce.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "catalogue_items")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CatalogueItem {
    @Id
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(name = "publication_year", nullable = false)
    private int publicationYear;

    @Column(name = "number_of_pages")
    private int numberOfPages;

    @OneToMany(mappedBy = "catalogueItem")
    private List<Loan> loans;

    public CatalogueItem() {
    }

    public CatalogueItem(String isbn, String title, int publicationYear, int numberOfPages) {
        this.isbn = isbn;
        this.title = title;
        this.publicationYear = publicationYear;
        this.numberOfPages = numberOfPages;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return "CatalogueItem{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
