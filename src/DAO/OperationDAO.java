package DAO;

import Entity.CardOperation;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OperationDAO {

    private Connection connection;

    public OperationDAO(Connection connection) {
        this.connection = connection;
    }

    public void addOperation(CardOperation cardOperation) {


        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO card_operation (operation_date,amount,operation_type,location,card_id)" +
                        " values (?,?,?,?,?)")) {
            LocalDateTime now = LocalDateTime.now();

            preparedStatement.setTimestamp(1, Timestamp.valueOf(now));
            preparedStatement.setDouble(2, cardOperation.amount());
            preparedStatement.setString(3, cardOperation.type().name());
            preparedStatement.setString(4, cardOperation.location());
            preparedStatement.setInt(5, cardOperation.cardId());

            preparedStatement.executeUpdate();
            System.out.println("New Operation Added");
        } catch (Exception e) {
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

    public List<CardOperation> getAllOperations(int cardId) {
        List<CardOperation> cardOperations = new ArrayList<>();
        String sql = "SELECT *  FROM card_operation  where card_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,cardId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Timestamp ts = resultSet.getTimestamp("operation_date");
                LocalDateTime operationDate = ts.toLocalDateTime();
                Double amount = resultSet.getDouble("amount");
                String typeStr = resultSet.getString("operation_type");
                // converts String -> Enum
                CardOperation.OperationType type = CardOperation.OperationType.valueOf(typeStr);


                String location = resultSet.getString("location");

                CardOperation cardOperation = new CardOperation(id, operationDate, amount, type, location, cardId);

                cardOperations.add(cardOperation);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cardOperations;
    }


}
