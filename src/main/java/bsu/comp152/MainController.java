package bsu.comp152;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class MainController implements Initializable {

    @FXML
    private ListView<DataHandler.jobDataType> ListControl;
    private DataHandler Model;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        ListControl.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<DataHandler.jobDataType> () {

                    @Override
                    public void changed(ObservableValue<? extends DataHandler.jobDataType> observable, DataHandler.jobDataType oldValue, DataHandler.jobDataType newValue) {
                        var job = ListControl.getSelectionModel().getSelectedItem();
                        Alert jobInfo = new Alert(Alert.AlertType.CONFIRMATION);
                        jobInfo.setTitle("Info for"+job.title);
                        jobInfo.setHeaderText("Company: "+job.company);
                        jobInfo.showAndWait();
                    }
                }
        );
    }

    public void loadData() {
        var site = "https://jobs.github.com/positions.json?page=1";
        var params = getQueryParameters();
        var query = site + params;

        Model = new DataHandler(query);
        var jobList = Model.getData();
        ObservableList<DataHandler.jobDataType> dataToShow = FXCollections.observableArrayList(jobList);
        ListControl.setItems(dataToShow);
    }

    public String getQueryParameters() {
        var specificJob = getJobType();
        var jobInfo = getJobField();
        return "i=" + jobInfo + "&q=" + specificJob;
    }

    private String getJobField() {
        TextInputDialog answer = new TextInputDialog("Nurse");
        answer.setHeaderText("Gathering Information");
        answer.setContentText("What specific job would you like to do in that field? (example: nurse, salesman, chemist)");
        Optional<String> result = answer.showAndWait();
        if (result.isPresent())
            return result.get();
        else
            return "";
    }

    private String getJobType() {
        TextInputDialog answer = new TextInputDialog("Medial");
        answer.setHeaderText("Gathering Information");
        answer.setContentText("What job field do you want to go into? (example: medical, business, science, etc");
        answer.setWidth(400);
        answer.setResizable(true);
        Optional<String> result = answer.showAndWait();
        if (result.isPresent())
            return result.get();
        else
            return "";
    }
}

//comment
