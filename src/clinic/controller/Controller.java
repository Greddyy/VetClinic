package controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Pet;
import model.PetDAO;
import model.Client;
import model.ClientDAO;
import utils.Validation;

import java.sql.*;
import java.util.HashMap;

public class Controller  {
    @FXML
    private Button backBtn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginBtn;
    @FXML
    private Label Errors;
    @FXML
    private Label registrationLabel;
    @FXML
    private Button signupButton;
    @FXML
    private ComboBox<String> densityBox;
    @FXML
    private TextField emailRegister;
    @FXML
    private TextField usernameRegister;
    @FXML
    private PasswordField passwordRegister;
    @FXML
    private Button registerBtn;
    @FXML
    private TextField passwordConfirm;
    @FXML
    private Button logOutButton;
    @FXML
    private TableColumn petName;
    @FXML
    private TableColumn petAge;
    @FXML
    private TableColumn petSpeciesField;
    @FXML
    private TableColumn petWeight;
    @FXML
    private TableColumn petGender;
    @FXML
    private TableColumn dogPedigree;
    @FXML
    private TextField petNameField;
    @FXML
    private TextField petWeightField;
    @FXML
    private TextField petGenderField;
    @FXML
    private ComboBox petActivity;
    @FXML
    private CheckBox maleCheckBox;
    @FXML
    private CheckBox femaleCheckBox;
    @FXML
    private CheckBox neuteredCheckBox;
    @FXML
    private RadioButton isPedigree;
    @FXML
    private RadioButton noPedigree;
    @FXML
    private RadioButton undefinedPedigree;
    @FXML
    private Button createButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TableView tableView;
    @FXML
    private Button loadDataFromDatabase;
    @FXML
    private TextField idField;
    @FXML
            private TextField breedField;

    ResultSet rsAllEntries;




    public void handleButtonAction(javafx.event.ActionEvent actionEvent) {
        if(actionEvent.getSource() == loginBtn) {
               login(actionEvent);
        }
        if(actionEvent.getSource() == signupButton) {
            // register();
            StageChanger.switchRegister((Stage)signupButton.getScene().getWindow(), new Registration());
        }
        if(actionEvent.getSource() == backBtn) {
            StageChanger.switchLogin((Stage)backBtn.getScene().getWindow(), new Loging());
        }
        if(actionEvent.getSource() == registerBtn) {
            String email = String.valueOf(emailRegister.getText());
            String username = String.valueOf(usernameRegister.getText());
            String password = String.valueOf(passwordRegister.getText());
            String passConfirm = String.valueOf(passwordConfirm.getText());
            register(email, username, password, passConfirm);
        }
        if(actionEvent.getSource() == logOutButton) {
            StageChanger.switchLogin((Stage)logOutButton.getScene().getWindow(), new Loging());
        }
        if(actionEvent.getSource() == createButton) {
            try {
                String petName = String.valueOf(petNameField.getText());
                int weight = Integer.parseInt(petWeightField.getText());
                String petBreed = String.valueOf(breedField.getText());
                //String gender = String.valueOf(petGenderField.getText());
                //paskirti satellite count
                String physicalActivity = petActivity.getSelectionModel().getSelectedItem().toString();
                boolean male = maleCheckBox.isSelected();
                boolean female = femaleCheckBox.isSelected();
                boolean neutered = neuteredCheckBox.isSelected();
                String gender = gender(male, female, neutered);
                boolean hasPedigree = isPedigree.isSelected();
                boolean noPedigree2 = noPedigree.isSelected();
                boolean undefinedPedigree2 = undefinedPedigree.isSelected();
                String animalPedigree = animalPedigree(hasPedigree, noPedigree2, undefinedPedigree2);
                Pet pet = new Pet(petName, gender, animalPedigree, physicalActivity,petBreed, weight);
                create(pet);
            }
            catch (RuntimeException e) {

            }

//            this.petName = petName;
//            this.gender = gender;
//            this.animalPedigree =getAnimalPedigree();
//            this.animalSpecie = animalSpecie;
//            this.petBreed = petBreed;
//            this.weigt = weigt;

        }
        if(actionEvent.getSource() == deleteButton) {
            String petName = String.valueOf(petNameField.getText());
            delete(petName);
        }
        if(actionEvent.getSource() == loadDataFromDatabase) {
            loadDatafromDB();
        }

    }


    public void exit(MouseEvent event) {
        System.exit(0);
    }
    public String gender(boolean male, boolean female, boolean neutered) {

        String gender = "";
        if (male) {
            gender = "Male";
        }
        else if (female) {
            gender = "Female";
        }
        else if (neutered) {
            gender = "Neutered";
        }
        return  gender;
    }
    public String animalPedigree(boolean isPedigree, boolean noPedigree, boolean undefinedPedigree){
        String animalPedigree = "";
        if (isPedigree) {
            animalPedigree = "Has pedigree";
        }
        else if (noPedigree) {
            animalPedigree = "No pedigree";
        }
        else if (undefinedPedigree) {
            animalPedigree = "Undefined pedigree";
        }
        return  animalPedigree;
    }


