package DAO;

import Entity.CardOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class OperationDAO {

    private Connection connection;

    public  OperationDAO(Connection connection){
        this.connection=connection;
    }

    public  void cardOperation(CardOperation cardOperation){


        try(PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO card_operation (operation_date,amount,operation_type,location,card_id)" +
                        " values (?,?,?,?,?)")){
            preparedStatement.setString(1, LocalDateTime);


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
