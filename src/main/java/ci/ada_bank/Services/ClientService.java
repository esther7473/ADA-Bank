package ci.ada_bank.Services;

import ci.ada_bank.Dao.ClientDao;
import ci.ada_bank.Modeles.Banque;
import ci.ada_bank.Modeles.Client;
import ci.ada_bank.Modeles.builders.banqueBuilder.BanqueBuilderImpl;
import ci.ada_bank.Modeles.builders.clientBuilder.ClientBuilderImpl;
import ci.ada_bank.util.Menu;
import ci.ada_bank.util.Display;

import java.util.List;
import java.util.Scanner;

public class ClientService {

    public ClientDao clientDao ;
    public BanqueService banqueService ;
    public ClientBuilderImpl clientBuilder = new ClientBuilderImpl();
    public BanqueBuilderImpl banqueBuilder = new BanqueBuilderImpl();
    //public Menu menu = new Menu();


    public ClientService(ClientDao clientDao,BanqueService banqueService){
        this.clientDao = clientDao ;
        this.banqueService = banqueService ;

    }


    public void handleClientMenu(Scanner scanner){
        String choix = scanner.nextLine();
        switch(choix){
            case "1":{registerClient(scanner); break;}
            case "2":{
                getAllClients(scanner);
                break;
            }

            case "4":{
                System.out.println("Entrez le nom de la banque :");
                String nom = scanner.nextLine();
                try {
                    Banque banque = banqueBuilder.setNom(nom).build();
                    System.out.println(banque);
                    getAllClientsByBankId(banque);
                }catch (NullPointerException e){
                    System.out.println("Erreur !");
                }

                break;
            }
            case "5":{break;}
            default:
                System.out.println("Choix invalide.");
        }
    }
    public void registerClient(Scanner scanner) {
        System.out.println("====== VEUILLEZ SAISIR LES INFORMATIONS DU CLIENT ======");

        System.out.print(" NOM : >>> ");
        String nom = scanner.nextLine();

        System.out.print(" PRENOM : >>> ");
        String prenom = scanner.nextLine();

        System.out.print(" EMAIL : >>> ");
        String email = scanner.nextLine();

        System.out.print(" NUMERO DE TELEPHONE : >>> ");
        String num = scanner.nextLine();

        System.out.print(" A QUELLE BANQUE APPARTIENT-IL ? : >>> ");
        String nomBanque = scanner.nextLine();

        try {
            // Vérification si la banque existe
            Banque banqueTrouvee = banqueService.getBanqueByNom(
                    banqueBuilder.setNom(nomBanque.toUpperCase()).build()
            );

            if (banqueTrouvee == null) {
                System.out.println("Banque introuvable. Impossible d'enregistrer le client.");
                return; // On arrête la méthode ici
            }

            // Création du client
            Client client = clientBuilder
                    .setNumeroClient(num)
                    .setNom(nom)
                    .setEmail(email)
                    .setPrenom(prenom)
                    .setBanque(banqueTrouvee)
                    .build();

            Client clientCree = clientDao.createClient(client);

            String emailBody =
                    "Bonjour M/Mme " + client.getNom() + ",\n\n" +
                            "Bienvenue chez Bank ADA ! 🎉\n" +
                            "Nous sommes ravis de vous compter parmi nos clients et de vous accompagner dans vos projets financiers.\n\n" +
                            "Votre compte est désormais actif et prêt à être utilisé.\n" +
                            "Vous recevrez sous peu un mail de confirmation de l'ouverture de votre compte.\n\n" +
                            "Merci pour votre confiance et bienvenue dans la famille Bank ADA ❤️\n" +
                            "L'équipe Bank ADA";
            try {
                EmailService.sendEmail(clientCree.getEmail(),"Bienvenu chez ADA Bank",emailBody);
                System.out.println(" Client enregistré avec succès !");
            }catch (Exception e){
                throw new RuntimeException(e);
            }

        } catch (Exception e) {
            System.out.println(" Erreur lors de l'enregistrement du client : " + e.getMessage());
            e.printStackTrace(); // utile pour le debug
        }
    }





    public List<Client> searchClientByNom(String nom){
        List<Client> clients = clientDao.searchClientByNom(nom);
        for (Client client : clients){
            client.setBanque(banqueService.getBankById(client.getBanque()));
        }

        return clients;
    }
    public Client searchClientByEmail(String email){
        try {
            Client client = clientDao.searchClientByEmail(email);
            client.setBanque(banqueService.getBankById(client.getBanque()));
            return client;
        }catch (NullPointerException e){System.out.println("element nul ! :"+e);return null;}


    }
    public Client searchClientByDateNumero(String numeroclient){
        try {
            Client client = clientDao.searchClientByNumero(numeroclient);
            System.out.println(client.getBanque().getId());
            client.setBanque(banqueService.getBankById(client.getBanque()));
            return client;
        }catch (NullPointerException e){System.out.println("element nul ! :"+e);return null;}

    }

    public void getAllClients(Scanner scanner) {
        System.out.println("====== LISTE DES CLIENTS ======");
        Display.displayTable(clientDao.getAllClients());

        Menu.sousMenuFiltreClient();
        String choixFiltre = scanner.nextLine();

        switch (choixFiltre) {
            case "1":
                System.out.println("Entrez le nom :");
                String nom = scanner.nextLine();
                Display.displayTable(searchClientByNom(nom));
                break;

            case "2":
                System.out.println("Entrez l'email :");
                String email = scanner.nextLine();
                Client c = searchClientByEmail(email);
                if (c != null) {
                    Display.displayTable(List.of(c));
                } else {
                    System.out.println("Aucun client trouvé.");
                }
                break;

            case "3":
                System.out.println("Entrez le numéro de client :");
                String numero = scanner.nextLine();
                Client c2 = searchClientByDateNumero(numero);
                if (c2 != null) {
                    Display.displayTable(List.of(c2));
                } else {
                    System.out.println("Aucun client trouvé.");
                }
                break;

            case "4":
                // Filtre par banque
                System.out.println("Entrez le nom de la banque :");
                Banque banque = banqueBuilder.setNom(scanner.nextLine()).build();
                getAllClientsByBankId(banque);
                break;

            case "5":
                // Retour
                break;

            default:
                System.out.println("Choix invalide.");
        }
    }
    public void getAllClientsByBankId(Banque banque){
        System.out.println("====== LISTE DES CLIENTS DE LA BANQUE "+banque.getNom().toUpperCase()+" ======");
        Display.displayTable(clientDao.getAllClientsByBankId(banque));

    }


}
