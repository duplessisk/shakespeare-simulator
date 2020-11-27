import java.util.*;
import java.lang.*;

public class Play {

    private Map<String, Map<String, Double>> wordMap;

    public Play() {}

    public List<String> initWordList(Scanner inputFile) {
        List<String> wordList = new ArrayList();
        while (inputFile.hasNext()) {
            wordList.add(inputFile.next());
        }
        return wordList;
    }
    
    public Map<String, Map<String, Double>> initWordMap(List<String> wordList) {
        wordMap = new TreeMap();
        for (int i = 0; i < wordList.size() - 1; i++) {
            String word = wordList.get(i), nextWord = wordList.get(i + 1);
            if (wordMap.keySet().contains(word)) {
                Map<String, Double> innerMap = wordMap.get(word);
                if (innerMap.keySet().contains(nextWord)) {
                    innerMap.put(nextWord, innerMap.get(nextWord) + 1);
                } else {
                    innerMap.put(nextWord,1.0);
                }
            } else {
                wordMap.put(word,new TreeMap());
                Map<String, Double> innerMap = wordMap.get(word);
                innerMap.put(nextWord,1.0);
            }
        }
        return wordMap;
    }

    public void setWeightedCounts() {
        for (String word : wordMap.keySet()) {
            Map<String,Double> innerMap = wordMap.get(word);
            weightedCounts(innerMap);
        }
    }

    public void weightedCounts(Map<String,Double> innerMap) {
        int totalWordCount = totalCount(innerMap);
        double previousWeightedCount = 0;
        for (String innerKey : innerMap.keySet()) {
            double weightedCount = innerMap.get(innerKey)/totalWordCount + previousWeightedCount;
            innerMap.put(innerKey,weightedCount);
            previousWeightedCount = weightedCount;
         }
    }

    public int totalCount(Map<String,Double> innerMap) {
        int totalWordCount = 0;
        for (String innerKey : innerMap.keySet()) {
            totalWordCount += innerMap.get(innerKey);
        }
        return totalWordCount;
    }

    public String initSentence() {
        String sentence = "";
        String nextKey = "Thy";
        sentence += nextKey;
        while(!nextKey.contains(".") && !nextKey.contains("?") && !nextKey.contains("!")) {
            nextKey = nextWord(nextKey);
            sentence += " " + nextKey.toLowerCase();
        }
        return sentence;
    }

    public String nextWord(String nextKey) {
        Random rand = new Random();
        double ran = rand.nextDouble();
        Map<String,Double> innerMap = wordMap.get(nextKey);
        for (String innerKey : innerMap.keySet()) {
            if (ran <= innerMap.get(innerKey)) {
                return innerKey;
            }
        }
        return "ERROR";
    }
}
