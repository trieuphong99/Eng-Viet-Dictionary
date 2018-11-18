package MyPackages.DeleteScene;

import MyPackages.MainScene.Controller;
import MyPackages.MainScene.Dictionary;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteSceneController implements Initializable {

    @FXML
    private Button deleteButton;

    @FXML
    private Button cancelDeleteButton;

    @FXML
    private TextField deleteField;

    public void Delete(){
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dictionary.dictionary.dictionaryManagement.dictionnaryDeleting(Dictionary.dictionary.words_list, deleteField
                ,Dictionary.dictionary.file);
            }
        });
    }

    public void CancelDeleteScene(){
        cancelDeleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Controller.deleteStage.close();
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        Delete();
        CancelDeleteScene();
    }
}
