package ci.ada_bank.Services.factory;

import ci.ada_bank.Dao.BanqueDao;
import ci.ada_bank.Dao.ClientDao;
import ci.ada_bank.Dao.CompteDao;
import ci.ada_bank.Dao.TransactionDao;
import ci.ada_bank.Modeles.builders.clientBuilder.ClientBuilderImpl;
import ci.ada_bank.Services.BanqueService;
import ci.ada_bank.Services.ClientService;
import ci.ada_bank.Services.CompteService;
import ci.ada_bank.Services.TransactionService;

public class ServiceFactory {

    private static ClientDao clientDao;
    private static BanqueDao banqueDao;
    private static TransactionDao transactionDao;
    private static CompteDao compteDao;
    private static BanqueService banqueService;
    private static ClientService clientService;
    private static TransactionService transactionService;
    private static CompteService compteService;

    public static ClientDao getClientDao() {
        if (clientDao == null) {
            clientDao = new ClientDao();
        }
        return clientDao;
    }
    public static BanqueDao getBanqueDao() {
        if (banqueDao == null) {
            banqueDao = new BanqueDao();
        }
        return banqueDao;
    }
    public static TransactionDao getTransactionDao() {
        if ( transactionDao == null) {
            transactionDao = new TransactionDao();
        }
        return transactionDao;
    }
    public static CompteDao getCompteDao() {
        if ( compteDao == null) {
            compteDao = new CompteDao();
        }
        return compteDao;
    }


    public static BanqueService getBanqueService() {
        if (banqueService == null) {
            banqueService = new BanqueService(getBanqueDao());
        }
        return banqueService;
    }

    public static ClientService getClientService() {
        if (clientService == null) {
            clientService = new ClientService(getClientDao(), getBanqueService());
        }
        return clientService;
    }

    public static TransactionService getTransactionService() {
        if (transactionService == null) {
            transactionService = new TransactionService(getTransactionDao());
        }
        return transactionService;
    }

    public static CompteService getCompteService() {
        if (compteService == null) {
            compteService = new CompteService(getCompteDao(),getClientDao());
        }
        return compteService;
    }
}
