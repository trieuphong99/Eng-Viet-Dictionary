package MyPackages.MainScene;

import java.util.Scanner;
import java.util.TreeSet;

public class Dictionary{

    public static Dictionary dictionary = new Dictionary();
    public DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public static String file = "C:\\Users\\ADMIN\\IdeaProjects\\DictGUI\\src\\MyPackages\\MainScene\\dictionary.txt";
    public TreeSet<Word> words_list = new TreeSet<>();
    Scanner scanWord = new Scanner(System.in);

}


