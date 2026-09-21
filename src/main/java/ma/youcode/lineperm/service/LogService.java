package ma.youcode.lineperm.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import ma.youcode.lineperm.model.AccessLog;
import ma.youcode.lineperm.model.Users;

public class LogService {
    Path link = Path.of("src/main/resources/access.log");
    Scanner scanner = new Scanner(System.in);

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

    public Map<String, Long> userActions() {
        return logs.stream()
                .collect(
                    Collectors.groupingBy(
                        AccessLog::getUtilisateur,
                        Collectors.counting()
                    )
                );
    }

    public List<Map.Entry<String, Long>> topFiles(){
        Map<String, Long> files = logs.stream()
                    .collect(Collectors.groupingBy(AccessLog::getFichier,Collectors.counting()));

                    return files.entrySet().stream()
                                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                                .limit(3).toList();

    }

    public Map<String, Long> aceesRefuserUser() {
        String username = scanner.nextLine();
        return logs.stream()
                .filter(log -> log.getResultat().equals("REFUSE"))
                .filter(log -> log.getUtilisateur().equals(username))
                .collect(
                    Collectors.groupingBy(
                        AccessLog::getUtilisateur,
                        Collectors.counting()
                    )
                );
    }

    public String utilisateurPlusActif() {
        Map<String, Long> actions = logs.stream()
                .collect(
                    Collectors.groupingBy(
                        AccessLog::getUtilisateur,
                        Collectors.counting()
                    )
                );

        return actions.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()).get().getKey();

    }

    public Map<String, Long> actionType(){
        return logs.stream()
                .collect(
                    Collectors.groupingBy(
                        AccessLog::getAction,
                        Collectors.counting()
                    )
                );        
    }
}


