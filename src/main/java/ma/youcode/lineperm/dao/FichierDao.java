package ma.youcode.lineperm.dao;

import java.sql.*;
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

    private static final String permissionQuery =
            "UPDATE files SET permissions = ? WHERE id = ?";

    private static final String findFileByNameQuery =
            "SELECT * FROM files WHERE name = ?";

    private static final String findAllFilesQuery =
            "SELECT * FROM files";

    private static final String updatePermissionByNameQuery =
            "UPDATE files SET permissions = ? WHERE name = ?";

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

            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    String name = result.getString("name");
                    String owner = result.getString("owner");
                    String permissions = result.getString("permissions");

                    FichierProtege fichier = new FichierProtege(name, owner);

                    if (permissions.charAt(4) == 'r') {
                        fichier.setotherR(true);
                    }

                    if (permissions.charAt(5) == 'w') {
                        fichier.setotherW(true);
                    }

                    if (permissions.charAt(6) == 'd') {
                        fichier.setotherD(true);
                    }

                    return fichier;
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
                System.out.println("Aucun fichier avec id = " + id);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void findByProprietaire(String ownerName) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(findFileByOwnerQuery)) {
            preparedStatement.setString(1, ownerName);

            try (ResultSet result = preparedStatement.executeQuery()) {
                boolean found = false;

                while (result.next()) {
                    found = true;

                    String permissions = result.getString("permissions");
                    String owner = result.getString("owner");
                    String name = result.getString("name");

                    System.out.println(permissions + " " + owner + " " + name);
                }

                if (!found) {
                    System.out.println("Aucun fichier");
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }   

    public void updatePermission(int id, String permission) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(permissionQuery)) {
            preparedStatement.setString(1, permission);
            preparedStatement.setInt(2, id);

            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                System.out.println("Aucun fichier trouvé avec id = " + id);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public FichierProtege findByName(String name) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(findFileByNameQuery)) {
            preparedStatement.setString(1, name);

            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    String nameOfFile = result.getString("name");
                    String owner = result.getString("owner");
                    String permissions = result.getString("permissions");

                    FichierProtege fichier = new FichierProtege(nameOfFile, owner);

                    if (permissions.charAt(4) == 'r') {
                        fichier.setotherR(true);
                    }

                    if (permissions.charAt(5) == 'w') {
                        fichier.setotherW(true);
                    }

                    if (permissions.charAt(6) == 'd') {
                        fichier.setotherD(true);
                    }

                    return fichier;
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }
    public void findAll() {
        try (PreparedStatement preparedStatement = connect.prepareStatement(findAllFilesQuery);
            ResultSet result = preparedStatement.executeQuery()) {

            boolean found = false;

            while (result.next()) {
                found = true;

                String permissions = result.getString("permissions");
                String owner = result.getString("owner");
                String name = result.getString("name");

                System.out.println(permissions + " " + owner + " " + name);
            }

            if (!found) {
                System.out.println("Aucun fichier");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public void updatePermission(String fileName, String permission) {
        try (PreparedStatement preparedStatement = connect.prepareStatement(updatePermissionByNameQuery)) {
            preparedStatement.setString(1, permission);
            preparedStatement.setString(2, fileName);

            int result = preparedStatement.executeUpdate();

            if (result == 0) {
                System.out.println("Aucun fichier trouvé avec name = " + fileName);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

}