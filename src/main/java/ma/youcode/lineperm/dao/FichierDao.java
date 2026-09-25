package ma.youcode.lineperm.dao;

import java.sql.*;
import java.util.List;

import ma.youcode.lineperm.model.FichierProtege;

public class FichierDao extends AbstractDao<FichierProtege> {

    private static final String insertFileQuery =
        "INSERT INTO files(name, owner, permissions) VALUES (?, ?, ?)";

    private static final String findFileByIdQuery =
            "SELECT * FROM files WHERE id = ?";

    private static final String deleteFileQuery =
            "DELETE FROM files WHERE id = ?";

    private static final String findFileByOwnerQuery=
            "SELECT * FROM files WHERE owner = ?";


    @Override
    public void save(FichierProtege fichier) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(insertFileQuery)) {
            preparedStatement.setString(1, fichier.getNameOfFile());
            preparedStatement.setString(2, fichier.getOwner());
            preparedStatement.setString(3, fichier.getPermission());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public FichierProtege findById(int id) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(findFileByIdQuery)) {
            preparedStatement.setInt(1, id);

            try (ResultSet reuslt = preparedStatement.executeQuery()) {
                if (reuslt.next()) {
                    String name = reuslt.getNString("nameOfFile");
                    String owner = reuslt.getNString("owner");
                    String permissions = reuslt.getNString("permissions");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(deleteFileQuery)) {
            preparedStatement.setInt(1, id);

            int result = preparedStatement.executeUpdate();

            if(result == 0){
                System.out.println("Aucun utilisateur avec id = " + id);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<FichierProtege> findByProprietaire(int userId) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(findFileByOwnerQuery)) {
            preparedStatement.setInt(1, userId);
            
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    String nameOfFile = result.getNString("nameOfFile");
                    String owner = result.getNString("owner");
                    String permissions = result.getNString("permissions");
                }
            }
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }    


}