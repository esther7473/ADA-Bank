package ci.ada_bank.Services.strategies;

import ci.ada_bank.Dao.CompteDao;
import ci.ada_bank.Dao.TransactionDao;
import ci.ada_bank.Modeles.Compte;
import ci.ada_bank.Modeles.Enumeration.TransactionType;
import ci.ada_bank.Modeles.Transaction;
import ci.ada_bank.Services.EmailService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class VirementStrategy implements TransactionStrategy{
    private final TransactionDao transactionDao = new TransactionDao();
    private final CompteDao compteDao = new CompteDao();


    public void makeVirement(String receiverNum,String senderNum, BigDecimal montant){
        try {
            Compte sender = compteDao.readByNumeroCompte(senderNum);
            Compte receiver = compteDao.readByNumeroCompte(receiverNum);

            sender.setSolde(sender.getSolde().subtract(montant));
            sender = compteDao.updateById(sender);

            receiver.setSolde(receiver.getSolde().add(montant));
            receiver = compteDao.updateById(receiver);

            Transaction transaction = new Transaction();
            transaction.setCompte(sender);
            transaction.setMontant(montant);
            transaction.setTypeTransaction(TransactionType.Virement);
            transaction.setDateTransaction(String.valueOf(LocalDateTime.now()));

            transactionDao.createTransaction(transaction);

            System.out.println("operation reussie !");


        } catch (Exception e) {
            System.out.println("L'operation a échoué  ! :"+e.getLocalizedMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void makeTransaction(Transaction transaction, String receiverNum, BigDecimal montant) {
        try {
            Compte sender = transaction.getCompte();
            sender = compteDao.readByNumeroCompte(sender.getNumeroCompte());

            Compte receiver = compteDao.readByNumeroCompte(receiverNum);

            sender.setSolde(sender.getSolde().subtract(montant));
            sender = compteDao.updateById(sender);

            receiver.setSolde(receiver.getSolde().add(montant));
            receiver = compteDao.updateById(receiver);

            transaction.setCompte(sender);
            transaction.setTypeTransaction(TransactionType.Virement);
            transaction.setDescription("compte beneficiaire du virement");
            transaction.setMontant(montant);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Ou "yyyy-MM-dd HH:mm:ss.SSSSSS" si vous voulez les micro/nanosecondes
            String formattedDateTime = LocalDateTime.now().format(formatter);
            transaction.setDateTransaction(formattedDateTime);

            transactionDao.createTransaction(transaction);


            Transaction transactionReceiver = new Transaction();
            transactionReceiver.setCompte(receiver);
            transactionReceiver.setMontant(montant);
            transactionReceiver.setDescription("compte auteur du virement");
            transactionReceiver.setTypeTransaction(TransactionType.Virement);
            transactionReceiver.setDateTransaction(formattedDateTime);
            transactionDao.createTransaction(transactionReceiver);

            System.out.println("operation reussie !");
            String emailBodySender = " Bonjour M/Mme "+sender.getClient().getNom()+" Votre virement sur le compte "+receiverNum+" a ete effectue.";
            EmailService.sendEmail(sender.getClient().getEmail(),"Facture Virement",emailBodySender);

            String emailBodyReceiver = " Bonjour M/Mme "+sender.getClient().getNom()+" Vous avez recu un virement de "+montant+" du compte N :"+transaction.getCompte().getNumeroCompte();
            EmailService.sendEmail(receiver.getClient().getEmail(),"Facture Virement",emailBodyReceiver);

        } catch (Exception e) {
            System.out.println("L'operation a échoué  ! :"+e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }
}
