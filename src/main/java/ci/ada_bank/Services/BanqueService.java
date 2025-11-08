package ci.ada_bank.Services;

import ci.ada_bank.Dao.BanqueDao;
import ci.ada_bank.Modeles.Banque;
import ci.ada_bank.Modeles.builders.banqueBuilder.BanqueBuilderImpl;
import ci.ada_bank.util.Display;
import ci.ada_bank.util.Menu;

import java.util.Scanner;

public class BanqueService {
    public BanqueBuilderImpl banqueBuilder = new BanqueBuilderImpl();
    public BanqueDao banqueDao;

    public BanqueService(BanqueDao banqueDao){
        this.banqueDao=banqueDao;
    }

    public void handleBankMenu(Scanner scanner){
        Menu.menuBanque();

        String choix = scanner.nextLine();
        switch (choix){
            case "1":{
                createBank(scanner);
                break;
            }
            case "2":{
                displayFifteenBest(scanner);
                break;
            }
            case "3":{
                getAllBank(scanner);
                break;
            }
            case "4":{break;}
            default:{
                System.out.println("La choix est incorrecte");
                break;
            }
        }
    }

    private void getAllBank(Scanner scanner) {
        System.out.println("");
        System.out.println("====== LISTE DES  BANQUES ======");
        Display.displayTable(banqueDao.findAll());

        Menu.sousMenuFiltreBanque();
        String choixFiltre = scanner.nextLine();
        switch (choixFiltre){
            case "1":{
                System.out.println("Entrez le nom: ");
                String nom = scanner.nextLine();
                Display.displayTable(banqueDao.findByNom(nom));
                break;
            }
            case "2":{
                System.out.println("Entrez le pays: ");
                String pays = scanner.nextLine();
                Display.displayTable(banqueDao.findByPays(pays));
                break;
            }
            case "3":{
                System.out.println("Entrez le ville: ");
                String ville = scanner.nextLine();
                Display.displayTable(banqueDao.findByVille(ville));
                break;
            }
            case "4":{
                break;
            }
            default:{
                System.out.println("choix invalide  !");
                break;
            }
        }

    }

    public void createBank(Scanner scanner) {
        System.out.println("====== VEILLEZ SAISIR LES INFORMATIONS DE LA BANQUE  ======");
        System.out.println(" NOM :" );
        System.out.println(" >>>" );
        String nom = scanner.nextLine();

        System.out.println(" PAYS :" );
        System.out.println(" >>>" );
        String pays = scanner.nextLine();

        System.out.println(" VILLE :" );
        System.out.println(" >>>" );
        String ville = scanner.nextLine();

        Banque banque =banqueBuilder.setNom(nom)
                .setPays(pays.toUpperCase())
                .setVille(ville.toUpperCase())
                .build();

        banqueDao.create(banque);

        //******ENVOI DU MAIL HERE

        System.out.println("Banque enregistree !");

    }

    public Banque getBanqueByNom(Banque banque){
        return banqueDao.readByNom(banque);
    }

    public Banque getBankById(Banque banque){
        return banqueDao.readById(banque);
    }

    public void displayFifteenBest(Scanner scanner){
        System.out.println();
        System.out.println("====== LISTE DES 15 meilleures banques ======");

        Display.displayTable(banqueDao.read15Banques());

    }




}
