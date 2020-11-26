import java.util.*;

public class Play {

    private Map<String, Map<String, Integer>> wordDict;

    public Play() {}

    public List<String> initWordList(Scanner inputFile) {
        List<String> wordList = new ArrayList();
        while (inputFile.hasNext()) {
            wordList.add(inputFile.next());
        }
        return wordList;
    }

    public Map<String, Map<String, Integer>> initWordDict(List<String> wordList) {
        wordDict = new HashMap();
        for (int i = 0; i < wordList.size() - 1; i++) {
            String word = wordList.get(i), nextWord = wordList.get(i + 1);
            if (wordDict.keySet().contains(word)) {
                Map<String, Integer> subDict = wordDict.get(word);
                if (subDict.keySet().contains(nextWord)) {
                    subDict.put(nextWord, subDict.get(nextWord) + 1);
                } else {
                    subDict.put(nextWord,1);
                }
            } else {
                wordDict.put(word,new HashMap());
                Map<String, Integer> subDict = wordDict.get(word);
                subDict.put(nextWord,1);
            }
        }
        return wordDict;
    }

    public double weightedCounts(String word, Map<String,Integer> subDict) {

        return 0;
    }

}
