package ma.youcode.lineperm.dao;

import ma.youcode.lineperm.model.AccessLog;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class LogDao extends AbstractDao<AccessLog> {

    private static final String insertLogQuery =
            "INSERT INTO logs(date, time, user, action, file, result) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String findLogByIdQuery =
            "SELECT * FROM logs WHERE id = ?";

    private static final String deleteLogQuery =
            "DELETE FROM logs WHERE id = ?";

    private static final String countTotalActionsQuery =
            "SELECT COUNT(*) AS total FROM logs";

    private static final String countAccessDeniedQuery =
            "SELECT COUNT(*) AS total FROM logs WHERE result = 'REFUSE'";

    private static final String countDistinctUsersQuery =
            "SELECT COUNT(DISTINCT user) AS total FROM logs";


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

            try (ResultSet result = preparedStatement.executeQuery()) {

                if (result.next()) {
                    LocalDate date = LocalDate.parse(result.getString("date"));
                    LocalTime time = LocalTime.parse(result.getString("time"));
                    String user = result.getString("user");
                    String action = result.getString("action");
                    String file = result.getString("file");
                    String resultat = result.getString("result");

                    return new AccessLog(date, time, user, action, file, resultat);
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(deleteLogQuery)) {
            preparedStatement.setInt(1, id);

            int result = preparedStatement.executeUpdate();

            if(result == 0){
                System.out.println("Aucun Logs avec id = " + id);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public long countTotalActions() {
        try (PreparedStatement preparedStatement = connect.prepareStatement(countTotalActionsQuery);
            ResultSet result = preparedStatement.executeQuery()) {

            if (result.next()) {
                return result.getLong("total");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return 0;
    }
    
    public long countAccesRefuses() {
        try (PreparedStatement preparedStatement = connect.prepareStatement(countAccessDeniedQuery)) {
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return 0;
    }

    public long countUtilisateursDistincts() {
        try (PreparedStatement preparedStatement = connect.prepareStatement(countDistinctUsersQuery)) {
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return 0;
    }

    public void actionsByUser() {

    }

    public void topFichiers(int limite) {

    }

    public void refusesByUser(String username) {

    }

    public void userPlusActif() {

    }

    public void repartitionByAction() {

    }
}