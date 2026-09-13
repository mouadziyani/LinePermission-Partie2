package ma.youcode.lineperm.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import ma.youcode.lineperm.model.FichierProtege;

public class FileService{

    Path dossier = Path.of("createdFile");  
    private List<FichierProtege> fichiers = new ArrayList<>();
    Path files = Path.of("src/main/resources/files.txt");

    public FileService(){
        loadFiles();
    }

   public boolean touch(String name, String owner) {

       
       try {
           
           if (name == null || name.isEmpty()) {
               return false;
           }
    
           for (FichierProtege fichier : fichiers) {
               if (fichier.getNameOfFile().equals(name)) {
                   return false;
               }
           }
            if (!Files.exists(dossier)) {
                Files.createDirectory(dossier);
            }

            
                Path file = dossier.resolve(name);

                Files.createFile(file);

                FichierProtege newFile = new FichierProtege(name, owner);

                String filePermWriter = "rwd|--- " + owner + " " + name;

                Files.writeString(files, filePermWriter+System.lineSeparator(),StandardOpenOption.APPEND);

                fichiers.add(newFile);
                return true ;

        } catch (Exception e) {

            System.out.println("error");
            return false;
        }
    }

    public void lsFichier() {
        try {
            if (!Files.exists(files)) {
                System.out.println("Aucun fichier");
                return;
            }

            List<String> lignes = Files .readAllLines(files);

            if (lignes.isEmpty()) {
                System.out.println("Aucun fichier");
                return;
            }

            for (String ligne : lignes) {
                System.out.println(ligne);
            }

        } catch (Exception e) {
            System.out.println("Erreur lecture files");
        }
    }

    public void catFile(String name , String username){

        FichierProtege filePerm = null ;

        for (FichierProtege file : fichiers) {
            if (file.getNameOfFile().equals(name)) {
                filePerm = file;
                break;
            }
        }

        if (filePerm == null) {
            System.out.println("File not found");
            return;
        }
        if (!filePerm.getOwner().equals(username) && !filePerm.getOtherR()) {
            System.out.println("Permission denied.");
            return;
        }

        Path file = dossier.resolve(name);

        try {
            if (!Files.exists(file)) {
                System.out.println("Aucun fichier");
                return;
            }

            String content = Files.readString(file);

            if (content.isEmpty()) {
                System.out.println("file is vide");
                return ;
            }

            System.out.println(content);

        } catch (Exception e) {
            System.out.println("Erreur lister les files");
        }

    }

    public void nano(String name , String contenue , String username){

        FichierProtege filePerm = null;

        for (FichierProtege file : fichiers) {
            if (file.getNameOfFile().equals(name)) {
                filePerm = file;
                break;
            }
        }

        if (filePerm == null) {
            System.out.println("File not found");
            return;
        }

        if (!filePerm.getOwner().equals(username) && !filePerm.getOtherW()) {
            System.out.println("Permission denied.");
            return;
        }

        Path file = dossier.resolve(name);
        try {
            if (!Files.exists(file)) {
                System.out.println("Aucun fichier");
                return;
            }

            Files.writeString(file, contenue);
            
        } catch (Exception e) {
            System.out.println("Erreur lister les files");
        }
    }

    public boolean chmod(String username, String permission, String fileName){

        FichierProtege filePerm = null;

        for(FichierProtege file : fichiers){

            if(file.getNameOfFile().equals(fileName)){
                filePerm = file;
                break;
            }
        }

        if(filePerm == null){
            System.out.println("File not found");
            return false;
        }

        if(!filePerm.getOwner().equals(username)){
            System.out.println("Permission denied.");
            return false;
        }


        if(permission.equals("r")) {

        filePerm.setPermission('r');

        } else if(permission.equals("w")) {

            filePerm.setPermission('w');

        } else if(permission.equals("d")) {

            filePerm.setPermission('d');

        } else if(permission.equals("-r")) {

            filePerm.removePermission('r');

        } else if(permission.equals("-w")) {

            filePerm.removePermission('w');

        } else if(permission.equals("-d")) {

            filePerm.removePermission('d');

        } else {

            System.out.println("Permission invalide");
            return false;
        }

        saveFiles();

        return true;
    }

    public void loadFiles(){

        if (!Files.exists(files)) {
            return;
        }

        try {

            List<String> lignes = Files.readAllLines(files);

            for (String ligne : lignes) {

                if (ligne.isEmpty()) {
                    continue;
                }

                String[] data = ligne.split(" ");

                if (data.length == 3) {

                    String permission = data[0];
                    String owner = data[1];
                    String name = data[2];

                    FichierProtege fichier = new FichierProtege(name, owner);

                    if (permission.charAt(4) == 'r') {
                        fichier.setotherR(true);
                    }

                    if (permission.charAt(5) == 'w') {
                        fichier.setotherW(true);
                    }

                    if (permission.charAt(6) == 'd') {
                        fichier.setotherD(true);
                    }

                    fichiers.add(fichier);
                }
            }

        } catch (Exception e) {
            System.out.println("Erreur chargement fichiers");
        }
    }

    public void saveFiles() {

        try {

            StringBuilder content = new StringBuilder();

            for (FichierProtege fichier : fichiers) {

                content.append(fichier.getPermission())
                    .append(" ")
                    .append(fichier.getOwner())
                    .append(" ")
                    .append(fichier.getNameOfFile())
                    .append(System.lineSeparator());
            }

            Files.writeString(files, content.toString());

        } catch (Exception e) {
            System.out.println("Erreur sauvegarde fichiers");
        }
    }
    
}