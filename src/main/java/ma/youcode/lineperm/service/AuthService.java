package ma.youcode.lineperm.service;

import org.mindrot.jbcrypt.BCrypt;

import ma.youcode.lineperm.model.Users;


public class AuthService{

    public static boolean isAuth = false ;
    private UserService userService = new UserService();

    public void login(String username , String password){

        Users user = userService.findByUsername(username);

        if (user == null) {
            System.out.println("username not found");
            return;
        }

        if (!BCrypt.checkpw(password, user.getPasswordHash())) {
            System.out.println("password incorrect");
            return;
        }

        isAuth = true ;
        System.out.println("connect");

    }

    public void signup(String username , String password){

        if(username.isEmpty()){
            System.out.println("username invalide");
            return ;
        }

        if(password.isEmpty()){
            System.out.println("password invalid");
            return;
        }

        if (userService.findByUsername(username)!=null) {
            System.out.println("username deja utliser dans un autre compte");
            return ;
        }

        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        Users user = new Users(username, passwordHash) ;
        userService.saveUsers(user);
        System.out.println("compte creer");

    }

    // public AuthService(){
    //     new UserService();
    // }


    // Path userPath = Path.of("src/main/resources/users.txt");   
    
    
    // public void login(String username, String password) {

    //     if (UserService.users.containsKey(username)) {

    //         String passwordHash = UserService.users.get(username);

    //         if (!BCrypt.checkpw(password, passwordHash)) {
    //             System.out.println("password incorrect");
    //             return;
    //         }

    //         isAuth = true;
    //         System.out.println("connect");

    //     } else {
    //         System.out.println("username not found");
    //     }
    // }

    // public void signup(String username , String password) throws Exception{

    //     if (username.isEmpty()) {
    //         System.out.println("Username invalide");
    //         return;
    //     }

    //     if (username.contains(" ")) {
    //         System.out.println("Username invalide");
    //         return;
    //     }

    //     if (username.contains(":")) {
    //         System.out.println("Username invalide");
    //         return;
    //     }

    //     if (password.isEmpty()) {
    //         System.out.println("Password invalide");
    //         return;
    //     }

    //     if (UserService.users.containsKey(username)) {
    //         System.out.println("Username deja existe");
    //         return;
    //     }

    //     try {
            
    //         String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
    //         String creatUser = username + " : " + passwordHash ; 
    //         UserService.users.put(username, passwordHash);

    //         Files.writeString(userPath, creatUser + System.lineSeparator() , StandardOpenOption.APPEND);

    //     } catch (Exception e) {
    //         System.out.println(e.getStackTrace());
    //     }
    // }


    

}
