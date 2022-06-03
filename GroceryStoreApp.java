package HW1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import oracle.jdbc.pool.OracleDataSource;
import static javafx.application.Application.launch;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class GroceryStoreApp extends Application {

    ResultSet rsSalaries;
    ResultSet rsEmployees;
    Statement statement;
    Statement statement1;
    Statement statement2;
    Connection con;

    //creates text fields to be place on the gridpane
    TextField tfFirstName = new TextField();
    TextField tfLastName = new TextField();
    TextField tfEmpID = new TextField();
    TextField tfSalary = new TextField();
    TextField tfSupplier = new TextField();

    // Creates drop down boxes to be place on the gridpane
    ComboBox<String> cboProducts = new ComboBox<>();
    ComboBox<String> cboSupplier = new ComboBox<>();
    ComboBox<String> cboStoreArea = new ComboBox<>();

    // Creates labels to be place on the gridpane next to the text fields
    Label lblEmpID = new Label("Emp ID: ");
    Label lblFirstName = new Label("First Name: ");
    Label lblLastName = new Label("Last Name: ");
    Label lblSalary = new Label("Salary: ");
    Label lblSupplier = new Label("Supplier: ");
    Label lblProducts = new Label("Products: ");
    Label lblStoreArea = new Label("Store Area: ");
    Label lblErrorDescription = new Label();

    // Pulls images to be place on the gridpane next to its corresponding button
    Image img = new Image("images/save.gif");
    ImageView view = new ImageView(img);
    Image img1 = new Image("images/left.gif");
    ImageView view1 = new ImageView(img1);
    Image img2 = new Image("images/right.gif");
    ImageView view2 = new ImageView(img2);
    Image img3 = new Image("images/chart.jpg");
    ImageView view3 = new ImageView(img3);

    // Creates buttons to be place on the gridpane
    Button btnPrevious = new Button("Previous");
    Button btnNext = new Button("Next");
    Button btnShowChart = new Button("View Inventory Report");
    Button btnUpdateEmp = new Button("Update Report");

    // Connects to the orcale database
    static Connection conOracle(String id, String pw) throws Exception {
        String connectionString = "jdbc:oracle:thin:@localhost:1521:XE";
        OracleDataSource ds = new OracleDataSource();   // use of OracleDriver is from this class
        ds.setURL(connectionString);
        return ds.getConnection(id, pw);
    }

    static Connection conSQLServer(String db) throws Exception {
        String connectionString = "jdbc:sqlserver://localhost;databaseName=" + db
                + ";integratedSecurity=true;";
        SQLServerDriver s = new SQLServerDriver();
        return s.connect(connectionString, null);
    }

//    void productsInfo() {
//        // Creates and sets stage
//        Stage stage = new Stage();
//        stage.setTitle("Inventory Chart");
//
//        final CategoryAxis xAxis = new CategoryAxis();
//        final NumberAxis yAxis = new NumberAxis();
//        final BarChart<String, Number> bc;
//        bc = new BarChart<>(xAxis, yAxis);
//        bc.setTitle("Salaries by Department");
//        xAxis.setLabel("Department");
//        yAxis.setLabel("Salary");
//        XYChart.Series s1 = new XYChart.Series();
//        XYChart.Series s2 = new XYChart.Series();
//        XYChart.Series s3 = new XYChart.Series();
//        s1.setName("Maximum");
//        s2.setName("Average");
//        s3.setName("Minimum");
//
//        try {
//            statement2 = con.createStatement();
//            String query = " select MAX(salary) as Maximum, AVG(Salary) as Average, MIN(Salary) as Minimum, departments.departmentname from employees left join departments on employees.departmentid=departments.departmentid group by departments.departmentname order by departments.departmentname";
//            rsSalaries = statement2.executeQuery(query);
//
//            while (rsSalaries.next()) {
//                String department = rsSalaries.getString(4);
//                if (department == null) {
//                    department = "Unassigned";
//                }
//                s1.getData().add(new XYChart.Data(department, rsSalaries.getInt(1)));
//                s2.getData().add(new XYChart.Data(department, rsSalaries.getInt(2)));
//                s3.getData().add(new XYChart.Data(department, rsSalaries.getInt(3)));
//
//            }
//        } catch (SQLException e) {
//            System.out.println("Error: " + e.toString());
//        }
//        Scene scene = new Scene(bc, 900, 700);
//        bc.getData().addAll(s1, s2, s3);
//        stage.setScene(scene);
//        stage.show();
//    }

      void updateEmployee() {
        try {
            statement1 = con.createStatement();
            String updateProducts = cboProducts.getValue();
            String updateStoreArea = cboStoreArea.getValue();
            int index = cboStoreArea.getItems().indexOf(updateStoreArea) + 1;
            String updateFirst = tfFirstName.getText();
            String updateLast = tfLastName.getText();
            String updateEmpID = tfEmpID.getText();
            String updateSalary = tfSalary.getText();
            String query = "Update employees set products = '" + updateProducts + "', storearea = '" + index + "',firstname = '" + updateFirst + "', lastname ='" + updateLast + "', salary = '" + updateSalary + "' where employeeid = '" + updateEmpID + "'";
            statement1.executeUpdate(query);
            String query1 = "select empid, empfname, emplname, salary, from employees";
            rsEmployees = statement.executeQuery(query1);
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
      }

    void prevEmployee() {
        try {
            rsEmployees.previous();
            tfEmpID.setText(rsEmployees.getString(1));
            tfFirstName.setText(rsEmployees.getString(2));
            tfLastName.setText(rsEmployees.getString(3));
            tfSalary.setText(rsEmployees.getString(6));
            cboProducts.setValue(rsEmployees.getString(5));
            cboStoreArea.setValue(rsEmployees.getString(6));
        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }

    }

    void employeeNames() {
        try {
            rsEmployees.next();
            tfEmpID.setText(rsEmployees.getString(1));
            tfFirstName.setText(rsEmployees.getString(2));
            tfLastName.setText(rsEmployees.getString(3));
            tfSalary.setText(rsEmployees.getString(4));
//            cboJobTitle.setValue(rsEmployees.getString(5));
//            cboDepartment.setValue(rsEmployees.getString(6));

        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    @Override //overrides the start method in the application class and begins to construct components in the gui display
    public void start(Stage primaryStage) throws SQLException {
        try {
            GridPane pane = new GridPane();
            pane.setAlignment(Pos.CENTER);
            pane.setPadding(new Insets(15, 15, 15, 15));
            pane.setHgap(5.5);
            pane.setVgap(5.5);
            pane.add(lblEmpID, 0, 0);
            pane.add(tfEmpID, 1, 0);
            pane.add(lblFirstName, 0, 1);
            pane.add(tfFirstName, 1, 1);
            pane.add(lblLastName, 0, 2);
            pane.add(tfLastName, 1, 2);
            pane.add(lblSalary, 0, 3);
            pane.add(tfSalary, 1, 3);
            pane.add(lblSupplier, 0, 4);
            pane.add(tfSupplier, 1, 4); 
            pane.add(lblProducts, 0, 5);
            pane.add(cboProducts, 1, 5); 
            pane.add(lblStoreArea, 0, 6);
            pane.add(cboStoreArea, 1, 6); 
            pane.add(btnUpdateEmp, 0, 7);
            pane.add(btnPrevious, 0, 8);
            pane.add(btnNext, 1, 8);
            pane.add(btnShowChart, 2, 8);
            pane.add(lblErrorDescription, 0, 9);
            btnUpdateEmp.setGraphic(view);
            btnPrevious.setGraphic(view1);
            btnNext.setGraphic(view2);
            btnShowChart.setGraphic(view3);

            //Displays screen for pane
            Scene scene = new Scene(pane, 700, 400);
            primaryStage.setTitle("Grocery Store App");
            primaryStage.setScene(scene);
            primaryStage.show();

            // connects to oracle database and logs in with pre determined username and password "emp"
            String connectionString = "jdbc:oracle:thin:@localhost:1521:XE";
            OracleDataSource ds = new OracleDataSource();   // use of OracleDriver is from this class
            ds.setURL(connectionString);
            con = ds.getConnection("javauser", "javapass");
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rsProducts = statement.executeQuery("select distinct prodname from inventory");
            while (rsProducts.next()) {
                cboProducts.getItems().add(rsProducts.getString(1));
            }
            ResultSet rsStoreArea = statement.executeQuery("select storeid, areaname from storearea");
            while (rsStoreArea.next()) {
                cboStoreArea.getItems().add(rsStoreArea.getString(2));
            }
            String query = "select EmpId, EmpFName, EmpLName, Salary, from EMPLOYEES";
            rsEmployees = statement.executeQuery(query);
            rsEmployees.next();
            tfEmpID.setText(rsEmployees.getString(1));
            tfFirstName.setText(rsEmployees.getString(2));
            tfLastName.setText(rsEmployees.getString(3));
            tfSalary.setText(rsEmployees.getString(4));


            btnNext.setOnAction(e -> employeeNames());
            btnPrevious.setOnAction(e -> prevEmployee());
            btnUpdateEmp.setOnAction(e -> updateEmployee());
//            btnShowChart.setOnAction(e -> salariesInfo());

        } catch (SQLException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    public static void main(String[] args) {
        launch(args);

    }
}
    
