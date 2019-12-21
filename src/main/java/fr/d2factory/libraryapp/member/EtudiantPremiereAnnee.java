package fr.d2factory.libraryapp.member;

import fr.d2factory.libraryapp.Emprunt;
import fr.d2factory.libraryapp.library.Library;
import fr.d2factory.libraryapp.library.MyLibrary;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

public class EtudiantPremiereAnnee extends Etudiant{

    public EtudiantPremiereAnnee(String name) {
        super(name);
    }

    /*
        Paiment de l'étudiant de première année
     */
    @Override
    public void payBook(int numberOfDays) {
        if (numberOfDays>15){
            float price= (numberOfDays-15)*TARIF;
            debitWallet(price);
        }
        else {
            debitWallet(0);
        }
    }


    /*
           Fonction qui vérifie si l'étudiant de première année et en retard
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
            if(LocalDate.now().isAfter(e.getDateEmprunt().plusDays(15))) return true;
        }
        //System.out.println("Is not retard");
        return false;
    }


}

