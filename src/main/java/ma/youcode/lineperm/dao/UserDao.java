package ma.youcode.lineperm.dao;

import java.sql.*;

import ma.youcode.lineperm.model.Users;

public class UserDao extends AbstractDao<Users> {

    private static final String insertQuery =
            "INSERT INTO users(username, password) VALUES (?, ?)";

    @Override
    public void save(Users user) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(insertQuery)){
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPasswordHash());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Users findById(int id) {
        return null ;
    }

    @Override
    public void delete(int id) {
    }

    
}