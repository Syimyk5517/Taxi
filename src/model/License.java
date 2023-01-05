package model;

import java.time.LocalDate;

public class License {
    private  Long id;
    private LocalDate dateOfIssue;
    private  LocalDate expirationDAte;

    public License(Long id, LocalDate dateOfIssue, LocalDate expirationDAte) {
        this.id = id;
        this.dateOfIssue = dateOfIssue;
        this.expirationDAte = expirationDAte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public LocalDate getExpirationDAte() {
        return expirationDAte;
    }

    public void setExpirationDAte(LocalDate expirationDAte) {
        this.expirationDAte = expirationDAte;
    }

    @Override
    public String toString() {
        return "License{" +
                "id=" + id +
                ", dateOfIssue=" + dateOfIssue +
                ", expirationDAte=" + expirationDAte +
                '}';
    }
}
