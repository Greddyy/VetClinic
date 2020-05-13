package clinic.model;

import clinic.utils.Constants;

import java.sql.*;

public class ClientDAO {
    public String login(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String msg = "";
        try {
            connection = DriverManager.getConnection(Constants.URL + Constants.DB_NAME, "root", "");

            preparedStatement = connection.prepareStatement("SELECT  * FROM  " + Constants.TABLE_NAME + " Where username = ? AND password = ?");

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                msg = "Successful login";
            } else {
                msg = "No user found under these credentials";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return msg;
    }

    public Client getClient(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        boolean isDoctor = false;
        Client client = null;
        try {
            connection = DriverManager.getConnection(Constants.URL + Constants.DB_NAME, "root", "");

            preparedStatement = connection.prepareStatement("SELECT * FROM " + Constants.TABLE_NAME + " Where username = ?");

            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username2 = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                boolean admin = resultSet.getBoolean("doctor");
                //int id, String username, String password, String email, boolean admin
                client = new Client (id, username2, password, email, admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

}
