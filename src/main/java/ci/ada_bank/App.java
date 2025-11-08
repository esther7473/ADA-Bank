package ci.ada_bank;

import ci.ada_bank.Dao.Singletton.DatabaseConnection;
import ci.ada_bank.Modeles.Client;
import ci.ada_bank.Services.BanqueService;
import ci.ada_bank.Services.ClientService;
import ci.ada_bank.Modeles.builders.clientBuilder.ClientBuilderImpl;
import ci.ada_bank.util.Display;
import ci.ada_bank.util.Menu;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{

    public static void main( String[] args )
    {

        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        Scanner sc = new Scanner(System.in);
        Menu menu;
        boolean start = true;

        while(start){

            Display.displayWelcomeSimple();
            start = Menu.mainMenu(sc);

        }

        dbConnection.closeConnection();
    }
}
