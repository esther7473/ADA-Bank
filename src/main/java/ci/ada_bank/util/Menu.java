package ci.ada_bank.util;

import ci.ada_bank.Services.ClientService;
import ci.ada_bank.Services.factory.ServiceFactory;

import java.util.Scanner;

public class Menu {

    private static final ClientService clientService = ServiceFactory.getClientService();
    public Menu(){

    }

    public static boolean mainMenu(Scanner scanner){
        System.out.println("\n========= BIENVENUE DANS ADA BANK =========");
        System.out.println("======----    MENU GENERAL  ----=====");
        System.out.println("1. Gerer les clients ");
        System.out.println("2. Gerer les banques");
        System.out.println("3. Gerer les comptes");
        System.out.println("4. Gerer les tansactions ");
        System.out.println("4. Quitter");
        System.out.print(">>>>");

        switch (scanner.nextLine()){
            case "1":{
                menuClient();
                clientService.handleClientMenu(scanner);
                //return true;
                break;
            }
            case "2":{
                ServiceFactory.getBanqueService().handleBankMenu(scanner);
                break;
            }
            case "3":{
                menuCompte();
                ServiceFactory.getCompteService().handleMenuCompte(scanner);
                break;
            }
            case "4":{
                menuTransaction();
                ServiceFactory.getTransactionService().handleMenuTransac(scanner);
                break;
            }
            case "5":{return false;}

        }
        return true;
    }


    public static void menuClient(){

        System.out.println("======----MENU DE CLIENT----=====");
        System.out.println("1. Inscrire un client dans une banque ");
        System.out.println("2. Afficher la liste des clients");
        //System.out.println("3. Rechercher un client");
        System.out.println("4. Afficher la liste des clients d'une banque");
        System.out.println("5. Quitter");
        System.out.print(">>>> ");


    }

    public static void sousMenuFiltreClient(){

        System.out.println("======----Choisissez un filtre----=====");
        System.out.println("1. Filtrer par nom ");
        System.out.println("2. Filtrer par prenom ");
        System.out.println("3. Filtrer par email");
        System.out.println("4. Filtrer pqr numero");
        System.out.println("5. retour");

        System.out.print(">>>> ");
    }

    public static void menuBanque(){

        System.out.println("======----GESTION DES BANQUES----=====");
        System.out.println("1. Creer une nouvelle banque ");
        System.out.println("2. Visualiser les meilleurs banques ");
        //System.out.println("3. Rechercher une banque");
        System.out.println("3. Liste des banques");
        System.out.println("4. retour");

        System.out.print(">>>> ");
    }

    public static void sousMenuFiltreBanque(){

        System.out.println("======----Choisissez un filtre----=====");
        System.out.println("1. Filtrer par nom ");
        System.out.println("2. Filtrer par pays ");
        System.out.println("3. Filtrer par ville");
       // System.out.println("4. Liste des banques");
        System.out.println("4. retour");

        System.out.print(">>>> ");
    }

    public static void menuTransaction(){

        System.out.println("======----MENU TRANSACTION----=====");
        System.out.println("1. Effectuer un nouveau depot ");
        System.out.println("2. Effectuer un nouveau retrait");
        System.out.println("3. Effectuer un nouveau virement");
        System.out.println("4. Consulter l'historique des transactions");
        System.out.println("5. Quitter");
        System.out.print(">>>> ");
    }

    public static void sousMenuFiltreTransaction(){

        System.out.println("======----MENU TRANSACTION----=====");
        System.out.println("1. Filtrer par type de transaction ");
        System.out.println("2. Filtrer par date");
        System.out.println("3. Filtrer par montant");
        System.out.println("4. Quitter");
        System.out.print(">>>> ");
    }

    public static void menuCompte(){

        System.out.println("======----MENU COMPTE----=====");
        System.out.println("1. Ouvrir un compte ");
        System.out.println("2. Fermer un compte");
        System.out.println("3. Afficher la liste des comptes");
        System.out.println("4. Consulter les informations détaillées d'un compte");
        System.out.println("5. Quitter");
        System.out.print(">>>> ");


    }

}
