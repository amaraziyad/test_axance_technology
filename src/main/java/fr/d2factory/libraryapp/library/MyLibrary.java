package fr.d2factory.libraryapp.library;

import fr.d2factory.libraryapp.Emprunt;
import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.member.Member;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MyLibrary implements Library {

    /*
        Instance de bookRepository pour obtenir la liste des livres
     */
    BookRepository bookRepository;
    /*
        Listes des emprunts pour sauvgarder les emprunt effectués par les adhérants
     */
    ArrayList<Emprunt> emprunts ;
    /*
        HashMap pour associer à chaque adhérant sa liste des emprunts
     */
    private HashMap<Integer,ArrayList<Emprunt>> empruntsList;

    public ArrayList<Emprunt> getEmprunts() {
        return emprunts;
    }
    public MyLibrary(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.empruntsList = new HashMap<>();
    }
    /*
        A chaque emprunt on vérifie les conditions suivante!
        - Verifier si le le livre est toujours disponible
        - Si c'est le cas verifier si l'adhérant n'est pas en retard
        - Si les deux conditions sont vérifiées, attribuer le livre à l'adhérant, enregistrer le livre dans la liste de ses emprunt
          et le retirer de la liste des livres disponibles
        - Si l'adhérant est en retard, déclancher l'exception HasLateBookException
     */
    @Override
    public Book borrowBook(long isbnCode, Member member, LocalDate borrowedAt) throws HasLateBooksException {
        Book book = null;
        if (bookRepository.findBook(isbnCode) != null){
            if ( ! member.isRetard(this)){
                //System.out.println("Louer un livre ");
                ArrayList<Emprunt> userEmprunt= empruntsList.getOrDefault(member.getIdMember(),new ArrayList<Emprunt>()) ;
                //System.out.println(userEmprunt);
                userEmprunt.add(new Emprunt(member.getIdMember(),isbnCode,borrowedAt));
                empruntsList.put(member.getIdMember(),userEmprunt);
                book = bookRepository.findBook(isbnCode);
                bookRepository.saveBookBorrow(book,borrowedAt);
            }
            else throw new HasLateBooksException();
        }else {
            System.out.println("livre doesnt exist");
        }
        return book;
    }


    /*
        Actualiser la liste des emprunts de l'adhérant en supprimant l'emprunt contenant le livre en question et procéder au
        paiment
     */
    @Override
    public void returnBook(Book book, Member member) {
        boolean bool = true;
        ArrayList<Emprunt> userEmprunt= empruntsList.getOrDefault(member.getIdMember(),new ArrayList<Emprunt>()) ;
        Iterator<Emprunt> empruntIterator = userEmprunt.iterator();
        while (empruntIterator.hasNext() && bool){
            Emprunt emprunt = empruntIterator.next();
            if ((emprunt.getBook()).equals(book.getIsbn())){
                bookRepository.returnBook(book);
                LocalDate today = LocalDate.now();
                member.payBook((int) ChronoUnit.DAYS.between(emprunt.getDateEmprunt(), today));
                userEmprunt.remove(emprunt);
                //empruntsList.remove(member.getIdMember());
                empruntsList.put(member.getIdMember(),userEmprunt);
                bool =false;
            }
        }
    }

    /*
        Fonction qui retourne la liste des emprunt de l'adhérant
     */
    public ArrayList<Emprunt> getMemberEmprunts(int memberId){
        return empruntsList.get(memberId);
    }
}
