package pizzashop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import  javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.scene.text.FontWeight;
import pizzashop.gui.OrdersGUI;
import pizzashop.service.PizzaService;

import java.util.HashMap;
import java.util.Map;

import static javafx.scene.paint.Color.DARKBLUE;

public class MainGUIController  {
    @FXML
    private Button table1;
    @FXML
    private Button table2;
    @FXML
    private Button table3;
    @FXML
    private Button table4;
    @FXML
    private Button table5;
    @FXML
    private Button table6;
    @FXML
    private Button table7;
    @FXML
    private Button table8;
    @FXML
    private MenuItem help;

    private final Map<Integer, OrdersGUI> tableOrdersMap = new HashMap<>();

    PizzaService service;

    public MainGUIController(){}

    public void setService(PizzaService service){
        this.service=service;
        tableHandlers();
    }

    private void tableHandlers() {
        Button[] tableButtons = {table1, table2, table3, table4, table5, table6, table7, table8};

        for (int i = 0; i < tableButtons.length; i++) {
            int tableNumber = i + 1;
            tableOrdersMap.put(tableNumber, new OrdersGUI());

            tableButtons[i].setOnAction(event -> {
                OrdersGUI tableOrders = tableOrdersMap.get(tableNumber);
                tableOrders.setTableNumber(tableNumber);
                tableOrders.displayOrdersForm(service);
            });
        }
    }


//    private void tableHandlers(){
//        table1.setOnAction(event -> {
//            table1Orders.setTableNumber(1);
//            table1Orders.displayOrdersForm(service);
//        });
//        table2.setOnAction(event -> {
//            table2Orders.setTableNumber(2);
//            table2Orders.displayOrdersForm(service);
//        });
//        table3.setOnAction(event -> {
//            table3Orders.setTableNumber(3);
//            table3Orders.displayOrdersForm(service);
//        });
//        table4.setOnAction(event -> {
//            table4Orders.setTableNumber(4);
//            table4Orders.displayOrdersForm(service);
//        });
//        table5.setOnAction(event -> {
//            table5Orders.setTableNumber(5);
//            table5Orders.displayOrdersForm(service);
//        });
//        table6.setOnAction(event -> {
//            table6Orders.setTableNumber(6);
//            table6Orders.displayOrdersForm(service);
//        });
//        table7.setOnAction(event -> {
//            table7Orders.setTableNumber(7);
//            table7Orders.displayOrdersForm(service);
//        });
//        table8.setOnAction(event -> {
//            table8Orders.setTableNumber(8);
//            table8Orders.displayOrdersForm(service);
//        });
//
//    }

    public void initialize(){

        help.setOnAction((ActionEvent event) -> {
            Stage stage = new Stage();

            stage.setTitle("How to use..");
            final Group rootGroup = new Group();
            final Scene scene = new Scene(rootGroup, 600, 250);
            final Text text1 = new Text(
                    25, 25,
                    "1. Click on a Table button - a table order form will pop up. "+ System.lineSeparator()
                            +System.lineSeparator()+
                            "2.Choose a Menu item from the menu list, choose Quantity from drop down list, " +  System.lineSeparator()
                            +"press Add to order button and Click on the Menu list (Repeat)" + System.lineSeparator()
                            +System.lineSeparator()+
                            "3.Press Place order button, the order will appear in the Kitchen window"+ System.lineSeparator()
                            +System.lineSeparator()+
                            "4.On the Kitchen window Click on the order in the Orders List and Press the Cook button, " + System.lineSeparator()
                            +"then after Click on the order in the Orders list and then on the Ready button"+ System.lineSeparator()
                            +System.lineSeparator()+
                            "5.On the Table order form press the Order served button, at the end press" + System.lineSeparator()
                            +"the Pay order button "+ System.lineSeparator()
            );

            text1.setFont(Font.font(java.awt.Font.SERIF, 15 ) );
            rootGroup.getChildren().add(text1 );

            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        });
    }
}
