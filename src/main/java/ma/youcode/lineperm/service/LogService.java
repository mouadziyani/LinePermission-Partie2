package ma.youcode.lineperm.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import ma.youcode.lineperm.model.AccessLog;
import ma.youcode.lineperm.model.Users;

public class LogService {
    Path link = Path.of("src/main/resources/access.log");

    private List<AccessLog> logs = new ArrayList<>();
    
    
    
public LogService() {
    loadLogs();
    }

public void saveLogs(
        String utilisateur,
        String action,
        String fichier,
        String resultat) {

    try {

        AccessLog log = new AccessLog(
                LocalDate.now(),
                LocalTime.now(),
                utilisateur,
                action,
                fichier,
                resultat
        );

        String save = log.getDate() + ";" +
                      log.getHeure() + ";" +
                      log.getUtilisateur() + ";" +
                      log.getAction() + ";" +
                      log.getFichier() + ";" +
                      log.getResultat();

        Files.writeString(
                link,
                save + System.lineSeparator(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}
    
    public void loadLogs(){
    
        try {
            
            List<String> lignes = Files.readAllLines(link);
    
            for(String ligne : lignes){
                String[] data = ligne.split(";");
    
                LocalDate date = LocalDate.parse(data[0]);
                LocalTime heur = LocalTime.parse(data[1]); 
                String utilisateur = data[2];
                String action = data[3];
                String fichier = data[4];
                String resultat = data[5];
    
                AccessLog log = new AccessLog(date, heur, utilisateur, action, fichier, resultat);
                logs.add(log);  
            }
    
            
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
    
    public long totalActions(){
        return logs.stream()
                    .count();
    }

    public long totalRefuse(){
        return logs.stream()
                    .filter(log -> log.getResultat().equals("REFUSE"))
                    .count();
    }

    public List<String> userDistinct(){
        return logs.stream()
                    .map(AccessLog::getUtilisateur)
                    .distinct().toList();
    }

    // public Map<String , long> userActions(){
    //     return logs.stream()
    //                 .collect(Collectors.groupingBy(AccessLog::getUtilisateur,Collectors.counting()));
    // }

}


