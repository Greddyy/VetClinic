package model;

import utils.Constant;

import java.sql.*;

public class ClientDAO {

    // login funkcija RETURNS message
    public  String login(String username, String password) {
        ResultSet resultSet = null;

        String msg="";
        String query = "SELECT * FROM " + Constant.TABLE +
                " WHERE username = ? AND password = ?";

        try {
            Connection connection = DriverManager.getConnection(Constant.URL + Constant.DB_NAME, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                msg = "Successful login!";
            }
            else {
                msg = "Wrong username or password.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msg;
    }
    // register function RETURNS message

    public String register(Client client) {
        String query = "INSERT INTO " + Constant.TABLE + "(email, username, password) VALUES (?, ?, ?)";
        String msg = "";
        ResultSet resultSet = null;

        try {
            Connection connection = DriverManager.getConnection(Constant.URL + Constant.DB_NAME, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, client.getEmail());
            preparedStatement.setString(2, client.getUsername());
            preparedStatement.setString(3, client.getPassword());
            preparedStatement.executeUpdate();
            msg = "Client successfully created!";

        } catch (SQLException e) {
            e.printStackTrace();
            msg = "Failed creating new client!";
        }
        return msg;
    }
}

