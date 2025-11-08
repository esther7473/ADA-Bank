package ci.ada_bank.Dao;

import ci.ada_bank.Dao.Singletton.DatabaseConnection;
import ci.ada_bank.Modeles.Banque;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BanqueDao {
    private final static String SELECT_ALL = "SELECT * FROM banque";
    private final static String SELECT_BY_ID = "SELECT * FROM banque WHERE id = ?";
    private final static String SELECT_BY_NOM = "SELECT * FROM banque WHERE nom = ?";
    private final static String SELECT_BY_VILLE = "SELECT * FROM banque WHERE ville = ?";
    private final static String SELECT_BY_COLUMN = "SELECT * FROM banque WHERE %s = ?";
    private final static String SELECT_15 = "SELECT * FROM banque ORDER BY nombreClients DESC, nom ASC LIMIT 15";
    private final static String SELECT_BY_PAYS = "SELECT * FROM banque WHERE pays = ?";
    private final static String INSERT = "INSERT INTO banque (nom, pays, ville, nombreClients) VALUES (?, ?, ?, ?)";
    private final static String UPDATE_BY_ID = "UPDATE banque SET nom = ?, pays = ?, ville = ?, nombreClients = ? WHERE id = ?";
    private final static String UPDATE_BY_NOM = "UPDATE banque SET pays = ?, ville = ?, dateCreation = ?, nombreClients = ? WHERE nom = ?";
    private final static String DELETE_BY_ID = "DELETE FROM banque WHERE id = ?";
    private final static String DELETE_BY_NOM = "DELETE FROM banque WHERE nom = ?";


    DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    Connection connection = dbConnection.getConnection();


    public BanqueDao() {}


    public Banque create(Banque banque) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, banque.getNom());
            preparedStatement.setString(2, banque.getPays());
            preparedStatement.setString(3, banque.getVille());
            //preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(banque.getDateCreation()));
            preparedStatement.setInt(4, banque.getNombreClients());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows>0){
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    banque.setId(generatedKeys.getLong(1));
                }else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            System.out.println(banque);
            return banque;
        } catch (SQLException e) {
            throw new RuntimeException("Error while inserting banque",e);
        }
    }

    public Banque updateById(Banque banque) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID);
            preparedStatement.setString(1, banque.getNom());
            preparedStatement.setString(2, banque.getPays());
            preparedStatement.setString(3, banque.getVille());
            //preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(banque.getDateCreation()));
            preparedStatement.setInt(4, banque.getNombreClients());
            preparedStatement.setLong(5, banque.getId());
            preparedStatement.executeUpdate();
            System.out.println(banque);
            return banque;
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating banque",e);
        }
    }

    public Banque updateByNom(Banque banque) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_NOM);
            preparedStatement.setString(1, banque.getPays());
            preparedStatement.setString(2, banque.getVille());
            preparedStatement.setTimestamp(3, java.sql.Timestamp.valueOf(banque.getDateCreation()));
            preparedStatement.setInt(4, banque.getNombreClients());
            preparedStatement.setString(5, banque.getNom());
            preparedStatement.executeUpdate();
            System.out.println(banque);
            return banque;
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating banque",e);
        }
    }

    public Banque deleteById(Banque banque) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setLong(1, banque.getId());
            preparedStatement.executeUpdate();
            return banque;
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting banque",e);
        }
    }

    public Banque deleteByNom(Banque banque) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_NOM);
            preparedStatement.setString(1, banque.getNom());
            preparedStatement.executeUpdate();
            return banque;
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting banque",e);
        }
    }

    public Banque readById(Banque banque) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, banque.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                banque = mapBanqueFromResultSet(resultSet);
            }
            System.out.println(banque+"\n");
            return banque;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading banque",e);
        }
    }

    public Banque readByNom(Banque banque) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_NOM);
            preparedStatement.setString(1, banque.getNom());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                banque=mapBanqueFromResultSet(resultSet);
            }
            System.out.println(banque+"\n");
            return banque;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading banque",e);
        }
    }

    public List<Banque> findAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Banque> banques = new ArrayList<>();
            while (resultSet.next()) {
                Banque banque = mapBanqueFromResultSet(resultSet);
                banques.add(banque);
                System.out.println(banque);
            }
            return banques;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading banques", e);
        }
    }

    public List<Banque> findByField(String fieldName, String value) {
        String query = String.format(SELECT_BY_COLUMN, fieldName);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Banque> banques = new ArrayList<>();

            while (resultSet.next()) {
                Banque banque = mapBanqueFromResultSet(resultSet);
                banques.add(banque);
                System.out.println(banque);
            }
            return banques;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading banque", e);
        }
    }
    public List<Banque> findByNom(String nom) {
        return findByField("nom", nom);
    }

    public List<Banque> findByVille(String ville) {
        return findByField("ville", ville);
    }

    public List<Banque> findByPays(String pays) {
        return findByField("pays", pays);
    }


    public List<Banque> read15Banques() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_15);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Banque> banques = new ArrayList<>();
            System.out.println();
            while (resultSet.next()) {
                Banque banque = mapBanqueFromResultSet(resultSet);
                banques.add(banque);
                System.out.println(banque);
            }
            return banques;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading banque",e);
        }
    }

    private Banque mapBanqueFromResultSet(ResultSet resultSet){
        try {
            Banque banque = new Banque();
            banque.setId(resultSet.getLong("id"));
            banque.setNom(resultSet.getString("nom"));
            banque.setPays(resultSet.getString("pays"));
            banque.setVille(resultSet.getString("ville"));
            LocalDateTime sqlDate = resultSet.getObject("dateCreation", LocalDateTime.class);
            if (sqlDate != null) {
                banque.setDateCreation(String.valueOf(sqlDate.toLocalDate().atStartOfDay()));
            }
            banque.setNombreClients(resultSet.getInt("nombreClients"));
            return banque;
        } catch (SQLException e) {
            throw new RuntimeException("Error while mapping banque",e);
        }
    }

}