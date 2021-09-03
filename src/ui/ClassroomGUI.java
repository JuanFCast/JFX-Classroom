package ui;

import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import model.Classroom;
import model.UserAccount;

public class ClassroomGUI {
	//main-Pane
	@FXML
    private BorderPane mainPane;
	
	//login
	@FXML
    private TextField txtUserName;

    @FXML
    private PasswordField passwordField;
    
    //register
    @FXML
    private TextField txtFUsername;

    @FXML
    private TextField txtFUsername1;

    @FXML
    private TextField txtProfilePhoto;

    @FXML
    private RadioButton rbFemale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rbMale;

    @FXML
    private ToggleGroup gender1;

    @FXML
    private RadioButton rbOther;

    @FXML
    private ToggleGroup gender2;

    @FXML
    private CheckBox software;

    @FXML
    private CheckBox telematic;

    @FXML
    private CheckBox industrial;

    @FXML
    private DatePicker birthday;

    @FXML
    private ComboBox<String> browser;
    
    
    //account-list
    @FXML
    private Label labUserName;
    
    @FXML
    private TableView<UserAccount> tableAccList;

    @FXML
    private TableColumn<UserAccount, String> colUserName;

    @FXML
    private TableColumn<UserAccount, String> colGender;

    @FXML
    private TableColumn<UserAccount, String> colCareer;

    @FXML
    private TableColumn<UserAccount, String> colBirthday;

    @FXML
    private TableColumn<UserAccount, String> colBrowser;
    
    
	
	private Classroom classroom;
	
	
	public ClassroomGUI(Classroom cr) {
		this.classroom=cr;
		
	}
    
	public void initializeComBox() {
		ObservableList<String> options = FXCollections.observableArrayList("Google Chrome","Mozilla Firefox","Opera GX", "Safari", "Microsoft Edge");
		browser.setValue("Choose an option");
		browser.setItems(options);
	}
    
	//login
    @FXML
    public void logIn(ActionEvent event) throws IOException {
    	if(txtUserName.getText().equals("") && passwordField.getText().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Validacion de error");
    		alert.setHeaderText(null);
    		alert.setContentText("Diligencie por favos los campos del usuario y contraseña ;3");

    		alert.showAndWait();
		}
		else if(classroom.findAccount(txtUserName.getText(), passwordField.getText())){
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));

			fxmlLoader.setController(this);
	    	Parent accList= fxmlLoader.load();
			mainPane.getChildren().clear();
			mainPane.setCenter(accList);
			labUserName.setText(txtUserName.getText());
			File file= new File(classroom.profilePhotoResource(txtUserName.getText(), passwordField.getText()));
			Image image = new Image(file.toURI().toString());
			ImageView imageView=new ImageView(image);
			imageView.setFitHeight(100);
			imageView.setFitWidth(150);
			labUserName.setGraphic(imageView);
			labUserName.setContentDisplay(ContentDisplay.RIGHT);
			initializeTableView();
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Acceso denegado");
    		alert.setHeaderText(null);
    		alert.setContentText("El nombre de usuario y la contraseña proporcionados son incorrectos ");

    		alert.showAndWait();
		}
		
		txtUserName.clear();
    	passwordField.clear();
    }
    
    public void initializeTableView() {
    	ObservableList<UserAccount> list= FXCollections.observableArrayList(classroom.getAccounts());
    	
    	tableAccList.setItems(list);
    	colUserName.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("userName"));
    	colGender.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("gender"));
    	colCareer.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("career"));
    	colBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("birthday"));
    	colBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("browser"));
	
    	
	}
    
    
	
	
    @FXML
	public void loadRegister(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
	
		fxmlLoader.setController(this);
		Parent register= fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(register);
		
		initializeComBox();
	}

    
    
    
    
    
    //register
    @FXML
    public void createAccount(ActionEvent event) {
    	String gend="";
		if(rbFemale.isSelected()) {
			gend="Female";
		}
		else if(rbMale.isSelected()) {
			gend="Male";
		}
		else if(rbOther.isSelected()) {
			gend="Other";
		}
		
		String career="";
		if(software.isSelected()) {
			career+="Software engineering\n";
		}
		if(telematic.isSelected()) {
			career+="Telematic engineering\n";
		}
		if(industrial.isSelected()) {
			career+="Industrial engineering\n";
		}
		
    	
    	if(!txtUserName.getText().equals("") && !passwordField.getText().equals("") && !txtProfilePhoto.getText().equals("") && birthday.getValue()!=null && !browser.getSelectionModel().getSelectedItem().equals("Choose an option")&& !gend.equals("") && !career.equals("") ){
	    	
    		
    		
    		classroom.createAccount(txtUserName.getText(), passwordField.getText(),txtProfilePhoto.getText(),gend, career,birthday.getValue(), browser.getSelectionModel().getSelectedItem());
    		
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Cuenta creada");
    		alert.setHeaderText(null);
    		alert.setContentText("Se ha creado una nueva cuenta!" + "\n" + "Bienvenido " + txtUserName.getText() + "!");

    		alert.showAndWait();
    		
    		txtUserName.clear();
        	passwordField.clear();
        	txtProfilePhoto.clear();
        	birthday.setValue(null);
        	browser.setValue("Choose an option");
        	
        	software.setSelected(false);
        	telematic.setSelected(false);
        	industrial.setSelected(false);
        	if(!gend.equals("")) {
        		gender.getSelectedToggle().setSelected(false);
        	}
        	
    	}else {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Acceso denegado");
    		alert.setHeaderText(null);
    		alert.setContentText("Debes completar cada campo en el formulario");

    		alert.showAndWait();
    	}

    }

    

    //register
    @FXML
    public void selectBrowse(ActionEvent event) {
    	FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
    	FileChooser fileChooser= new FileChooser();
    	fileChooser.getExtensionFilters().add(imageFilter);
    	fileChooser.setTitle("Select profile photo");
    	File file= fileChooser.showOpenDialog(null);
    	
    	if(file != null) {
    		txtProfilePhoto.setText(file.getAbsolutePath());
    	}
    }
    
    
    
    //register / account-list
    @FXML
    public void loadLogIn(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));

		fxmlLoader.setController(this);
    	Parent login= fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(login);
    }
    

}
