package fr.d2factory.libraryapp;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.member.Member;

import java.time.LocalDate;
import java.util.Date;

public class Emprunt {

    private int idMember;
    private Long isbn;
    private LocalDate dateEmprunt;

    public Emprunt(int idMember, Long isbn, LocalDate dateEmprunt) {
        this.idMember = idMember;
        this.isbn = isbn;
        this.dateEmprunt = dateEmprunt;
    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public Long getBook() {
        return isbn;
    }

    public void setBook(Long isbn) {
        this.isbn = isbn;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }
}
