    package ma.youcode.lineperm.model;

    import java.time.LocalDate;
    import java.time.LocalTime;

    public class AccessLog {
        
        private LocalDate date;
        private LocalTime heure;
        private String utilisateur;
        private String action;
        private String fichier;
        private String resultat;

        public AccessLog(LocalDate date, LocalTime heure, String utilisateur, String action, String fichier, String resultat) {
            this.date = date;
            this.heure = heure;
            this.utilisateur = utilisateur;
            this.action = action;
            this.fichier = fichier;
            this.resultat = resultat;
        }
        
        public AccessLog() {
            
        }

        public LocalDate getDate() {
            return date;
        }
        public void setDate(LocalDate date) {
            this.date = date;
        }
        public LocalTime getHeure() {
            return heure;
        }
        public void setHeure(LocalTime heure) {
            this.heure = heure;
        }
        public String getUtilisateur() {
            return utilisateur;
        }
        public void setUtilisateur(String utilisateur) {
            this.utilisateur = utilisateur;
        }
        public String getAction() {
            return action;
        }
        public void setAction(String action) {
            this.action = action;
        }
        public String getFichier() {
            return fichier;
        }
        public void setFichier(String fichier) {
            this.fichier = fichier;
        }
        public String getResultat() {
            return resultat;
        }
        public void setResultat(String resultat) {
            this.resultat = resultat;
        }

    }
