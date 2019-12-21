package fr.d2factory.libraryapp.member;

import fr.d2factory.libraryapp.Emprunt;
import fr.d2factory.libraryapp.library.Library;
import fr.d2factory.libraryapp.library.MyLibrary;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

public class Riverain extends Member {

    private final float TARIF_RIVERAIN = (float) 0.10;
    private final float TARIF_RIVERAIN_MAJORE = (float) 0.20;

    public Riverain(String name) {
        super(name);
    }

    /*
        Fonction de paiment du riveraint
     */
    @Override
    public void payBook(int numberOfDays) {
        if(numberOfDays<0) throw new IllegalArgumentException();
        if (numberOfDays>60){
            float price = 60*TARIF_RIVERAIN + (numberOfDays-60)*TARIF_RIVERAIN_MAJORE;
            debitWallet(price);
        }else {
            float price = numberOfDays*TARIF_RIVERAIN;
            debitWallet(price);
        }
    }

    /*
        Fonction qui vérifie si le riverain est en retard
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
            if(LocalDate.now().isAfter(e.getDateEmprunt().plusDays(60))) return true;
        }
        //System.out.println("Is not retard");
        return false;
    }


}
