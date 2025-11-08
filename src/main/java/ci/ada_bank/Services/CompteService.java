package ci.ada_bank.Services;

import ci.ada_bank.Dao.ClientDao;
import ci.ada_bank.Dao.CompteDao;
import ci.ada_bank.Modeles.Client;
import ci.ada_bank.Modeles.Compte;
import ci.ada_bank.Modeles.Enumeration.CompteType;
import ci.ada_bank.Modeles.Enumeration.Statut;
import ci.ada_bank.util.CompteNumberGenerator;
import ci.ada_bank.util.Display;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CompteService {
    private static CompteDao compteDao;
    private static ClientDao clientDao;

    public CompteService(CompteDao compteDao, ClientDao clientDao) {
        CompteService.compteDao = compteDao;
        CompteService.clientDao = clientDao;
    }

    public void handleMenuCompte(Scanner scanner){

        String choix = scanner.nextLine();
        switch (choix){
            case "1":{
                createCompte(scanner);
                break;
            }
            case "2":{
                fermerCompte(scanner);
                break;
            }
            case "3":{
                Display.displayTable(compteDao.findAll());
                break;
            }
            case "4":{
                readCompte(scanner);
                break;
            }
            case "5":{
                break;
            }
            default:{
                System.out.println("Erreur lors de la choix");
            }
        }

    }
    public void createCompte(Scanner scanner){
        Compte compte = new Compte();

        System.out.println("Choisissez le type de compte :");
        System.out.println("1. Courant ");
        System.out.println("2. Epargne ");
        System.out.println("3. Entreprise ");
        String choix = scanner.nextLine();

        switch (choix){
            case "1":{
                compte.setTypeCompte(CompteType.Courant);
                break;
            }
            case "2":{
                compte.setTypeCompte(CompteType.Entreprise);
                break;
            }
            case "3":{
                compte.setTypeCompte(CompteType.Épargne);
                break;
            }
        }
        System.out.println("Entrez l'id du client :");
        String idClient = scanner.nextLine();


        Client client = clientDao.getClientById(Long.parseLong(idClient));

        if (client == null) {
            System.out.println("Aucun client trouvé avec l'ID : " + idClient);
            return;
        }

        compte.setClient(client);

        compte.setNumeroCompte(CompteNumberGenerator.generateCompteNumber(compte.getTypeCompte()));
        compte.setDateOuverture(String.valueOf(LocalDateTime.now()));
        compte.setStatut(Statut.Actif);
        try {
           // System.out.println("id :"+compte.getClient().getNumeroClient());
            compteDao.create(compte);
        }catch (Exception e){
            System.out.println("Impossible de poursuivre la creation :"+e.getMessage());
            throw new RuntimeException(e);
        }
        String emailBody =
                "Bonjour M/Mme " + client.getNom() + "\n\n" +
                        "Votre compte a été créé avec succès.\n" +
                        "Veuillez trouver ci-dessous les informations de votre compte :\n\n" +
                        "NUMÉRO DE COMPTE : " + compte.getNumeroCompte() + "\n" +
                        "TYPE DE COMPTE   : " + compte.getTypeCompte() + "\n" +
                        "STATUT           : " + compte.getStatut().name() + "\n" +
                        "SOLDE            : " + compte.getSolde() + "\n\n" +
                        "Merci pour votre confiance.\n" +
                        "L'équipe Bank ADA";

        try {
            EmailService.sendEmail(compte.getClient().getEmail(),"Creation de Compte ADA Bank",emailBody);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        System.out.println("Compte cree ! ");
  }

    public void readCompte(Scanner scanner){

        System.out.println("Entrez le numero du compte: ");
        String numeroCompte = scanner.nextLine();
        try {
            Compte compte = compteDao.readByNumeroCompte(numeroCompte);
            Client  client = clientDao.getClientById(compte.getClient().getId());
            System.out.println("============= INFOS DU COMPTE "+numeroCompte+" ===============");
            System.out.println("Compte ID : "+compte.getId());
            System.out.println("Solde : "+compte.getId());
            System.out.println("TypeCompte : "+compte.getTypeCompte());
            System.out.println("Date Ouverture : "+compte.getDateOuverture());
            System.out.println("Proprietaire du compte : "+client.getNom()  );
            System.out.println("Statut  : "+compte.getStatut());


        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void fermerCompte(Scanner scanner){
        System.out.println("Entrez le numero du compte: ");
        String numeroCompte = scanner.nextLine();

        try {
            Compte compte = compteDao.readByNumeroCompte(numeroCompte);
            compte.setStatut(Statut.Ferme);
            compteDao.updateById(compte);

            System.out.println("Fermeture du compte "+numeroCompte+" reussie !");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
