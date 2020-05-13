package clinic.model;

import java.sql.*;

public class PatientDAO {
    final static String URL = "jdbc:mysql://localhost:3306/db";
    public String add(Patient patient){
        String query = "insert into patients (animal_name, species, colour, gender, age, id)" +
                "values (?,?,?,?,?,?)";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, patient.getAnimal_name());
            preparedStatement.setString(2, patient.getSpecies());
            preparedStatement.setString(3, patient.getColour());
            preparedStatement.setString(4, patient.getGender());
            preparedStatement.setInt(5, patient.getAge());
            preparedStatement.setInt(6, patient.getId());
            preparedStatement.executeUpdate();

            return "Successfully created new entry";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failure creating new entry";
        }
    }

    public ResultSet searchByAnimalName(String name, Client client){
        String query2 = "";
        if (client.isDoctor()) { // display all results- it's admin after all ;)
            if (name.equals("")) {//Admin didn't entered any team_name - displaying all entries
                query2 = "SELECT * FROM patients";
            } else {// Admin entered team_name displaying specific entries
                query2 = "SELECT * FROM patients WHERE animal_name LIKE '" + name + "'";
            }
        } else { // display only specific user results
            if (name.equals("")) {//User didn't entered any team_name - displaying all entries created by him
                query2 = "SELECT * FROM patients WHERE user_id = '" + client.getId() + "'";
            } else {// User entered team_name displaying specific entries created by him
                query2 = "SELECT * FROM patients WHERE user_id = '" + client.getId() + "' AND animal_name LIKE '" + name + "'";
            }
        }

        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(URL, "root", "");
            preparedStatement = connection.prepareStatement(query2);
            resultSet = preparedStatement.executeQuery(query2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public void editById(Patient patient){
        String query = "update patients set animal_name=?, species=?, colour=?, gender=?, age=? " +
                " where id=?";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, patient.getAnimal_name());
            preparedStatement.setString(2, patient.getSpecies());
            preparedStatement.setString(3, patient.getColour());
            preparedStatement.setString(4, patient.getGender());
            preparedStatement.setString(5, patient.getGender());
            preparedStatement.setInt(6, patient.getAge());
            preparedStatement.executeUpdate();

            System.out.println("Pavyko paredaguoti esama irasa");
        } catch (SQLException e) {
            System.out.println("Ivyko klaida redaguojant esama irasa");
            e.printStackTrace();
        }
    }
    public void deleteById(int id){
        String query = "delete from patients where id=?";
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            System.out.println("Pavyko istrinti esama irasa");
        } catch (SQLException e) {
            System.out.println("Ivyko klaida istrinant esama irasa");
            e.printStackTrace();
        }
    }
}
