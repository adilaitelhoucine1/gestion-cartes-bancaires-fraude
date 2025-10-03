package DAO;

import Entity.CardOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            preparedStatement.setString(2, LocalDateTime);
            preparedStatement.setString(1, LocalDateTime);
            preparedStatement.setString(1, LocalDateTime);
            preparedStatement.setString(1, LocalDateTime);


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public Double getSumOperationPerToday(int cardID) {
        String sql = "SELECT SUM(amount) as total FROM card_operation WHERE card_id = ? AND DATE(operation_date) = CURDATE()";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cardID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double amountTotal = resultSet.getDouble("total");
                if (resultSet.wasNull()) {
                    return 0.0;
                }
                return amountTotal;
            }
            return 0.0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Double getSumOperationPerMonth(int cardID) {
        String sql = "SELECT SUM(amount) AS total " +
                "FROM card_operation " +
                "WHERE card_id = ? " +
                "AND YEAR(operation_date) = YEAR(CURDATE()) " +
                "AND MONTH(operation_date) = MONTH(CURDATE())";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cardID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double amountTotal = resultSet.getDouble("total");
                if (resultSet.wasNull()) {
                    return 0.0;
                }
                return amountTotal;
            }
            return 0.0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
