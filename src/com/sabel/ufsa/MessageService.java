package com.sabel.ufsa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageService {

    private static String URL = "jdbc:sqlite:d:\\message.sqlite";
    private Connection connection;
    private PreparedStatement preparedStatementInsert;

    public MessageService() throws SQLException {
        this.connection = DriverManager.getConnection(URL);
        this.preparedStatementInsert = connection.prepareStatement("INSERT INTO message(id, text, tstamp) VALUES (?,?,?)");
    }

    public void writeMessage(Message message) throws SQLException {
        preparedStatementInsert.setString(1, message.getText());
        preparedStatementInsert.setLong(2, message.getTimestamp());
        preparedStatementInsert.execute();
    }
}

