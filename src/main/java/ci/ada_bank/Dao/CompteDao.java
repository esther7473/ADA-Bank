package ci.ada_bank.Dao;

import ci.ada_bank.Dao.Singletton.DatabaseConnection;
import ci.ada_bank.Modeles.Client;
import ci.ada_bank.Modeles.Compte;
import ci.ada_bank.Modeles.Enumeration.CompteType;
import ci.ada_bank.Modeles.Enumeration.Statut;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompteDao {

        private static final String INSERT = "INSERT INTO compte (numeroCompte, solde, typeCompte, clientId, statut) VALUES (?, ?, ?, ?, ?)";
        private static final String SELECT_ALL = "SELECT * FROM compte";
        private static final String SELECT_BY_ID = "SELECT * FROM compte WHERE id = ?";
        private static final String SELECT_BY_NUMERO_COMPTE = "SELECT * FROM compte WHERE numeroCompte = ?";

        private static final String DELETE_BY_ID = "DELETE FROM compte WHERE id = ?";
        private static final String UPDATE_BY_ID = "UPDATE compte SET numeroCompte=?, solde=?, typeCompte=?, clientId=?, statut=? WHERE id=?";
        private static final String SELECT_BY_COLUMN = "SELECT * FROM compte WHERE %s = ?";

        private static final ClientDao clientDao = new ClientDao();
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        Connection connection = dbConnection.getConnection();

        public CompteDao() {
            this.connection = DatabaseConnection.getInstance().getConnection();
        }

        public Compte create(Compte compte) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, compte.getNumeroCompte());
                ps.setBigDecimal(2, compte.getSolde());
                ps.setString(3, compte.getTypeCompte().name());
                ps.setLong(4, compte.getClient().getId());
                ps.setString(5, compte.getStatut().name());

                int affectedRows = ps.executeUpdate();
                if (affectedRows > 0) {
                    ResultSet keys = ps.getGeneratedKeys();
                    if (keys.next()) {
                        compte.setId(keys.getLong(1));
                    }
                }
                return compte;
            } catch (SQLException e) {
                throw new RuntimeException( e);
            }
        }

        public Compte updateById(Compte compte) {
            try (PreparedStatement ps = connection.prepareStatement(UPDATE_BY_ID)) {
                ps.setString(1, compte.getNumeroCompte());
                ps.setBigDecimal(2, compte.getSolde());
                ps.setString(3, compte.getTypeCompte().name());
                ps.setLong(4, compte.getClient().getId());
                ps.setString(5, compte.getStatut().name());
                ps.setLong(6, compte.getId());

                ps.executeUpdate();
                return compte;
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la mise à jour du compte", e);
            }
        }

        public boolean deleteById(Long id) {
            try (PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID)) {
                ps.setLong(1, id);
                return ps.executeUpdate() > 0;
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la suppression du compte", e);
            }
        }

        public Compte readById(Long id) {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return mapCompteFromResultSet(rs);
                }
                return null;
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la lecture du compte", e);
            }
        }

    public Compte readByNumeroCompte(String numero) {
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_NUMERO_COMPTE)) {
            ps.setString(1, numero);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapCompteFromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la lecture du compte", e);
        }
    }

        public List<Compte> findAll() {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL)) {
                ResultSet rs = ps.executeQuery();
                List<Compte> comptes = new ArrayList<>();
                while (rs.next()) {
                    comptes.add(mapCompteFromResultSet(rs));
                }
                return comptes;
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la lecture des comptes", e);
            }
        }

        public List<Compte> findByField(String fieldName, String value) {
            String query = String.format(SELECT_BY_COLUMN, fieldName);
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, value);
                ResultSet rs = ps.executeQuery();
                List<Compte> comptes = new ArrayList<>();
                while (rs.next()) {
                    comptes.add(mapCompteFromResultSet(rs));
                }
                return comptes;
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la recherche par champ", e);
            }
        }

        private Compte mapCompteFromResultSet(ResultSet rs) {
            try {
                System.out.println("===== Debut Mapping ======!");

                Compte compte = new Compte();
                compte.setId(rs.getLong("id"));
                compte.setNumeroCompte(rs.getString("numeroCompte"));
                compte.setSolde(rs.getBigDecimal("solde"));
                compte.setTypeCompte(CompteType.valueOf(rs.getString("typeCompte")));

                System.out.println("compte recupere: !"+compte.getNumeroCompte());

                Client client = clientDao.getClientById(rs.getLong("clientId"));
                compte.setClient(client);
                compte.setStatut(Statut.valueOf(rs.getString("statut")));

                Timestamp ts = rs.getTimestamp("dateOuverture");
                if (ts != null) {
                    compte.setDateOuverture(String.valueOf(ts.toLocalDateTime()));
                }

                compte.setStatut(Statut.valueOf(rs.getString("statut")));
                System.out.println("====== fin de  mapping! =====");
                return compte;

            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors du mapping du compte", e);
            }
        }
}
