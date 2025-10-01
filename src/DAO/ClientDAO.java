package DAO;

import Entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClientDAO {
    private final Connection conn;

    public ClientDAO(Connection conn) {
        this.conn = conn;
    }

    public  void  save(Client client) {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Client (name, email, phone) VALUES (?, ?, ?)"
        )){
            ps.setString(1,client.name());
            ps.setString(2,client.email());
            ps.setString(3,client.phone());
            ps.executeUpdate();
            System.out.println("Tesssssssssssssssssssssssssssssssssst");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public ResultSet listAllClientsWithCards() {
        String sql = "SELECT c.id AS client_id, c.name, c.email, " +
                "GROUP_CONCAT(CONCAT(cr.number, ' (', cr.card_type, ')') SEPARATOR ', ') AS cards " +
                "FROM client c " +
                " JOIN card cr ON c.id = cr.client_id " +
                "GROUP BY c.id, c.name, c.email " ;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
