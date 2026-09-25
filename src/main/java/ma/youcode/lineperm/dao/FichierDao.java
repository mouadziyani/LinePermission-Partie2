package ma.youcode.lineperm.dao;

import java.sql.*;

import ma.youcode.lineperm.model.FichierProtege;

public class FichierDao extends AbstractDao<FichierProtege> {

    private static final String insertQuery =
        "INSERT INTO files(name, owner, permissions) VALUES (?, ?, ?)";

    private static final String findByIdQuery =
            "SELECT * FROM files WHERE id = ?";

    @Override
    public void save(FichierProtege fichier) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(insertQuery)) {
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
        try (PreparedStatement preparedStatement = connect.prepareStatement(findByIdQuery)) {
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
    }


}