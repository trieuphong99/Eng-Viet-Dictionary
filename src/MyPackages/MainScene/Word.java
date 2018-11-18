package MyPackages.MainScene;

public class Word implements Comparable<Word>{

    public String word_target;
    public String word_explain;

    public Word() {
        word_explain = word_target = "";
    }

    public Word(String word_target, String word_explain){
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    @Override
    public int compareTo(Word word) {
        return this.word_target.compareTo(word.word_target);
    }

    @Override
    public String toString() {
        return word_target;
    }
}
