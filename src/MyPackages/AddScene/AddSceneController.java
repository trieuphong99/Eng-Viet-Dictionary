package MyPackages.AddScene;

import MyPackages.MainScene.Dictionary;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import MyPackages.MainScene.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;



public class AddSceneController implements Initializable {


    @FXML
    private TextArea newWordField;

    @FXML
    private TextArea meanWordField;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;


    public void Add() {
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dictionary.dictionary.dictionaryManagement.dictionaryAdding(Dictionary.dictionary.words_list, newWordField, meanWordField,
                        Dictionary.dictionary.file);

            }
        });
    }

    public void CancelAddScene(){
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Controller.addStage.close();
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        Add();
        CancelAddScene();
    }
}
