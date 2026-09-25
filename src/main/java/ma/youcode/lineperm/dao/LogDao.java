package ma.youcode.lineperm.dao;

import ma.youcode.lineperm.model.AccessLog;
import java.sql.*;

public class LogDao extends AbstractDao<AccessLog> {

    private static final String insertLogQuery =
            "INSERT INTO logs(date, time, user, action, file, result) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String findLogByIdQuery =
            "SELECT * FROM logs WHERE id = ?";




    @Override
    public void save(AccessLog log) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(insertLogQuery)) {
            preparedStatement.setString(1, log.getDate().toString());
            preparedStatement.setString(2, log.getHeure().toString());
            preparedStatement.setString(3, log.getUtilisateur());
            preparedStatement.setString(4, log.getAction());
            preparedStatement.setString(5, log.getFichier());
            preparedStatement.setString(6, log.getResultat());

            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public AccessLog findById(int id) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(findLogByIdQuery)) {
            
            preparedStatement.setInt(1, id);

            try(ResultSet result = preparedStatement.executeQuery()){

                if (result.next()) {
                    String date = result.getString("date");
                    String time = result.getString("time");
                    String user = result.getString("user");
                    String action = result.getString("action");
                    String file = result.getString("file");
                    String resultat = result.getString("result");
                }

            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(int id) {
        // try () {
            
        // } catch (SQLException e) {
        //     System.err.println(e.getMessage());
        // }
    }


}