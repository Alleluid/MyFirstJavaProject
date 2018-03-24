package com.alleluid.alleiminders;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static javafx.application.Platform.exit;


public class MainWindow extends Application{
    //Construct layout objects
    private VBox vBoxRoot = new VBox();
    private HBox hBoxMain = new HBox();
    private TreeView tree = new TreeView();
    private TableView table = new TableView();
    private MenuBar menuBar = new MenuBar();
    private AnchorPane bottomPane = new AnchorPane();

    //Init table's data list
    public ObservableList<Entry> tableData = FXCollections.observableArrayList();

//********************************************************************************************************************
    /**
     * Displays About dialog for general and versioning information.
     */
    private void onShowAboutAlert(){
        String contentText=
                "Alleiminders by Alleluid"+System.lineSeparator()+
                "Version PRE-ALPHA"; //TODO add proper version var
        Alert alert=new Alert(Alert.AlertType.INFORMATION,contentText,ButtonType.CLOSE);
        alert.setHeaderText("About Alleiminders");
        alert.show();
    }
//*********************************************[Main Method]**********************************************************
    public static void main(String [] args){
        launch(args);
    }

//********************************************************************************************************************
    @Override //defines app elements
    public void start(Stage stage){
        Scene scene = new Scene(new Group());
        stage.setTitle("Hello World!");
        stage.setWidth(1000);
        stage.setHeight(600);

        onLoadFromFile();
        /*tableData.addAll(
                new Entry("Tomorrow Thing", LocalDate.now(),LocalTime.parse("16:04"), "wooo"),
                new Entry("Next Week Thing", "woooooo"),
                new Entry("Next Month Thing", LocalDate.parse("2018-04-06"), "wooooooooooo asda s dasd asdasd asasdasd asdasdasda asdasdasd asdasdasdas asdasdasdasd asdasdasdasd asdasdasdasd wrwer"),
                new Entry(),
                new Entry("Name", LocalDate.now(), LocalTime.now(), "notes")

        ); */
        table.setItems(tableData);

        //init button
        Button button1 = new Button("New Entry");
        button1.setAlignment(Pos.CENTER);
        button1.setPadding(new Insets(20));
        button1.setOnAction(event -> addNewEntry());

        //init tree
        tree.setPrefWidth(250);

        //init table
        table.setTableMenuButtonVisible(true);
        table.setPrefWidth(730);
        table.setEditable(true);
        table.setOnMouseClicked((MouseEvent event) -> onTableClicked());
//**********************************************************************************************************************
        TableColumn labelCol = new TableColumn("Label");
        TableColumn dueDateCol = new TableColumn("Due Date");
        TableColumn dueTimeCol = new TableColumn("Time");
        TableColumn notesCol = new TableColumn("Notes");

        labelCol.setId("label");
        dueDateCol.setId("dueDate");
        dueTimeCol.setId("dueTime");
        notesCol.setId("notes");
        notesCol.setMaxWidth(500);

        labelCol.setCellValueFactory(
                new PropertyValueFactory<>("labelString")
        );
        dueDateCol.setCellValueFactory(
                new PropertyValueFactory<>("dueDateString")
        );
        dueTimeCol.setCellValueFactory(
                new PropertyValueFactory<>("dueTimeString")
        );
        notesCol.setCellValueFactory(
                new PropertyValueFactory<>("notesString")
        );

        labelCol.setCellFactory(TextFieldTableCell.forTableColumn());
        labelCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Entry, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Entry, String> event) {
                    ((Entry) event.getTableView().getItems().get(
                            event.getTablePosition().getRow())
                    ).setLabelString(event.getNewValue());

                }
            });
        dueDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dueDateCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Entry, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Entry, String> event) {
                    Entry entry = ((Entry) event.getTableView().getItems().get(event.getTablePosition().getRow()));
                    entry.parseDueDate(event.getNewValue());


                }
            });
        dueTimeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dueTimeCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Entry, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Entry, String> event) {
                    ((Entry) event.getTableView().getItems().get(event.getTablePosition().getRow())).parseDueTime(event.getNewValue());

                }
            });
        notesCol.setCellFactory(TextFieldTableCell.forTableColumn());
        notesCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<Entry, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<Entry, String> event) {
                    ((Entry) event.getTableView().getItems().get(event.getTablePosition().getRow())).setNotesString(event.getNewValue());

                }
            });




        table.getColumns().addAll(labelCol,dueDateCol,dueTimeCol,notesCol);

