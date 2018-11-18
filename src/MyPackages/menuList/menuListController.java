package MyPackages.menuList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class menuListController implements Initializable {

    @FXML
    private Button addWordButton;
    @FXML
    private AnchorPane parentMainScene;

    @Override
    public void initialize(URL url, ResourceBundle rb){
    }

    /*
    public void initialize_addButton(){

        Parent addScene = FXMLLoader.load(getClass().getResource("AddScene/addScene.fxml"));
        Scene scene = addWordButton.getScene();
        addScene.translateXProperty().set(scene.getWidth());
        StackPane parentContainer = (StackPane) addWordButton.getScene().getRoot();

        parentContainer.getChildren().add(addScene);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(addScene.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(parentMainScene);
        });
        timeline.play();


        addWordButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyPackages/AddScene/addScene.fxml"));
                Parent addScene = null;
                try {
                    addScene = fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setTitle("Adding New Word");
                stage.setScene(new Scene(addScene));
                stage.show();
            }
        });
    }
    */
}
