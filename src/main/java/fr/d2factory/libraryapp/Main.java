package fr.d2factory.libraryapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.library.Library;
import fr.d2factory.libraryapp.library.MyLibrary;
import fr.d2factory.libraryapp.member.Etudiant;
import fr.d2factory.libraryapp.member.Member;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

   public static void main(String[] args) throws IOException {
        MyLibrary library ;
        BookRepository bookRepository;
        List<Book> books;

        ObjectMapper mapper = new ObjectMapper();
        File booksJson = new File("src/test/resources/books.json");
        books = mapper.readValue(booksJson, new TypeReference<List<Book>>() {
        });

        bookRepository   = new BookRepository();
        bookRepository.addBooks(books);
        library = new MyLibrary(bookRepository);

        Member member = new Etudiant("Ziyad");
        Book book = library.borrowBook(968787565445l,member, LocalDate.of(2019,12,1));
        Book bookdd = library.borrowBook(46578964513l,member, LocalDate.of(2019,12,1));

       System.out.println(member.getMemberEmprunts(library).size());
    }
}
