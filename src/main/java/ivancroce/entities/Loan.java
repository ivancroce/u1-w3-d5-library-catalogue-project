package ivancroce.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_isbn", nullable = false)
    private CatalogueItem catalogueItem;

    @Column(name = "loan_date", nullable = false)
    private LocalDate loanDate;

    @Column(name = "expected_return_date")
    private LocalDate expectedReturnDate;

    @Column(name = "actual_return_date")
    private LocalDate actualReturnDate;


    public Loan() {
    }

    public Loan(User user, CatalogueItem catalogueItem, LocalDate loanDate) {
        this.user = user;
        this.catalogueItem = catalogueItem;
        this.loanDate = loanDate;
        this.expectedReturnDate = loanDate.plusDays(30);
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CatalogueItem getCatalogueItem() {
        return catalogueItem;
    }

    public void setCatalogueItem(CatalogueItem catalogueItem) {
        this.catalogueItem = catalogueItem;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", user=" + user +
                ", catalogueItem=" + catalogueItem +
                ", loanDate=" + loanDate +
                ", expectedReturnDate=" + expectedReturnDate +
                ", actualReturnDate=" + actualReturnDate +
                '}';
    }
}
