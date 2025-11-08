package ci.ada_bank.Services;

import ci.ada_bank.Dao.TransactionDao;
import ci.ada_bank.Modeles.Compte;
import ci.ada_bank.Modeles.Enumeration.TransactionType;
import ci.ada_bank.Modeles.Transaction;
import ci.ada_bank.Modeles.TransactionDTO;
import ci.ada_bank.Services.strategies.DepotStrategy;
import ci.ada_bank.Services.strategies.RetraitStrategy;
import ci.ada_bank.Services.strategies.TransactionStrategy;
import ci.ada_bank.Services.strategies.VirementStrategy;
import ci.ada_bank.util.Display;
import ci.ada_bank.util.Menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionService {

    private static  TransactionDao transactionDao;
    public TransactionService(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public void handleMenuTransac(Scanner scanner){
        String choix = scanner.nextLine();
        switch (choix){
            case "1":{
                makeOperation(TransactionType.Depot,scanner);
                break;
            }
            case "2":{
                makeOperation(TransactionType.Retrait,scanner);
                break;
            }
            case "3":{
                makeVirement(scanner);
                break;
            }
            case "4":{
                getHistoriqueTransactions();
                break;
            }
            case "5":{break;}
            default:{
                System.out.println("La choix est incorrecte");
                break;
            }
        }

    }

    public static void makeOperation(TransactionType transactionType, Scanner scanner){
        System.out.println(" numero du compte client: ");
        String numCompte = scanner.nextLine();

        System.out.println(" montant: ");
        String montantStr = scanner.nextLine();
        BigDecimal montant = new BigDecimal(montantStr);

        Transaction transaction = new Transaction();
        TransactionStrategy strategy = null;
        switch (transactionType){
            case Retrait: {
                strategy = new RetraitStrategy();
                break;
            }
            case Depot: {
                strategy = new DepotStrategy();
                break;
            }
            default: System.out.println("la strategy est nulle");
        }
        transaction.setTransactionStrategy(strategy);
        transaction.makeTransaction(strategy, montant,numCompte);


    }

    public static void makeVirement( Scanner scanner){
        System.out.println(" numero du compte auteur du virement: ");
        String numCompteSender = scanner.nextLine();
        System.out.println(" numero du compte beneficaire: ");
        String numCompteReceiver = scanner.nextLine();

        System.out.println(" montant: ");
        String montantStr = scanner.nextLine();
        BigDecimal montant = new BigDecimal(montantStr);

        Compte sender = new Compte();
        sender.setNumeroCompte(numCompteSender);


        Transaction transaction = new Transaction();
        transaction.setCompte(sender);
        TransactionStrategy strategy = new VirementStrategy();
        transaction.setTransactionStrategy(strategy);
        transaction.makeTransaction(strategy, montant,numCompteReceiver);
    }

    public static void getHistoriqueTransactions(){
        List<Transaction> transactions = transactionDao.findAll();

        List<TransactionDTO> transactionsMap = transactions.stream()
                .map(t -> new TransactionDTO(
                        t.getId(),
                        t.getMontant(),
                        t.getTypeTransaction(),
                        t.getCompte() != null ? t.getCompte().getNumeroCompte() : null,
                        t.getDateTransaction(),
                        t.getDescription()
                ))
                .toList();

        Display.displayTable(transactionsMap);
    }


}

