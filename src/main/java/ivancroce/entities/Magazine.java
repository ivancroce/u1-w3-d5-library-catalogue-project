package ivancroce.entities;

import ivancroce.enums.Periodicity;
import jakarta.persistence.*;

@Entity
@Table(name = "magazines")
public class Magazine extends CatalogueItem {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Periodicity periodicity;


    public Magazine() {
    }

    public Magazine(String isbn, String title, int publicationYear, int numberOfPages, Periodicity periodicity) {
        super(isbn, title, publicationYear, numberOfPages);
        this.periodicity = periodicity;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "periodicity=" + periodicity +
                '}';
    }
}
