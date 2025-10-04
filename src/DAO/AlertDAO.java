package DAO;

import Entity.FraudAlert;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AlertDAO {
    private Connection connection;

    public  AlertDAO(Connection connection){
        this.connection=connection;
    }

    public  void  saveAlerte(FraudAlert fraudAlert){
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO fraud_alert(description,alert_level,card_id)" +
                        " values (?,?,?)")){

            preparedStatement.setString(1,fraudAlert.description());
            preparedStatement.setString(2,fraudAlert.level().name());
            preparedStatement.setInt(3,fraudAlert.cardId());

            preparedStatement.executeUpdate();
            System.out.println("Alert Added succesfuly");

        }catch (Exception e ){
            System.out.println(e.getMessage());
        }

    }
}
