import java.io.File;
import java.io.*;
import java.util.*;

public class CuratedCorpora {

    private List<String> curatedCorpora;

    public CuratedCorpora() throws FileNotFoundException{
        Scanner input = new Scanner(new File("lib/corpora/Hamlet.txt"));
        curatedCorpora = new ArrayList<>();
    }

    public List<String> createPlayArray(Scanner input) {
        while (input.hasNext()) {
            curatedCorpora.add(input.next());
        }
        return curatedCorpora;
    }
}