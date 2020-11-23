import java.util.*;

public class HamDict {

    private Map<String,Map<String, Integer>> hamWordDict;

    public HamDict() {
        hamWordDict = new HashMap();
    }

    public HamDict(HamWordBank hamWordList) {
        Set<String> hamKeySet = keySet(hamWordList);
    }

    public Set<String> keySet(HamWordBank hamWordList) {
        Set<String> hamKeySet = new HashSet();
        for (int i = 0; i < hamWordList.getSize(); i++) {
            hamKeySet.add(hamWordList.getWord(i));
        }
        return hamKeySet;
    }

    public List<String> getNextWords(List<String> hamWordList, Set<String> hamKeySet) {
        List<String> nextWords = new ArrayList();
        return nextWords;
    }
}
