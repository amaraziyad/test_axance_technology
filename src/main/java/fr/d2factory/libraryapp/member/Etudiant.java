package fr.d2factory.libraryapp.member;

import fr.d2factory.libraryapp.Emprunt;
import fr.d2factory.libraryapp.library.Library;
import fr.d2factory.libraryapp.library.MyLibrary;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

public class Etudiant extends Member{

    protected final float TARIF = (float) 0.10;

    public Etudiant(String name) {
        super(name);
    }

    /*
        Paiment de l'adhérant pour le livre emprunté en prenant en compte le nombre de jours
     */
    @Override
    public void payBook(int numberOfDays) {
        float price= numberOfDays*TARIF;
        debitWallet(price);
    }

    /*
        Fonction qui vérifie si l'étudiant et en retard
     */
    @Override
    public boolean isRetard(Library library) {

        ArrayList<Emprunt> emprunts=library.getMemberEmprunts(getIdMember());
        //System.out.println(emprunts);
        if (emprunts == null) {
            //System.out.println("Aucun emprunt  n a ete trouve");
            return false;
        }
        for (Emprunt e:
             emprunts) {
                //System.out.println(e.getDateEmprunt());
                if(LocalDate.now().isAfter(e.getDateEmprunt().plusDays(30))) return true;
        }
        //System.out.println("Is not retard");
        return false;
    }


}
