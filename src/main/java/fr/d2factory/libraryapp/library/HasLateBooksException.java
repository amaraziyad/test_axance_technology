package fr.d2factory.libraryapp.library;

/**
 * This exception is thrown when a member who owns late books tries to borrow another book
 */
public class HasLateBooksException extends RuntimeException {

    /*
        Exception declenchée lorsque l'adherant est en retard pour la remise du livre
     */
    public HasLateBooksException() {
        System.out.println("The member has a late book !!!");
    }
}
