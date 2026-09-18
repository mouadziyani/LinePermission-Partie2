    package ma.youcode.lineperm.ui;

    import java.util.Scanner;

import ma.youcode.lineperm.service.LogService;

    public class log {
        public void logMenue(){

            Scanner scanner = new Scanner(System.in);

            LogService logService = new LogService() ;

            int choix ;
            

            do{
                System.out.println("Bienvenue dans LogAnalyzer . Choiser un Nombre");
                System.out.println("====================LogAnalyser====================");
                System.out.println("1) Nombre total d'actions");
                System.out.println("2) Nombre d'acces refuser");
                System.out.println("3) Utilisateurs distincts");
                System.out.println("4) Actions par utilisateur");
                System.out.println("5) Top 3 des fichiers consultes");
                System.out.println("6) Acces refuses d'un utilisateur");
                System.out.println("7) Utilisateur le plus actif");
                System.out.println("8) Reapartition des actions par type");
                System.out.println("0) Quitter");


                System.out.print("votre choix est : ");

                choix = scanner.nextInt();

                switch (choix) {
                    case 1:
                        System.out.println("\n");
                        System.out.print("le nomre total d'action : ");
                        System.out.println(logService.totalActions());
                        System.out.println("\n");
                        break;
                    case 2:
                        System.out.println("\n");
                        System.out.print("le nomre d'action refuser : ");
                        System.out.println(logService.totalRefuse());
                        System.out.println("\n");
                        break;
                    case 3:
                        System.out.println("\n");
                        System.out.print("les Action par utilisateur sont : ");
                        System.out.println(logService.userDistinct());
                        System.out.println("\n");
                        break;
                    case 4:
                        System.out.println("\n");
                        System.out.print("les Utilisateur de Lineperm sont : ");
                        System.out.println(logService.userDistinct());
                        System.out.println("\n");                        
                        break;
                    case 5:
                        
                        break;
                    case 6:
                        
                        break;
                    case 7:
                        
                        break;
                    case 8:
                        
                        break;
                    case 0:
                        System.out.println("EXIT LogAnalyser !. ");
                        break;
                
                    default:
                        break;
                }

            }while(!(choix == 0));

        }
    }
