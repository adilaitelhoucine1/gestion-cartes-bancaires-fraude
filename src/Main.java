import DAO.CardDAO;
import DAO.ClientDAO;
import Db.DatabaseConnection;
import Service.CardService;
import Service.ClientService;
import UI.MainMenu;

import java.sql.*;

public class Main  {
    public static void main(String[] args) {

        try( Connection connection= DatabaseConnection.getConnection()) {

            ClientDAO clientDAO = new ClientDAO(connection);
            CardDAO cardDAO= new CardDAO(connection);
            CardService cardService = new CardService(cardDAO);
            ClientService clientService = new ClientService(clientDAO);
            MainMenu mainMenu=new MainMenu(clientService,cardService);
            mainMenu.display();
        }catch (SQLException e) {
            System.out.println(" Failed to connect: " + e.getMessage());
        }


    }
}
