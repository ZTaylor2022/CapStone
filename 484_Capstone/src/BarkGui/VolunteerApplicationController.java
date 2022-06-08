package BarkGui;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class VolunteerApplicationController implements Initializable {

    @FXML
    private ComboBox<String> CBExp;

    @FXML
    private ComboBox<String> CBSpec;

    @FXML
    private Button btnSubmit;

    @FXML
    private DatePicker DOB;

    @FXML
    private TextField txtFName;

    @FXML
    private TextField txtLName;
    
    @FXML
    private Button backBtn;

    ObservableList<String> experienceList = FXCollections.observableArrayList("None", "1-2 years", "3+ years");
    ObservableList<String> specializationsList = FXCollections.observableArrayList("Animal Health Care", "Feeding", "Enclosure Care", "Adoptive Relations", "Event Volunteer");
    //ObservableList<Application> applications = FXCollections.observableArrayList();
    ResultSet rs;
    Statement stmt;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CBExp.setItems(experienceList);
        CBSpec.setItems(specializationsList);
//        String query = "Select * from application";
//        DBConncection conn = new DBConncection();
//        try{
//        conn.sendDBCommand(query);
//        while(conn.dbResults.next()){
//        Application dbApp = new Application();
//        dbApp.setAppID(rs.getInt(1));
//        dbApp.setFName(rs.getString(2));  
//        dbApp.setLName(rs.getString(3));             
//        dbApp.setDOB(rs.getString(4)); 
//        dbApp.setExperience(rs.getString(5)); 
//        dbApp.setSpecialization(rs.getString(6));
//        applications.add(dbApp);
        } //conn.dbResults.close();

   // }catch (SQLException ex){
    //}
    }

//    public void submitApplication() throws SQLException {
//        if(txtFName.getText().isEmpty() || txtLName.getText().isEmpty() || //DOB.getChronology().isNul ||
//                CBExp.getValue().isEmpty())//|| CBSpec.getValue().isEmpty())
//                {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText("Please enter all data");
//            alert.showAndWait();
//        }else{
//            String date = DOB.getValue().format(DateTimeFormatter.ISO_DATE);
//        System.out.println(txtFName.getText() + " " + txtLName.getText() + " " + date + " " + CBExp.getValue() + " " + CBSpec.getValue());
//        Application submittedApp = new Application(
//                txtFName.getText(),
//                txtLName.getText(),
//                date,
//                CBExp.getValue(),
//                CBSpec.getValue()
//                
//        );applications.add(submittedApp);
//        String query = "INSERT INTO APPLICATION (ApplicationID,AFirstName,ALastName,DOB,experience,specialization) VALUES (" + Application.appCount + ", '"
//                + txtFName.getText() + "', '" + txtLName.getText() + "', '" + date + "', '" + CBExp.getValue() + "', '"
//                + CBSpec.getValue() + "')";
//        DBConncection conn = new DBConncection();
//        conn.sendDBCommand(query);
//        
//        txtFName.setText("");
//        txtLName.setText("");
//        DOB.setChronology(null);
//        CBExp.setValue("");
//        CBSpec.setValue("");
//        }
//}}

