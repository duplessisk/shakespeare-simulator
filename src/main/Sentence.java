import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Sentence {

    private List<String> senList;

    public Sentence() {
        senList = new LinkedList();
    }

    public void addWord(String word) {
        senList.add(word.toLowerCase());
    }

    public boolean correctLen() {
        return senList.size() >= 2 && senList.size() <= 10;
    }

    public boolean endSentence(String nextKey) {
        return nextKey.contains(".") || nextKey.contains("?") || nextKey.contains("!");
    }

    public void buildSentence(Words w, String nextWord, Random rand) {
        addWord(nextWord);
        while (!endSentence(nextWord)) {
            nextWord = w.getNextWord(w.getKey(nextWord), rand.nextDouble());
            addWord(nextWord);
        }
    }

    public int getLen() {
        return senList.size();
    }

    public String toString() {
        String sen = "";
        Iterator<String> itr = senList.iterator();
        while (itr.hasNext()) {
            sen += itr.next() + " ";
        }
        return sen;
    }

    public void clear() {
        senList.clear();
    }
}
