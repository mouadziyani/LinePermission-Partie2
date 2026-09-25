package ma.youcode.lineperm.dao;

import java.sql.*;

import ma.youcode.lineperm.model.Users;

public class UserDao extends AbstractDao<Users> {

    private static final String insertQuery =
            "INSERT INTO users(username, password) VALUES (?, ?)";
    
    private static final String deleteQuery =
            "DELETE FROM users WHERE id = ?";

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
        try(PreparedStatement preparedStatement = connect.prepareStatement(findByIdQuery)){
            preparedStatement.setInt(1, id);

            try(ResultSet result = preparedStatement.executeQuery()){
                if (result.next()) {
                    String username = result.getString("username");
                    String passwordHash = result.getString("password");

                    return new Users(username, passwordHash);
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null ;
    }

    @Override
    public void delete(int id) {
    }

    
}