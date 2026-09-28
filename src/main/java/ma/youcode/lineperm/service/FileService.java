package ma.youcode.lineperm.service;

import java.nio.file.Files;
import java.nio.file.Path;

import ma.youcode.lineperm.dao.FichierDao;
import ma.youcode.lineperm.model.FichierProtege;

public class FileService {

    FichierDao fichierDao = new FichierDao();

    Path dossier = Path.of("createdFile");

    public boolean touch(String name, String owner) {
        try {
            if (name == null || name.isEmpty()) {
                return false;
            }

            if (fichierDao.findByName(name) != null) {
                return false;
            }

            if (!Files.exists(dossier)) {
                Files.createDirectory(dossier);
            }

            Path file = dossier.resolve(name);

            if (Files.exists(file)) {
                return false;
            }

            Files.createFile(file);

            FichierProtege newFile = new FichierProtege(name, owner);

            fichierDao.save(newFile);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void lsFichier() {
        fichierDao.findAll();
    }

    public boolean catFile(String name, String username) {
        FichierProtege filePerm = fichierDao.findByName(name);

        if (filePerm == null) {
            System.out.println("File not found");
            return false;
        }

        if (!filePerm.getOwner().equals(username) && !filePerm.getOtherR()) {
            System.out.println("Permission denied.");
            return false;
        }

        Path file = dossier.resolve(name);

        try {
            if (!Files.exists(file)) {
                System.out.println("Aucun fichier");
                return false;
            }

            String content = Files.readString(file);

            if (content.isEmpty()) {
                System.out.println("file is vide");
                return true;
            }

            System.out.println(content);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean nano(String name, String contenue, String username) {
        FichierProtege filePerm = fichierDao.findByName(name);

        if (filePerm == null) {
            System.out.println("File not found");
            return false;
        }

        if (!filePerm.getOwner().equals(username) && !filePerm.getOtherW()) {
            System.out.println("Permission denied.");
            return false;
        }

        Path file = dossier.resolve(name);

        try {
            if (!Files.exists(file)) {
                System.out.println("Aucun fichier");
                return false;
            }

            Files.writeString(file, contenue);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean chmod(String username, String permission, String fileName) {
        FichierProtege filePerm = fichierDao.findByName(fileName);

        if (filePerm == null) {
            System.out.println("File not found");
            return false;
        }

        if (!filePerm.getOwner().equals(username)) {
            System.out.println("Permission denied.");
            return false;
        }

        if (permission.equals("r")) {
            filePerm.setPermission('r');
        } else if (permission.equals("w")) {
            filePerm.setPermission('w');
        } else if (permission.equals("d")) {
            filePerm.setPermission('d');
        } else if (permission.equals("-r")) {
            filePerm.removePermission('r');
        } else if (permission.equals("-w")) {
            filePerm.removePermission('w');
        } else if (permission.equals("-d")) {
            filePerm.removePermission('d');
        } else {
            System.out.println("Permission invalide");
            return false;
        }

        fichierDao.updatePermission(fileName, filePerm.getPermission());

        return true;
    }

    public boolean canWrite(String fileName, String username) {
        FichierProtege filePerm = fichierDao.findByName(fileName);

        if (filePerm == null) {
            return false;
        }

        if (!filePerm.getOwner().equals(username) && !filePerm.getOtherW()) {
            return false;
        }

        return true;
    }
}