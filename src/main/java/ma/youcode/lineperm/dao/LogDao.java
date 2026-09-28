package ma.youcode.lineperm.dao;

import ma.youcode.lineperm.model.AccessLog;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    private static final String findDistinctUsersQuery  =
            "SELECT DISTINCT user FROM logs";

    private static final String actionsByUserQuery =
            "SELECT user, COUNT(*) AS total FROM logs GROUP BY user";

    private static final String topFilesQuery =
            "SELECT file, COUNT(*) AS total FROM logs GROUP BY file ORDER BY total DESC LIMIT 3";

    private static final String refusedByUserQuery =
            "SELECT COUNT(*) AS total FROM logs WHERE user = ? AND result = 'REFUSE'";

    private static final String userPlusActifQuery =
            "SELECT user, COUNT(*) AS total FROM logs GROUP BY user ORDER BY total DESC LIMIT 1";

    private static final String repartitionByActionQuery =
            "SELECT action, COUNT(*) AS total FROM logs GROUP BY action";

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
            ResultSet result = preparedStatement.executeQuery();

            if(result.next()){
                return result.getLong("total");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return 0;
    }

    public List<String> findDistinctUsers() {
        List<String> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connect.prepareStatement(findDistinctUsersQuery)) {
            ResultSet result = preparedStatement.executeQuery();

            while(result.next()){
                users.add(result.getString("user"));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return users;
    }

    public void actionsByUser() {
        try (PreparedStatement preparedStatement = connect.prepareStatement(actionsByUserQuery);
            ResultSet result = preparedStatement.executeQuery()) {

            while (result.next()) {
                String user = result.getString("user");
                long total = result.getLong("total");

                System.out.println(user + " : " + total);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void topFichiers() {
        try (PreparedStatement preparedStatement = connect.prepareStatement(topFilesQuery);
            ResultSet result = preparedStatement.executeQuery()) {

            while (result.next()) {
                String file = result.getString("file");
                long total = result.getLong("total");

                System.out.println(file + " : " + total);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void refusesByUser(String username) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(refusedByUserQuery)) {

            preparedStatement.setString(1, username);

            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    long total = result.getLong("total");
                    System.out.println(username + " : " + total);
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void userPlusActif() {
        try (PreparedStatement preparedStatement = connect.prepareStatement(userPlusActifQuery);
            ResultSet result = preparedStatement.executeQuery()) {

            if (result.next()) {
                String user = result.getString("user");
                long total = result.getLong("total");

                System.out.println(user + " : " + total);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void repartitionByAction() {
        try (PreparedStatement preparedStatement = connect.prepareStatement(repartitionByActionQuery);
            ResultSet result = preparedStatement.executeQuery()) {

            while (result.next()) {
                String action = result.getString("action");
                long total = result.getLong("total");

                System.out.println(action + " : " + total);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}