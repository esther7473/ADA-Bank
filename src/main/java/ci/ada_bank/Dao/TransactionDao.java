package ci.ada_bank.Dao;

import ci.ada_bank.Dao.Singletton.DatabaseConnection;
import ci.ada_bank.Modeles.Compte;
import ci.ada_bank.Modeles.Enumeration.TransactionType;
import ci.ada_bank.Modeles.Transaction;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {

    private final static String INSERT_TRANSACTION = "INSERT INTO transactionbancaire (montant, typetransaction, compteid, datetransaction, description) VALUES (?, ?, ?, ?, ?)";

    private final static String SELECT_TRANSACTION_BY_DATE = "SELECT * FROM transactionbancaire WHERE datetransaction = ?";
    private final static String SELECT_TRANSACTION_BY_MONTANT = "SELECT * FROM transactionbancaire WHERE montant = ?";
    private final static String SELECT_ALL_TYPES_TRANSACTION = "SELECT DISTINCT typetransaction FROM transactionbancaire";

    private final static String READ_ALL_TRANSACTION = "SELECT * FROM transactionbancaire t JOIN compte c ON t.compteid = c.id";
    private final static String READ_BY_TYPE = "SELECT * FROM transactionbancaire WHERE type = ?";
    private final static String READ_BY_DATE = "SELECT * FROM transactionbancaire WHERE date >= ? AND date <= ?";
    private final static String READ_BY_MONTANT = "SELECT * FROM transaction WHERE amount >= ? AND amount <= ?";

    private final static String UPDATE_TRANSACTION = "UPDATE transactionbancaire SET montant = ?, typetransaction = ?, compteid = ?, datetransaction = ?, description = ? WHERE id = ?";
    private final static String DELETE_TRANSACTION = "DELETE FROM transactionbancaire WHERE id = ?";

    private static final Connection connection = DatabaseConnection.getInstance().getConnection();
    private static final CompteDao compteDao = new CompteDao();


    public Transaction createTransaction(Transaction transaction) {

        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_TRANSACTION);

            statement.setBigDecimal(1, transaction.getMontant());
            statement.setString(2, String.valueOf(transaction.getTypeTransaction()));
            statement.setLong(3, transaction.getCompte().getId());
            statement.setTimestamp(4, Timestamp.valueOf(transaction.getDateTransaction()));
            statement.setString(5, transaction.getDescription());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transaction;
    }

    public List<Transaction> findAll() {

        List<Transaction> transactions = new ArrayList<>();

        try {
            PreparedStatement stat = connection.prepareStatement(READ_ALL_TRANSACTION);
            ResultSet rs = stat.executeQuery();
            {

                while (rs.next()) {
                    Transaction transaction = new Transaction();
                    Compte compte = compteDao.readById(rs.getLong("id"));
                    transaction.setMontant(rs.getBigDecimal("montant"));
                    transaction.setTypeTransaction(TransactionType.valueOf(rs.getString("typetransaction")));
                    transaction.setCompte(compte);
                    transaction.setDateTransaction(String.valueOf(rs.getObject("datetransaction", LocalDateTime.class)));
                    transaction.setDescription(rs.getString("description"));

                    transactions.add(transaction);
                }


                return transactions;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Transaction> findByType(TransactionType type) throws SQLException {
        PreparedStatement stat = connection.prepareStatement(READ_BY_TYPE);
        ResultSet rs = stat.executeQuery();
        List<Transaction> transactions = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(READ_BY_TYPE)) {

            ps.setString(1, type.name());
            ResultSet rsa = ps.executeQuery();
            while (rsa.next()) {
                // ... mapping Transaction
                Transaction transaction = new Transaction();
                // set infos...
                transaction.setMontant(rs.getBigDecimal("montant"));
                transaction.setTypeTransaction(TransactionType.valueOf(rs.getString("typetransaction")));
                transaction.getCompte().setId(rs.getLong("compteid"));
                transaction.setDateTransaction(String.valueOf(rs.getObject("datetransaction", LocalDateTime.class)));
                transaction.setDescription(rs.getString("description"));

                transactions.add(transaction);
            }
        }
        return transactions;
    }

    public List<Transaction> findByDate(LocalDateTime startDate, LocalDateTime endDate) throws SQLException {

        PreparedStatement stat = connection.prepareStatement(READ_BY_DATE);
        ResultSet rs = stat.executeQuery();

        List<Transaction> transactions = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(READ_BY_DATE)) {

            ps.setTimestamp(1, Timestamp.valueOf(startDate));
            ps.setTimestamp(2, Timestamp.valueOf(endDate));
            ResultSet rsa = ps.executeQuery();

            while (rsa.next()) {
                // ... mapping Transaction
                Transaction transaction = new Transaction();
                // set infos...
                transaction.setMontant(rs.getBigDecimal("montant"));
                transaction.setTypeTransaction(TransactionType.valueOf(rs.getString("typetransaction")));
                transaction.getCompte().setId(rs.getLong("compteid"));
                transaction.setDateTransaction(String.valueOf(rs.getObject("datetransaction", LocalDateTime.class)));
                transaction.setDescription(rs.getString("description"));

                transactions.add(transaction);
            }
        }
        return transactions;
    }

    public List<Transaction> findByMontant(BigDecimal minMontant, BigDecimal maxMontant) throws SQLException {

        PreparedStatement stat = connection.prepareStatement(READ_BY_MONTANT);
        ResultSet rs = stat.executeQuery();

        List<Transaction> transactions = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(READ_BY_MONTANT)) {
            ps.setBigDecimal(1, minMontant);
            ps.setBigDecimal(2, maxMontant);

            while (rs.next()) {
                // ... mapping Transaction
                Transaction transaction = new Transaction();
                // set infos...
                transaction.setMontant(rs.getBigDecimal("montant"));
                transaction.setTypeTransaction(TransactionType.valueOf(rs.getString("typetransaction")));
                transaction.getCompte().setId(rs.getLong("compteid"));
                transaction.setDateTransaction(String.valueOf(rs.getObject("datetransaction", LocalDateTime.class)));
                transaction.setDescription(rs.getString("description"));

                transactions.add(transaction);

            }

        }

        return transactions;
    }

}
