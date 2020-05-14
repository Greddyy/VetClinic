package model;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import utils.Constant;

import java.sql.*;

public class PetDAO {

    @FXML
    private TableColumn<Pet, String> petName;
    @FXML
    private TableColumn<Pet, String> animalSpecie;
    @FXML
    private TableColumn<Pet, Integer> petGender;
    @FXML
    private TableColumn<Pet, Integer> petWeight;
    @FXML
    private TableColumn<Pet, String> composition;
    @FXML
    private TableColumn<Pet, String> animalPedigree;
    @FXML
    private TableView<Pet> tableView;

    public String addPet(Pet pet) {
        String query = "INSERT INTO " + Constant.TABLE_PETS + "(petName, gender, animalPedigree," +
                " physicalActivity, PetBreed , weight)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        String msg = "";

        try {
            Connection connection = DriverManager.getConnection(Constant.URL + Constant.DB_NAME, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pet.getPetName());
            preparedStatement.setString(2, pet.getGender());
            preparedStatement.setString(3, pet.getAnimalPedigree());
            preparedStatement.setString(4, pet.getPhysicalActivity());
            preparedStatement.setString(5, pet.getPetBreed());
            preparedStatement.setInt(6, pet.getWeight());
            preparedStatement.executeUpdate();
            msg = "Pet added!";
        }
        catch (SQLException e) {
            e.printStackTrace();
            msg = "Failed creating new pet entry!";
        }
        return msg;
    }

    public void deletePet(String petName) {
        String query = "DELETE FROM " + Constant.TABLE_PETS + " WHERE (petName = ?)";
        try {
            Connection connection = DriverManager.getConnection(Constant.URL + Constant.DB_NAME, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, petName);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public ResultSet searchByPetName(String petName) {
        String query = "";
        query = "SELECT * FROM " + Constant.TABLE_PETS + " WHERE name LIKE '" + petName + "'";
        ResultSet resultSet = null;
        try {
            Connection connection = DriverManager.getConnection(Constant.URL + Constant.DB_NAME, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    ObservableList<Pet> data;

    public ResultSet loadTableView(){
        String query2 = "SELECT * FROM pets";

        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = DriverManager.getConnection(Constant.URL + Constant.DB_NAME, "root", "");
            preparedStatement = connection.prepareStatement(query2);
            resultSet = preparedStatement.executeQuery(query2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }




}
