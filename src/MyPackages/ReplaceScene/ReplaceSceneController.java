package MyPackages.ReplaceScene;

import MyPackages.MainScene.Controller;
import MyPackages.MainScene.Dictionary;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ReplaceSceneController implements Initializable {

    @FXML
    private Button replaceButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextArea replaceWordField;

    @FXML
    private TextArea meanReplaceWordField;

    public void Replace(){
        replaceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dictionary.dictionary.dictionaryManagement.dictionaryReplacing(Dictionary.dictionary.words_list, replaceWordField, meanReplaceWordField,
                        Dictionary.dictionary.file);
            }
        });
    }

    public void Cancel(){
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Controller.replaceStage.close();
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        Replace();
        Cancel();
    }
}
