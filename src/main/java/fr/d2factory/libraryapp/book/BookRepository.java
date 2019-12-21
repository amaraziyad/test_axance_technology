package fr.d2factory.libraryapp.book;

import java.time.LocalDate;
import java.util.*;

/**
 * The book repository emulates a database via 2 HashMaps
 */
public class BookRepository {
    private Map<Long, Book> availableBooks = new HashMap<>();
    private Map<Book, LocalDate> borrowedBooks = new HashMap<>();

    public void addBooks(List<Book> books){
        /*
            On parcourt la liste des books et on les ajoute dans le HashMap "availableBooks"
         */
        Iterator<Book> bookIterator = books.iterator();
        while (bookIterator.hasNext()){
            availableBooks.put(bookIterator.next().getIsbn(),bookIterator.next());
        }
    }

    public Book findBook(long isbnCode) {
        //rechercher directement dans le HashMap "availableBooks" par isbn et retourner le livre associé
        return availableBooks.get(isbnCode);
    }

    public void saveBookBorrow(Book book, LocalDate borrowedAt){
        /*
            Retirer le livre en question de "availableBooks" pour qu'il ne soit plus disponible au prochain emprunt jusqu'a ce
            su'il soit rendu
            Et le sauvgarder dans borrowedBooks
         */
        availableBooks.remove(book.getIsbn());
        borrowedBooks.put(book,borrowedAt);
    }

    public void returnBook(Book book){

        /*
            Retirer le livre de la liste des emprunt "borrowedBooks"
            Et le réinserer dans "availableBooks" pour qu'il puisse de nouveau etre emprunté
         */
        availableBooks.put(book.getIsbn(),book);
        borrowedBooks.remove(book);
    }

    public LocalDate findBorrowedBookDate(Book book) {
        //TODO implement the missing feature
        //availableBooks.remove(book.getIsbn());
        /*
            Retourne la date de l'emprunt du livre
         */
        return borrowedBooks.get(book);
    }
}
