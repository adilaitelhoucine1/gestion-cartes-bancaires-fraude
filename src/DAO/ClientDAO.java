package DAO;

import Entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
