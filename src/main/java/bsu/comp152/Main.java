package bsu.comp152;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        var loc = getClass().getResource("Main.fxml");
        try {
            root = FXMLLoader.load(loc);
        }catch (IOException e){
            System.out.println("Couldn't Find FXML file!!!!!!");
        }
        Scene windowContents = new Scene(root);
        primaryStage.setScene(windowContents);
        primaryStage.setTitle("Selecting an API");
        primaryStage.show();
    }

    @FXML
    public void exit(ActionEvent event){
        System.exit(0);//alls well - bye bye
    }

    @FXML
    public  void openPhoneWindow(ActionEvent event){
        Parent phoneroot = null;
        var loc = getClass().getResource("PhoneWindow.fxml");
        try {
            phoneroot = FXMLLoader.load(loc);
        }catch (IOException e){
            System.out.println("Couldn't Find FXML file!!!!!!");
        }
        //here is where we are creating the new window
        Scene windowContents = new Scene(phoneroot, 900,400);
        Stage phoneWindow = new Stage();
        phoneWindow.setScene(windowContents);
        phoneWindow.setTitle("Phone Code");
        phoneWindow.show();
    }
    @FXML
    public void openJobsAPI(ActionEvent event) {
        Parent jobsAPIRoot = null;
        var loc = getClass().getResource("jobsAPI.fxml");
        try {
            jobsAPIRoot = FXMLLoader.load(loc);
        } catch (IOException e) {
            System.out.println("Either couldn't find FXML or there was an exception in the init function");
        }
        Scene windowContents = new Scene(jobsAPIRoot, 900, 400);
        Stage jobsAPI = new Stage();
        jobsAPI.setScene(windowContents);
        jobsAPI.setTitle("Job Search");
        jobsAPI.show();
    }
}
//comment4






