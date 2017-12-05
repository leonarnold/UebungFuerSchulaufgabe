package com.sabel.ufsa;

import java.sql.*;

public class UserService {

    private Connection connection;
    private PreparedStatement preparedStatementSelect, preparedStatementInsert;
    private static String URL = "jdbc:sqlite:d:\\benuetzer.sqlite";


    public UserService() throws SQLException {
        this.connection = DriverManager.getConnection(URL);
        this.preparedStatementSelect = connection.prepareStatement("Select name, password FROM benuetzer");
        this.preparedStatementInsert = connection.prepareStatement("INSERT INTO benuetzer(name, password) VALUES (?,?)");
    }

    public void userEinfuegen(User user) throws SQLException {
        preparedStatementInsert.setString(1, user.getName());
        preparedStatementInsert.setString(2, user.getPassword());
        preparedStatementInsert.execute();
    }

    public User userLesen() throws SQLException {
        ResultSet resultSet = preparedStatementSelect.executeQuery();
        while (resultSet.next()){
            String name = null;
            String password = null;

            name = resultSet.getString(1);
            password = resultSet.getString(2);

            return new User(name, password);
        }
        return null;
    }

}
