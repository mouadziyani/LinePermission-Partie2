package ma.youcode.lineperm.ui;

import java.util.*;
import ma.youcode.lineperm.service.AuthService;

public class ConsoleApp {

    public void LoadApp() throws Exception {

        AuthService service = new AuthService();

        String choixDeUser;
        String choix = "";
        String currentUsername = null;

        Scanner scanner = new Scanner(System.in);

        System.out.println("============================================================");
        System.out.println("LinePermission: gestion des fichiers et permissions");
        System.out.println("============================================================");
        System.out.println("Non connecter. Commandes : signup | login | logout | exit");

        do {

            if (AuthService.isAuth && currentUsername != null) {
                System.out.print(currentUsername + "@lineperm> ");
            } else {
                System.out.print("lineperm> ");
            }

            choixDeUser = scanner.nextLine();
            choix = choixDeUser.trim().toLowerCase();

            switch (choix) {

                case "signup":

                    if (AuthService.isAuth) {
                        System.out.println("Vous etes deja connecte");
                        break;
                    }

                    System.out.println(" ===================== SIGN UP ===================== ");
                    System.out.print("Username : ");
                    String username = scanner.nextLine().trim();

                    System.out.print("Password : ");
                    String password = scanner.nextLine();

                    service.signup(username, password);
                    break;

                case "login":

                    if (AuthService.isAuth) {
                        System.out.println("Vous etes deja connecte");
                        break;
                    }
                    
                    System.out.println(" ===================== Login ===================== ");
                    System.out.print("Username : ");
                    String loginUsername = scanner.nextLine();

                    System.out.print("Password : ");
                    String loginPpassword = scanner.nextLine();

                    service.login(loginUsername, loginPpassword);

                    if(AuthService.isAuth) {
                        currentUsername = loginUsername ;
                    }
                
                break;

                case "logout":
                
                        if(AuthService.isAuth){

                            AuthService.isAuth = false ;
                            currentUsername = null ;

                            System.out.println("deconect");

                        }else{
                            System.out.println("not conncted");
                        }
                            
                break;

                case "exit":
                    break;

                default:
                    System.out.println("Commande inconnue");
                    break;
            }

        } while (!choix.equals("exit"));

        System.out.println("Au revoir !");
    }
}