    public void dashboard(javafx.event.ActionEvent event){
        StageChanger.switchDashboard((Stage)loginBtn.getScene().getWindow(), new dashboardStage());
    }

    private void login(javafx.event.ActionEvent event) {
        String user = String.valueOf(username.getText());
        String pass = String.valueOf(password.getText());
        int userLength = user.length();
        int passLength = pass.length();

        if(Validation.isValidUsername(user) && Validation.isValidPassword(pass)) {
            ClientDAO clientDAO = new ClientDAO();
            String msg = clientDAO.login(user, pass);
            if(msg.contains("Successful")) {
                dashboard(event);
            }
            else {
                Errors.setText(msg);
            }
        }
        else if(userLength < 5 || userLength > 12) {
            Errors.setText("Username has to be between 5-12 characters!");
        }
        else if(passLength < 5 || passLength > 12) {
            Errors.setText("Password has to be between 5-12 characters!");
        }
        else {
            Errors.setText("Using invalid characters! Only letters and numbers allowed in username.");
        }
    }

    //register dashboard event

    public void register(String email, String username, String password, String passConfirm) {

        if(Validation.isValidEmailAddress(email) && Validation.isValidUsername(username)
                && Validation.isValidPassword(password) && passConfirm.equals(password) ){
            HashMap users = new HashMap();
            users.put(email, new Client(email, username, password));
            //   User user = new User(email, username, password);

            ClientDAO clientDAO = new ClientDAO();
            String msg = clientDAO.register((Client) users.get(email));
            registrationLabel.setText(msg);
        }
        else {
            registrationLabel.setText("Username and password must contain 5-12 characters!\n Or your email is inncorect.");
        }
        if (!passConfirm.equals(password)) { registrationLabel.setText("Password was not confirmed! Try again.");}
        boolean isRegistered = true;
    }

    public void create(Pet pet) {
        HashMap pets = new HashMap();
        pets.put(pet.getPetName(), new Pet(pet.getPetName(), pet.getGender(),
                pet.getAnimalPedigree(), pet.getPhysicalActivity(),
                pet.getPetBreed(), pet.getWeight()));

        PetDAO petDAO = new PetDAO();
        String msg = petDAO.addPet((Pet) pets.get(pet.getPetName()));
        updateTableFromDB();
    }
    public void delete(String petName){
        PetDAO petDAO = new PetDAO();
        petDAO.deletePet(petName);
        updateTableFromDB();
    }



    private void loadDatafromDB() {
        updateTableFromDB();
    }

    public void search(String petName) {
        PetDAO petDAO = new PetDAO();

        try {
            rsAllEntries = petDAO.searchByPetName(petName);
        }
        catch (NullPointerException e) {

        }
        fetchColumnList();
        fetchRowList();
    }


    public void updateTableFromDB() {
        PetDAO petDAO = new PetDAO();

        try {
            rsAllEntries = petDAO.loadTableView();
        }

        catch (NullPointerException e) {

        }
        fetchColumnList();
        fetchRowList();
    }



    //only fetch columns
    private void fetchColumnList() {
        try {
            tableView.getColumns().clear();

            if (rsAllEntries != null) {
                //SQL FOR SELECTING ALL OF CUSTOMER
                for (int i = 0; i < rsAllEntries.getMetaData().getColumnCount(); i++) {
                    //We are using non property style for making dynamic table
                    final int j = i;
                    TableColumn col = new TableColumn(rsAllEntries.getMetaData().getColumnName(i + 1).toUpperCase());
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                            return new SimpleStringProperty(param.getValue().get(j).toString());
                        }
                    });

                    tableView.getColumns().removeAll(col);
                    tableView.getColumns().addAll(col);
                }
            } else {

            }
        } catch (SQLException e) {

        }
    }

    ObservableList<ObservableList> data = FXCollections.observableArrayList();

    //fetches rows and data from the list
    private void fetchRowList() {
        try {
            data.clear();
            if (rsAllEntries != null) {
                while (rsAllEntries.next()) {
                    //Iterate Row
                    ObservableList row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rsAllEntries.getMetaData().getColumnCount(); i++) {
                        //Iterate Column
                        row.add(rsAllEntries.getString(i));
                    }
                    data.add(row);
                }
                //Connects table with list
                tableView.setItems(data);
            } else {

            }
        } catch (SQLException ex) {

        }
    }



}

