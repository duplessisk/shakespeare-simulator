import java.io.*;
import java.util.*;

public class ProcessFile {

    List<String> wordList;

    public ProcessFile() {
        wordList = new ArrayList();
    }

    public List<String> initWordList(String fileName) throws FileNotFoundException {
        Scanner file = new Scanner(new File(fileName));
        return wordList;
    }

}
