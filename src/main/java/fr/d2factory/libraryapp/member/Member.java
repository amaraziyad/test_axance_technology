package fr.d2factory.libraryapp.member;

import fr.d2factory.libraryapp.Emprunt;
import fr.d2factory.libraryapp.library.Library;
import fr.d2factory.libraryapp.library.MyLibrary;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;

/**
 * A member is a person who can borrow and return books to a {@link Library}
 * A member can be either a student or a resident
 */
public abstract class Member {

    /*
        Valeur incrémenté à chaque création d'un adhérant et qui sert de son identifiant
     */
    static int ids = 1;
    /**
     * An initial sum of money the member has
     */
    private float wallet;
    private int idMember;
    private String name;


    public Member(String name) {
        this.wallet = 0;
        this.idMember = ids++;
        this.name = name;
    }

    /**
     * The member should pay their books when they are returned to the library
     *
     * @param numberOfDays the number of days they kept the book
     */
    public abstract void payBook(int numberOfDays);
    public abstract boolean isRetard(Library library);

    public float getWallet() {
        return wallet;
    }

    public void setWallet(float wallet) {
        this.wallet = wallet;
    }

    public int getIdMember() {
        return idMember;
    }

    public String getName() {
        return name;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void creditWallet(float credit){
        wallet+= credit;
    }

    public void debitWallet(float debit){
        wallet-=debit;
    }

    public ArrayList<Emprunt> getMemberEmprunts(Library library){
        return library.getMemberEmprunts(idMember);
    }
}
