package ma.youcode.lineperm.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import ma.youcode.lineperm.model.FichierProtege;

public class FileService{

    Path dossier = Path.of("createdFile");  
    private List<FichierProtege> fichiers = new ArrayList<>();

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

                System.out.println(filePermWriter);

                fichiers.add(newFile);

                return true ;

        } catch (Exception e) {

            System.out.println("error");
            return false;
        }
    }

    public void lsFichier(){
        if(fichiers.isEmpty()){
            System.out.println("aucun fichier");
        }

        for(FichierProtege f : fichiers){
            System.out.println(f.getNameOfFile());
        }

    }
}