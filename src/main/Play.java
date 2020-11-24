import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Play {

    private Map<String, Map<String,Integer>> hamWordDict;

    public Play(Scanner playFile) {
        List<String> wordList = initWordList(playFile);
    }

    public List<String> initWordList(Scanner inputFile) {
        List<String> wordList = new ArrayList();
        while (inputFile.hasNext()) {
            wordList.add(inputFile.next());
        }
        return wordList;
    }

}