//**********************************************************************************************************************
        //init menu bar
        Menu menuFile = new Menu();
        Menu menuEdit = new Menu();
        Menu menuHelp = new Menu();

        //init menu items
        ////File
        MenuItem menuItemClose = new MenuItem();
        MenuItem menuItemSave = new MenuItem();
        MenuItem menuItemLoad = new MenuItem();

        ////Edit
        MenuItem menuItemDelete = new MenuItem();

        ////Help
        MenuItem menuItemAbout = new MenuItem();
        MenuItem menuItemDebug = new MenuItem();

        //setup menu bar
        menuFile.setText("File");
        menuFile.getItems().addAll(menuItemClose);
        menuFile.getItems().addAll(menuItemSave);
        menuFile.getItems().addAll(menuItemLoad);

        menuEdit.setText("Edit");
        menuEdit.getItems().addAll(menuItemDelete);

        menuHelp.setText("Help");
        menuHelp.getItems().addAll(menuItemAbout, menuItemDebug);

        //setup menu items
        menuItemClose.setText("Close");
        menuItemClose.setOnAction(event -> exit());

        menuItemSave.setText("Save");
        menuItemSave.setOnAction(event -> onSaveToFile());

        menuItemLoad.setText("Load");
        menuItemLoad.setOnAction(event -> onLoadFromFile());

        menuItemDelete.setText("Delete");
        menuItemDelete.setOnAction(event -> deleteFocusedRow());

        menuItemAbout.setText("About");
        menuItemAbout.setOnAction(event -> onShowAboutAlert());

        menuItemDebug.setText("~Debug~"); //TEMP
        menuItemDebug.setOnAction(event -> fillDebugData());


        menuBar.getMenus().addAll(menuFile,menuEdit,menuHelp);
        menuBar.setMaxWidth(-1);
        menuBar.setPrefWidth(Integer.MAX_VALUE); //FIXME this is probably bad

        //init root VBox
        vBoxRoot.getChildren().addAll(menuBar, hBoxMain, bottomPane);

        //init main HBox
        hBoxMain.getChildren().addAll(tree,table);

        //init bottom anchor pane
        bottomPane.getChildren().addAll(button1);



        ((Group) scene.getRoot()).getChildren().addAll(vBoxRoot);
        stage.setScene(scene);
        stage.show();
    }

//*********************************************[Action Events]**********************************************************
    private void fillDebugData(){
        System.out.println("Filling with some data");
        tableData.addAll(
                new Entry("Tomorrow Thing", LocalDate.now(),LocalTime.parse("16:04"), "wooo"),
                new Entry("Next Week Thing", "woooooo"),
                new Entry("Next Month Thing", LocalDate.parse("2018-04-06"), "wooooooooooo asda s dasd asdasd asasdasd asdasdasda asdasdasd asdasdasdas asdasdasdasd asdasdasdasd asdasdasdasd wrwer"),
                new Entry(),
                new Entry("Name", LocalDate.now(), LocalTime.now(), "notes"),
                new Entry(),
                new Entry(),
                new Entry());
    }

    private void addNewEntry(){
        System.out.println("Adding row");
        tableData.add(new Entry());
    }

    private void onTableClicked(){
        EventHandler<MouseEvent> mouseEvent = (MouseEvent event) -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                /*
                int focusedRowInt = table.getFocusModel().getFocusedCell().getRow();
                TableColumn focusedColObj = table.getFocusModel().getFocusedCell().getTableColumn();
                if (focusedColObj == null) {
                    return;
                }
                Entry focusedEntry = (Entry) table.getSelectionModel().getSelectedItem();
                System.out.println(focusedRowInt);
                System.out.println(focusedEntry);
                table.edit(focusedRowInt, focusedColObj);
                //TextFieldTableCell tfcell = new TextFieldTableCell();
                */
            }
        };
        //table.sort();
    }
    private void debugPrintInfo(){
        System.out.println(tableData.get(0).getLabelString());
    }
    private void deleteFocusedRow(){
        if (table.getSelectionModel().isEmpty()){return;}
        int focusedRowInt = table.getFocusModel().getFocusedCell().getRow();
        tableData.remove(focusedRowInt);
        System.out.println("deleteFocusedRow method called for: "+focusedRowInt);
    }
    private void onSaveToFile(){
        ArrayList<Entry> dataList = new ArrayList<>(tableData) ;
        MyFuncs.serializeArrayList(dataList);
    }
    private void onLoadFromFile(){
        ArrayList<Entry> dataList = (ArrayList<Entry>)MyFuncs.deserializeArrayList();
        tableData.remove(0,tableData.size());
        tableData.addAll(dataList);
        table.autosize();
    }
}
