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

    public void catFile(String name){

        Path file = dossier.resolve(name);

        try {
            if (!Files.exists(files)) {
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

    public void nano(String name , String contenue){
        Path file = dossier.resolve(name);
        try {
            if (!Files.exists(files)) {
                System.out.println("Aucun fichier");
                return;
            }

            Files.writeString(file, contenue);
            
        } catch (Exception e) {
            System.out.println("Erreur lister les files");
        }
    }

}