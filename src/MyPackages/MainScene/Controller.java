
package MyPackages.MainScene;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import com.gtranslate.Audio;
import com.gtranslate.Language;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import com.jfoenix.controls.JFXHamburger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.SpeechDataEvent;
import com.voicerss.tts.SpeechDataEventListener;
import com.voicerss.tts.SpeechErrorEvent;
import com.voicerss.tts.SpeechErrorEventListener;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class Controller implements Initializable {

    @FXML
    private AnchorPane parentMainScene;
    @FXML
    private AnchorPane mainScene;
    @FXML
    private TextField searchField;
    @FXML
    private Button search_Button;
    @FXML
    private TextArea translate_field;
    @FXML
    private Button refresh_Button;
    @FXML
    private JFXHamburger menuHamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private VBox options_box;
    @FXML
    private Button addWordButton;
    @FXML
    private Button addWord;
    @FXML
    private Button pronounceButton;


    public static Stage addStage = new Stage(); //declare this stage for addScene use.
    public static Stage deleteStage = new Stage();
    public static Stage replaceStage = new Stage();

    public void initialize_searchTextField(){
        Dictionary.dictionary.dictionaryManagement.insertFromFile(Dictionary.dictionary.words_list);
        Dictionary.dictionary.dictionaryManagement.dictionarySearcher(searchField, Dictionary.dictionary.words_list);
    }

    public void initialize_searchButton(){

        search_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dictionary.dictionary.dictionaryManagement.dictionaryTranslate(Dictionary.dictionary.words_list, translate_field, searchField.getText());
            }
        });

        searchField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)){
                    Dictionary.dictionary.dictionaryManagement.dictionaryTranslate(Dictionary.dictionary.words_list, translate_field, searchField.getText());
                }
            }
        });
    }

    public void initialize_refreshButton(){
        refresh_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Dictionary.dictionary.dictionaryManagement.dictionaryRefresh(translate_field, searchField);
            }
        });
    }


    public void close_options_box(HamburgerSlideCloseTransition menuButton, JFXDrawer drawer){
        mainScene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(drawer.isOpened()){
                    menuButton.setRate(menuButton.getRate() * -1);
                    menuButton.play();
                }
                drawer.close();
            }
        });
    }

    /*
    public void initialize_menuButton(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menuList/menuList.fxml"));
            VBox options_box = loader.load();
            drawer.setSidePane(options_box);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        HamburgerSlideCloseTransition menuButton = new HamburgerSlideCloseTransition(menuHamburger);
        menuButton.setRate(-1);
        menuHamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
            menuButton.setRate(menuButton.getRate() * -1);
            menuButton.play();

            if(drawer.isOpened()){
                drawer.close();
            }
            else{
                drawer.open();
            }
            close_options_box(menuButton, drawer);
        });
    }
    */

    @FXML
    private void initialize_addButton(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddScene/addScene.fxml"));
            Parent addScene = (Parent) fxmlLoader.load();
            addStage.setScene(new Scene(addScene));
            addStage.show();
        } catch (Exception e){
            System.out.print("No window");
        }
    }

    @FXML
    private void initialize_replaceButton(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReplaceScene/replaceScene.fxml"));
            Parent replaceScene = (Parent) fxmlLoader.load();
            replaceStage.setScene(new Scene(replaceScene));
            replaceStage.show();
        } catch (Exception e){
            System.out.print("No window");
        }
    }

    @FXML
    private void initialize_deleteButton(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteScene/deleteScene.fxml"));
            Parent deleteScene = (Parent) fxmlLoader.load();
            deleteStage.setScene(new Scene(deleteScene));
            deleteStage.show();
        } catch (Exception e){
            System.out.print("No window");
        }
    }

    public void initialize_pronounceButton() {
        pronounceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String word = searchField.getText();
                    VoiceProvider tts = new VoiceProvider("e04753452f8b49a49c7639c2cd260e68");

                    VoiceParameters params = new VoiceParameters(word, Languages.English_GreatBritain);
                    params.setCodec(AudioCodec.WAV);
                    params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
                    params.setBase64(false);
                    params.setSSML(false);
                    params.setRate(0);

                    byte[] voice = tts.speech(params);

                    FileOutputStream fos = new FileOutputStream("src/MyPackages/Pronunciation/" + word + ".mp3");
                    fos.write(voice, 0, voice.length);
                    fos.flush();
                    fos.close();

                    // tao file dau vao
                    String gongFile = "src/MyPackages/Pronunciation/" + word +".mp3";
                    InputStream in = new FileInputStream(gongFile);

                    // Tạo audiostream từ FileInputStream
                    AudioStream audioStream = new AudioStream(in);

                    // Mở file âm thanh vừa tải về
                    AudioPlayer.player.start(audioStream);
                } catch (Exception e){
                    System.out.println("No audio file");
                }
            }
        });

    }



    @Override
    public void initialize(URL url, ResourceBundle rb){
        initialize_searchTextField();
        initialize_searchButton();
        initialize_refreshButton();
        initialize_pronounceButton();
    }
}
