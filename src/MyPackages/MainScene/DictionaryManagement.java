package MyPackages.MainScene;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;
import com.gtranslate.Audio;
import com.gtranslate.Language;
import javazoom.jl.decoder.JavaLayerException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class DictionaryManagement {

    /*
    public void insertFromFile(TreeSet<Word> words_list, String file){
        String thisLine = null;

        try {

            thisLine = readFile(file, Charset.defaultCharset());
            String words[] = thisLine.split("@");
            for (String word : words) {
                String result[] = word.split("\r?\n", 2);
                if (result.length > 1) {
                    String wordExplain1 = new String();
                    String wordTarget1 = new String();
                    String wordSound1 = new String();
                    if (result[0].contains("/")) {
                        String firstmeaning = result[0].substring(0, result[0].indexOf("/"));
                        String lastSoundMeaning = result[0].substring(result[0].indexOf("/"), result[0].length());
                        wordTarget1 = firstmeaning;
                        wordSound1 = lastSoundMeaning;
                    }
                    else {
                        wordTarget1 = result[0];
                        wordSound1 = "";
                    }

                    wordExplain1 = result[1];

                    wordTarget1 = wordTarget1.trim();
                    wordExplain1 = wordExplain1.trim();
                    wordSound1 = wordSound1.trim();


                    words_list.add(new Word(wordTarget1, wordExplain1));

                }
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    */

    public void insertFromFile(TreeSet<Word> words_list){
        String nextline = new String();
        String packWord = new String();
        String word = new String();
        String meanWord = new String();
        StringBuilder stringBuilder = new StringBuilder();

        try{
            File file = new File("C:\\Users\\ADMIN\\IdeaProjects\\DictGUI\\src\\MyPackages\\MainScene\\dictionary.txt");
            Scanner sc = new Scanner(file);

            while(sc.hasNextLine()){
                nextline = sc.nextLine();
                if(!(nextline.equals("/"))){
                    stringBuilder.append(nextline + "\r\n");

                }
                else {
                    packWord = stringBuilder.toString();
                    word = packWord.substring(0, packWord.indexOf(":")).trim();
                    meanWord = packWord.substring(packWord.indexOf(":"), packWord.length()).trim();
                    words_list.add(new Word(word,meanWord));
                    stringBuilder.delete(0, packWord.length());
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void dictionaryExportToFile(TreeSet<Word> words_list,String file){
        try{
            PrintWriter out = new PrintWriter(file);
            for(Word w : words_list){
                out.println(w.word_target + " : " + w.word_explain + "\r\n" + "/");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void dictionaryTranslate(TreeSet<Word> words_list, TextArea translate_field, String inputWord){

        for(Word w : words_list) {
            if (w.word_target.equals(inputWord)) {
                translate_field.setText(w.word_explain);
                translate_field.setVisible(true);
                break;
            }
            else {
                translate_field.setText("No words found");
                translate_field.setVisible(true);
            }
        }
    }

    public void dictionaryRefresh(TextArea translate_field, TextField search_Word_Bar){
        translate_field.setVisible(false);
        search_Word_Bar.setText("");
    }


    public void dictionaryAdding(TreeSet<Word> words_list, TextArea getNewWord, TextArea getMeanWord, String file){
        String newWord = new String();
        String meanWord = new String();
        newWord = getNewWord.getText();
        meanWord = getMeanWord.getText();
        words_list.add(new Word(newWord, meanWord));
        dictionaryExportToFile(words_list,file);
    }

    public void dictionnaryDeleting(TreeSet<Word> words_list, TextField deletedWordField, String file){
        String deletedWord = new String();
        deletedWord = deletedWordField.getText();
        for(Word w : words_list) {
            if (w.word_target.equals(deletedWord))
                words_list.remove(w.word_target);
            else
                continue;
        }
        dictionaryExportToFile(words_list, file);
    }

    public void dictionaryReplacing(TreeSet<Word> words_list, TextArea replacedWordField, TextArea meanReplacedWord, String file){
        String keyWord = new String();
        keyWord = replacedWordField.getText();

        String replaced_explain_Word  = new String();
        replaced_explain_Word = meanReplacedWord.getText();

        for(Word w : words_list){
            if(w.word_target.equals(keyWord)){
                w.word_target = keyWord;
                w.word_explain = replaced_explain_Word;
            }
        }
        dictionaryExportToFile(words_list, file);
    }

    public void dictionaryPronouncing(TextField searchField) {
        try{
            String gongFile = "src/MyPackages/Pronunciation/" + searchField.getText() + ".mp3";
            InputStream in = new FileInputStream(gongFile);

            // Tạo audiostream từ FileInputStream
            AudioStream audioStream = new AudioStream(in);

            // Mở file âm thanh vừa tải về
            AudioPlayer.player.start(audioStream);
        } catch(Exception e){
            System.out.print(".");
        }
    }

    public void dictionarySearcher(TextField input, TreeSet<Word> words_list){
        TextFields.bindAutoCompletion(input, words_list);
    }
}
