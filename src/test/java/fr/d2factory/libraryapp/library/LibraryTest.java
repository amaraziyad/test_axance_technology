package fr.d2factory.libraryapp.library;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import fr.d2factory.libraryapp.member.Etudiant;
import fr.d2factory.libraryapp.member.EtudiantPremiereAnnee;
import fr.d2factory.libraryapp.member.Member;
import fr.d2factory.libraryapp.member.Riverain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Do not forget to consult the README.md :)
 */
public class LibraryTest {
    private Library library ;
    private BookRepository bookRepository;
    private static List<Book> books;


    @BeforeEach
    void setup() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        File booksJson = new File("src/test/resources/books.json");
        books = mapper.readValue(booksJson, new TypeReference<List<Book>>() {
        });
        bookRepository   = new BookRepository();
        bookRepository.addBooks(books);
        library = new MyLibrary(bookRepository);
    }

    @Test
    void member_can_borrow_a_book_if_book_is_available(){
        Member member=new Etudiant("Ziyad");
        Book borrowedBook = library.borrowBook(968787565445l,member, LocalDate.now());
        System.out.println(borrowedBook);
        Assertions.assertNotNull(member.getMemberEmprunts(library).stream().filter(e-> e.getBook()==borrowedBook.getIsbn()));
    }

    @Test
    void borrowed_book_is_no_longer_available(){
        Member member=new Etudiant("Ziyad");
        Book borrowedBook = library.borrowBook(3326456467846l,member, LocalDate.of(2019,11,30));
        Book borrowedBook1 = library.borrowBook(3326456467846l,member, LocalDate.now());
        Assertions.assertNull(borrowedBook1);
    }

    @Test
    void residents_are_taxed_10cents_for_each_day_they_keep_a_book(){
        Riverain riverain = new Riverain("Ziyad");
        riverain.creditWallet(20);
        float wallet_before_payment = riverain.getWallet();
        riverain.payBook(60);
        float wallet_after_payment = riverain.getWallet();
        Assertions.assertEquals(6,wallet_before_payment-wallet_after_payment);
    }

    @Test
    void students_pay_10_cents_the_first_30days(){
        Etudiant etudiant = new Etudiant("Ziyad");
        etudiant.creditWallet(20);
        float wallet_before_payment = etudiant.getWallet();
        etudiant.payBook(30);
        float wallet_after_payment = etudiant.getWallet();
        Assertions.assertEquals(3,wallet_before_payment-wallet_after_payment);
    }

    @Test
    void students_in_1st_year_are_not_taxed_for_the_first_15days(){
        EtudiantPremiereAnnee etudiantPremiereAnnee = new EtudiantPremiereAnnee("Ziayd");
        etudiantPremiereAnnee.creditWallet(25);
        float wallet = etudiantPremiereAnnee.getWallet();
        System.out.println("wallet"+wallet);
        etudiantPremiereAnnee.payBook(10);
        float walleAfterPayment = etudiantPremiereAnnee.getWallet();
        Assertions.assertEquals(wallet,walleAfterPayment);
        //Assertions.fail("Implement me");
    }
    
    @Test
    void residents_pay_20cents_for_each_day_they_keep_a_book_after_the_initial_60days(){
        Riverain riverain = new Riverain("Ziyad");
        riverain.creditWallet(20);
        float wallet_before_payment = riverain.getWallet();
        riverain.payBook(70);
        float wallet_after_payment = riverain.getWallet();
        Assertions.assertEquals(8,wallet_before_payment-wallet_after_payment);
    }

    @Test
    void members_cannot_borrow_book_if_they_have_late_books(){
        Member member = new Etudiant("Ziyad");
        Book book = library.borrowBook(968787565445l,member,LocalDate.of(2019,11,1));
        Assertions.assertThrows(HasLateBooksException.class, ()-> library.borrowBook(46578964513l,member,LocalDate.now()));
    }

    @Test
    void members_is_late(){
        Member member = new Etudiant("Ziyad");
        Book book = library.borrowBook(968787565445l,member,LocalDate.of(2019,11,1));
        Assertions.assertTrue(member.isRetard(library));
    }
}
