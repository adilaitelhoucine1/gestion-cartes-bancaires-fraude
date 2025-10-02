package DAO;

import Entity.Card;
import Entity.CreditCard;
import Entity.DebitCard;
import Entity.PrepaidCard;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CardDAO {
    private Connection connection;

    public CardDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveDebitCard(DebitCard debitCard) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Card (number,expiration_date,card_type,client_id,daily_limit)" +
                        " values (?,?,'DEBIT',?,?)"
        )) {
            preparedStatement.setString(1, debitCard.getNumber());
            preparedStatement.setDate(2, Date.valueOf(debitCard.getExpirationDate()));
            //preparedStatement.setString(3,debitCard.getStatus().name());
            preparedStatement.setInt(3, debitCard.getClientId());
            preparedStatement.setDouble(4, debitCard.getDailyLimit());
            preparedStatement.executeUpdate();
            System.out.println("Card added Sucessfuly");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveCreditCard(CreditCard creditCard) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Card (number,expiration_date,card_type,client_id,monthly_limit,interest_rate)" +
                        "values (?,?,'CREDIT',?,?,?)"

        )) {
            preparedStatement.setString(1, creditCard.getNumber());
            preparedStatement.setDate(2, Date.valueOf(creditCard.getExpirationDate()));
            // preparedStatement.setString(3,creditCard.getStatus().name());
            preparedStatement.setInt(3, creditCard.getClientId());
            preparedStatement.setDouble(4, creditCard.getMonthlyLimit());
            preparedStatement.setDouble(5, creditCard.getInterestRate());
            preparedStatement.executeUpdate();
            System.out.println("Card added Sucessfuly");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void savePrepaidCard(PrepaidCard prepaidCard) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Card (number,expiration_date,card_type,client_id,available_balance)" +
                        "values (?,?,'PREPAID',?,?)"

        )) {
            preparedStatement.setString(1, prepaidCard.getNumber());
            preparedStatement.setDate(2, Date.valueOf(prepaidCard.getExpirationDate()));
            // preparedStatement.setString(3,creditCard.getStatus().name());
            preparedStatement.setInt(3, prepaidCard.getClientId());
            preparedStatement.setDouble(4, prepaidCard.getAvailableBalance());
            preparedStatement.executeUpdate();
            System.out.println("Card added Sucessfuly");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Card>  getAllCards(){
        List<Card> cards=new ArrayList<>();
        try(PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM  card")){
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                int cardID = resultSet.getInt("id");
                String cardNumber=resultSet.getString("number");
                LocalDate cardDate = resultSet.getDate("expiration_date").toLocalDate();
                int clientId = resultSet.getInt("client_id");
                String cardStatus = resultSet.getString("status");

                Card card = new Card(cardID, cardDate, Card.CardStatus.ACTIVE, clientId);
                cards.add(card);
            }
            return  cards;


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return  cards;
    }
}
