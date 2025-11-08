package ci.ada_bank.Dao.Singletton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Instance unique de la classe
    private static DatabaseConnection instance;
    private Connection connection;

    // Informations de connexion (à remplacer par vos propres informations)
    private final static String url = "jdbc:postgresql://localhost:5432/postgres";    //private final static String username = "root";
    //private final static String password = "4564";
    private final static String username = "postgres";
    private final static String password = "root";

    // Constructeur privé pour empêcher l'instanciation directe
    private DatabaseConnection() {
        try {

            // Création de la connexion
            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion à la base de données établie avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }

    // Méthode pour obtenir l'instance unique
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    // Méthode pour obtenir la connexion
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de la connexion : " + e.getMessage());
        }
        return connection;
    }

    // Méthode pour fermer la connexion
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexion à la base de données fermée avec succès !");
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}

