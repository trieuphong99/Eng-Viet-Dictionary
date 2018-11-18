package MyPackages.MainScene;

import java.util.Scanner;
import java.util.TreeSet;

public class DictionaryCommandLine{
    public DictionaryManagement dictionary_management = new DictionaryManagement();
    public static DictionaryCommandLine dictionary_commandline = new DictionaryCommandLine();


    public void showAllWords(TreeSet<Word> words_list) {
        int sothutu = 0;
        System.out.println("No	|English	|Vietnamese");
        for (Word w : words_list) {
            sothutu++;
            System.out.println((sothutu) + "	" + (w.word_target) + "	" + (w.word_explain));
        }
    }


    /*
    public void dictionaryAdvanced(TreeSet<Word> words_list, String file){

        int i = 1;
        dictionary_management.insertFromFile(words_list, file);
        showAllWords(words_list);

        Scanner scanWord = new Scanner(System.in);

        while(i==1) {
            dictionary_management.dictionaryLookup(words_list, scanWord);
            //dictionary_management.dictionaryAdding(words_list, scanWord);
            //dictionary_management.dictionnaryDeleting(words_list, scanWord);
            dictionary_management.dictionaryReplace(words_list, scanWord);

            dictionary_management.dictionaryExportToFile(words_list, file);
        }
    }
    */
}
