package ma.youcode.lineperm.service;

import java.time.*;

import ma.youcode.lineperm.dao.LogDao;
import ma.youcode.lineperm.model.AccessLog;

public class LogService {

    private LogDao logDao = new LogDao();

    public void saveLogs(
            String utilisateur,
            String action,
            String fichier,
            String resultat) {

        AccessLog log = new AccessLog(
                LocalDate.now(),
                LocalTime.now(),
                utilisateur,
                action,
                fichier,
                resultat
        );

        logDao.save(log);
    }

    public long totalActions() {
        return logDao.countTotalActions();
    }

    public long totalRefuse() {
        return logDao.countAccesRefuses();
    }

    public void userDistinct() {
        System.out.println(logDao.findDistinctUsers());
    }

    public void userActions() {
        logDao.actionsByUser();
    }

    public void topFiles() {
        logDao.topFichiers();
    }

    public void aceesRefuserUser(String username) {
        logDao.refusesByUser(username);
    }

    public void utilisateurPlusActif() {
        logDao.userPlusActif();
    }

    public void actionType() {
        logDao.repartitionByAction();
    }
}