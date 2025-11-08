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

public class RetraitStrategy implements TransactionStrategy{
    private TransactionDao transactionDao = new TransactionDao();
    private CompteDao compteDao = new CompteDao();

    @Override
    public void makeTransaction(Transaction transaction, String numeroCompte, BigDecimal montant) {
        try {

            Compte compte = compteDao.readByNumeroCompte(numeroCompte);
            try {
                compte.setSolde(compte.getSolde().subtract(montant));
                compte = compteDao.updateById(compte);


               // Transaction transaction = new Transaction();
                transaction.setCompte(compte);
                transaction.setMontant(montant);
                transaction.setTypeTransaction(TransactionType.Retrait);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Ou "yyyy-MM-dd HH:mm:ss.SSSSSS" si vous voulez les micro/nanosecondes
                String formattedDateTime = LocalDateTime.now().format(formatter);
                transaction.setDateTransaction(formattedDateTime);

                transactionDao.createTransaction(transaction);

                String emailBody =
                        "Bonjour M/Mme " + compte.getClient().getNom() + ",\n\n" +
                                "Nous vous confirmons que votre dépôt a été effectué avec succès.\n\n" +
                                "Voici le récapitulatif de l'opération :\n\n" +
                                "TYPE D'OPÉRATION : Dépôt\n" +
                                "MONTANT          : " + montant + " FCFA\n" +
                                "NUMÉRO DE COMPTE : " + compte.getNumeroCompte() + "\n" +
                                "DATE             : " + transaction.getDateTransaction() + "\n" +
                                "SOLDE ACTUEL     : " + compte.getSolde() + " FCFA\n\n" +
                                "Merci pour votre confiance.\n" +
                                "L'équipe Bank ADA";

                EmailService.sendEmail(compte.getClient().getEmail(),"Facture Depot",emailBody);

                System.out.println("operation reussie !");

            }catch (NullPointerException e){
                System.out.println("impossible de recuperer le compte  ! :" + e.getLocalizedMessage());
            }
        } catch (Exception e) {
            System.out.println("L'operation a échoué  ! :" + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }
}
