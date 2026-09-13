package ma.youcode.lineperm.access;

import ma.youcode.lineperm.model.FichierProtege;

public class ControleAcces {

    public static boolean canRead(FichierProtege fichier, String username) {

        if (fichier.getOwner().equals(username)) {
            return fichier.getOwnerR();
        }

        return fichier.getOtherR();
    }


    public static boolean canWrite(FichierProtege fichier, String username) {

        if (fichier.getOwner().equals(username)) {
            return fichier.getOwnerW();
        }

        return fichier.getOtherW();
    }


    public static boolean canDelete(FichierProtege fichier, String username) {

        if (fichier.getOwner().equals(username)) {
            return fichier.getOwnerD();
        }

        return fichier.getOtherD();
    }
}