package com.example.kpp7;

import com.example.kpp7.Models.LibraryModel;
import com.example.kpp7.Models.LibraryViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    ObservableList<LibraryViewModel> list = FXCollections.observableArrayList();
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
    private TableColumn<LibraryViewModel, String> colName;
    @FXML
    private TableColumn<LibraryViewModel, String> colPriority;
    @FXML
    private TableColumn<LibraryViewModel, String> colState;
    @FXML
    private TableColumn<LibraryViewModel, String> colTimeStamp;
    @FXML
    private TableView<LibraryViewModel> table;

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
                Integer.parseInt(threadsNum.getText()),
                this
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
        LibraryViewModel dataUnit = table.getSelectionModel().getSelectedItem();
        library.suspendAThread(dataUnit.getId());
        dataUnit.setState(library.getState(dataUnit.getId()));
    }

    @FXML
    protected void onTerminateThreadButtonClick() {
        LibraryViewModel dataUnit = table.getSelectionModel().getSelectedItem();
        library.killAThread(dataUnit.getId());
        dataUnit.setState(library.getState(dataUnit.getId()));
    }

    @FXML
    protected void onResumeThreadButtonClick() {
        LibraryViewModel dataUnit = table.getSelectionModel().getSelectedItem();
        library.resumeAThread(dataUnit.getId());
        dataUnit.setState(library.getState(dataUnit.getId()));
    }


    @Override
    public void initialize(URL ur, ResourceBundle rs){
        //set up columns
        colName.setCellValueFactory(new PropertyValueFactory<LibraryViewModel, String>("name"));
        colPriority.setCellValueFactory(new PropertyValueFactory<LibraryViewModel, String>("priority"));
        colState.setCellValueFactory(new PropertyValueFactory<LibraryViewModel, String>("state"));
        colTimeStamp.setCellValueFactory(new PropertyValueFactory<LibraryViewModel, String>("timeStamp"));



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

        list.add(new LibraryViewModel("Name1", "111", "running",  29));
        list.add(new LibraryViewModel("Name2", "222", "running", 30));
        list.add(new LibraryViewModel("Name3", "3333", "running", 42));

    }

    public void addThread(String name, String priority, String state, Integer id){
        table.getItems().add(new LibraryViewModel(name, priority, state, id));
    }
    public void addThread(LibraryViewModel thread){
        table.getItems().add(thread);
    }

    /***
     * Method to call when a cell Name is double-clicked
     * @param editedCell cell that is being edited
     */
    public void ChangeNameCellEvent(TableColumn.CellEditEvent editedCell){
        LibraryViewModel dataUnit = table.getSelectionModel().getSelectedItem();
        dataUnit.setName(editedCell.getNewValue().toString());
    }

    public void ChangeNameCell(String newName){
//        table.getItems().get(1).setName(newName);
        LibraryViewModel dataUnit = table.getItems().get(0);
        dataUnit.setName(newName);
    }

    public void UpdateThreadStatus(Integer id,String status){
        LibraryViewModel dataUnit = table.getItems().get(id);
        dataUnit.setState(status);
    }


}