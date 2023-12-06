package com.example.kpp7;

import com.example.kpp7.Models.LibraryModel;
import com.example.kpp7.Models.Tempclass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellEditor;

import java.net.URL;
import java.security.Timestamp;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    ObservableList<Tempclass> list = FXCollections.observableArrayList();
    LibraryModel library;

    @FXML
    public Button helloButton;
    @FXML
    public Button startThreadsButton;
    @FXML
    public TextField threadsNum;
    @FXML
    public TextField booksNum;
    @FXML
    public Button suspendButton;
    @FXML
    public Button resumeButton;
    @FXML
    public Button terminateButton;
    @FXML
    private Label welcomeText;
    @FXML
    private TableColumn<Tempclass, String> colName;
    @FXML
    private TableColumn<Tempclass, String> colPriority;
    @FXML
    private TableColumn<Tempclass, String> colState;
    @FXML
    private TableColumn<Tempclass, String> colTimeStamp;
    @FXML
    private TableView<Tempclass> table;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        table.getItems().get(1).setId(7);
        ChangeNameCell("new name");
//        Tempclass dataUnit = table.getItems().get(0);
//        dataUnit.setId(7);
    }

    @FXML
    protected void onStartThreadsButtonClick() {
        library = new LibraryModel(
                Integer.parseInt(booksNum.getText()),
                Integer.parseInt(threadsNum.getText())
        );
        for(int i = 0; i < Integer.parseInt(threadsNum.getText()); i++){
            addThread(library.constructView(i));
        }
        //get dummy data
//        getData();
//        table.setItems(list);

    }

    @FXML
    protected void onSuspendThreadButtonClick() {
        Tempclass dataUnit = table.getSelectionModel().getSelectedItem();
        library.suspendAThread(dataUnit.getId());
        dataUnit.setState(library.getState(dataUnit.getId()));
    }

    @FXML
    protected void onTerminateThreadButtonClick() {
        Tempclass dataUnit = table.getSelectionModel().getSelectedItem();
        library.killAThread(dataUnit.getId());
        dataUnit.setState(library.getState(dataUnit.getId()));
    }

    @FXML
    protected void onResumeThreadButtonClick() {
        Tempclass dataUnit = table.getSelectionModel().getSelectedItem();
        library.resumeAThread(dataUnit.getId());
        dataUnit.setState(library.getState(dataUnit.getId()));
    }


    @Override
    public void initialize(URL ur, ResourceBundle rs){
        //set up columns
        colName.setCellValueFactory(new PropertyValueFactory<Tempclass, String>("name"));
        colPriority.setCellValueFactory(new PropertyValueFactory<Tempclass, String>("priority"));
        colState.setCellValueFactory(new PropertyValueFactory<Tempclass, String>("state"));
        colTimeStamp.setCellValueFactory(new PropertyValueFactory<Tempclass, String>("timeStamp"));



        //update columns to be editable
        table.setEditable(true);
        colName.setEditable(true);
        colPriority.setEditable(true);
        colState.setEditable(true);
        colTimeStamp.setEditable(true);

        colName.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    /***
     * Method to populate table with dummy data of dummy class
     */
    public void getData(){

        list.add(new Tempclass("Name1", "111", "running",  29));
        list.add(new Tempclass("Name2", "222", "running", 30));
        list.add(new Tempclass("Name3", "3333", "running", 42));

    }

    public void addThread(String name, String priority, String state, Integer id){
        table.getItems().add(new Tempclass(name, priority, state, id));
    }
    public void addThread(Tempclass thread){
        table.getItems().add(thread);
    }

    /***
     * Method to call when a cell Name is double-clicked
     * @param editedCell cell that is being edited
     */
    public void ChangeNameCellEvent(TableColumn.CellEditEvent editedCell){
        Tempclass dataUnit = table.getSelectionModel().getSelectedItem();
        dataUnit.setName(editedCell.getNewValue().toString());
    }

    public void ChangeNameCell(String newName){
//        table.getItems().get(1).setName(newName);
        Tempclass dataUnit = table.getItems().get(0);
        dataUnit.setName(newName);
    }


}