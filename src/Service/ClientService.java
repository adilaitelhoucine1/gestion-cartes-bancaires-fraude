package Service;

import DAO.ClientDAO;
import Entity.Client;
import Util.InputValidator;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
        private  ClientDAO clientDAO;

        public ClientService(ClientDAO clientDAO){
            this.clientDAO=clientDAO;
        }


    public void addClient(String name,String email,String phone){
        if(InputValidator.isValidName(name) && InputValidator.isValidEmail(email) && InputValidator.isValidPhone(phone)){
            Client client=new Client(0,name,email,phone);
            clientDAO.save(client);
        }else{
            System.out.println("No validate inputs");
        }
    }

    public  ResultSet listAllCLients(){
        return clientDAO.listAllClientsWithCards();

    }
}
