package ci.ada_bank.Dao;

import ci.ada_bank.Dao.Singletton.DatabaseConnection;
import ci.ada_bank.Modeles.Banque;
import ci.ada_bank.Modeles.Client;
import ci.ada_bank.Modeles.builders.banqueBuilder.BanqueBuilderImpl;
import ci.ada_bank.Modeles.builders.clientBuilder.ClientBuilderImpl;
import ci.ada_bank.Services.factory.ServiceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    private static final Connection connection = DatabaseConnection.getInstance().getConnection();
    private static ClientBuilderImpl clientBuilder = new ClientBuilderImpl();
    private static BanqueBuilderImpl banqueBuilder = new BanqueBuilderImpl();


    private static final String INSERT = "INSERT INTO Client (numeroclient, nom, prenom, email, banqueid) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_BY_ID = "UPDATE Client SET numeroclient=?, nom=?, prenom=?, email=?, banqueid=? WHERE id=?";

    private static final String DELETE_BY_ID = "DELETE FROM Client WHERE id=?";

    private static final String DELETE_BY_BANQUE_ID = "DELETE FROM Client WHERE banqueid=?";

    private static final String READ_BY_ID = "SELECT * FROM Client WHERE id=?";

    private static final String READ_BY_BANQUE_ID = "SELECT * FROM Client WHERE banqueid=?";

    private static final String READ_ALL = "SELECT * FROM Client";

    private static final String READ_ALL_BY_BANQUE_ID = "SELECT * FROM Client WHERE banqueid=?";

    private static final String READ_BY_NAME = "SELECT * FROM Client WHERE nom=?";

    private static final String READ_BY_PRENOM = "SELECT * FROM Client WHERE prenom=?";

    private static final String READ_BY_EMAIL = "SELECT * FROM Client WHERE email=?";

    private static final String READ_BY_NUMERO =  "SELECT * FROM Client WHERE numeroclient=?";





    public Client createClient(Client client){
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, client.getNumeroClient());
            statement.setString(2,client.getNom());
            statement.setString(3,client.getPrenom());
            statement.setString(4,client.getEmail());
            statement.setInt(5,1);

            int affectedRows = statement.executeUpdate();

            int nb_clients = client.getBanque().getNombreClients();
            Banque banque = client.getBanque();
            banque.setNombreClients(nb_clients);

            ServiceFactory.getBanqueDao().updateById(banque);

            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    client.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return client;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Client updateClientById(Client client){
        try {

            PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, client.getNumeroClient());
            statement.setString(2,client.getNom());
            statement.setString(3,client.getPrenom());
            statement.setString(4,client.getEmail());
            statement.setLong(5,client.getBanque().getId());
            statement.setLong(6,client.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }
            return client;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteClientById(long id){
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setLong(1,id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteByBanqueId(long id){
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_BANQUE_ID);
            statement.setLong(1,id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erreur de requete, aucune ligne n'a ete modifiee.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Client getClientById(long id){
        try {
            PreparedStatement statement = connection.prepareStatement(READ_BY_ID);
            statement.setLong(1,id);

            return getClient(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Client getClientByBanqueId(long id){
        try {
            PreparedStatement statement = connection.prepareStatement(READ_BY_BANQUE_ID);
            statement.setLong(1,id);
            return getClient(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Client> getAllClients(){
        try{
            PreparedStatement statement = connection.prepareStatement(READ_ALL);
            final List<Client> finalClients = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Client Client = mappingClientFromResultSet(resultSet);
                finalClients.add(Client);
            }
            return finalClients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Client> getAllClientsByBankId(Banque banque){
        try{
            PreparedStatement statement = connection.prepareStatement(READ_ALL_BY_BANQUE_ID);
            statement.setLong(1,banque.getId());
            final List<Client> finalClients = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Client Client = mappingClientFromResultSet(resultSet);
                finalClients.add(Client);
            }
            return finalClients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Client>  searchClientByNom(String nom){
        try {
            PreparedStatement statement = connection.prepareStatement(READ_BY_NAME);
            statement.setString(1,nom);

            final List<Client> finalClients = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Client Client = mappingClientFromResultSet(resultSet);
                finalClients.add(Client);
            }
            return finalClients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Client searchClientByPrenom(String prenom){
        try {
            PreparedStatement statement = connection.prepareStatement(READ_BY_PRENOM);
            statement.setString(1,prenom);
            return getClient(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Client searchClientByEmail(String email){
        try {
            PreparedStatement statement = connection.prepareStatement(READ_BY_EMAIL);
            statement.setString(1,email);
            return getClient(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Client searchClientByNumero(String email){
        try {
            PreparedStatement statement = connection.prepareStatement(READ_BY_NUMERO);
            statement.setString(1,email);
            return getClient(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*******Utilitaires **********/

    private Client getClient(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        Client client = null;

        if (resultSet.next()){
            client = mappingClientFromResultSet(resultSet);
        }
        System.out.println("client dans getClient"+client.getNom());
        return client;

    }
    private Client mappingClientFromResultSet(ResultSet result) throws SQLException {
        try {
            long clientId = result.getLong("id");
            String clientNom = result.getString("nom");
            long banqueId = result.getLong("banqueid");
            String email = result.getString("email");
            String prenom = result.getString("prenom");
            Banque banque = banqueBuilder.setId(banqueId).build();

            return clientBuilder.setNumeroClient(result.getString("numeroclient"))
                    .setId(clientId).setNom(clientNom).setBanque(banque).setEmail(email).setPrenom(prenom).build();

        } catch (IllegalArgumentException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}




