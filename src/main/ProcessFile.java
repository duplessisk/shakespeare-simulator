import java.io.*;
import java.util.*;

public class ProcessFile {

    List<String> wordList;

    public ProcessFile() {
        wordList = new ArrayList();
    }

    public List<String> initWordList(String fileName) throws FileNotFoundException {
        Scanner inputFile = new Scanner(new File(fileName));
        while (inputFile.hasNext()) {
            wordList.add(inputFile.next());
        }
        return wordList;
    }

}
