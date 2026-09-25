package ma.youcode.lineperm.service;

import java.nio.file.*;
import java.util.*;
import ma.youcode.lineperm.dao.*;
import ma.youcode.lineperm.model.Users;

public class UserService{

    public final UserDao userDao;

    public static HashMap<String , String> users = new HashMap<>();

        Path file = Path.of("src/main/resources/users.txt");

    public UserService(){
        this.userDao = new UserDao();
    };

    public void saveUsers(Users user){
        userDao.save(user);
    }

    public Users findById(int id){
        return userDao.findById(id);
    }
    public Users findByUsername(String username){
        return userDao.findByUsername(username);
    }
    public void deletUsers(int id){
        userDao.delete(id);
    }

}