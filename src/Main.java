import DAO.AlertDAO;
import DAO.CardDAO;
import DAO.ClientDAO;
import DAO.OperationDAO;
import Db.DatabaseConnection;
import Service.CardService;
import Service.ClientService;
import Service.FraudService;
import Service.OperationService;
import UI.MainMenu;
import Util.OperationRules;

import java.sql.*;

public class Main  {
    public static void main(String[] args) {



        try( Connection connection= DatabaseConnection.getConnection()) {

            ClientDAO clientDAO = new ClientDAO(connection);
            CardDAO cardDAO= new CardDAO(connection);
            AlertDAO alertDAO= new AlertDAO(connection);
            CardService cardService = new CardService(cardDAO);
            ClientService clientService = new ClientService(clientDAO);
            OperationDAO operationDAO=new OperationDAO(connection);
            FraudService fraudService=new FraudService(cardDAO,operationDAO,alertDAO);
            OperationRules operationRules= new OperationRules(operationDAO,cardDAO);
            OperationService operationService=new OperationService(operationDAO,cardService,operationRules);
            MainMenu mainMenu=new MainMenu(clientService,cardService,operationService,fraudService);
            mainMenu.display();
        }catch (SQLException e) {
            System.out.println(" Failed to connect: " + e.getMessage());
        }


    }
}
