import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
}
