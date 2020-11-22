import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CuratedCorporaTest extends CuratedCorpora{

    public CuratedCorporaTest() throws FileNotFoundException {}


    private List<String> playArray = createPlayArray(new Scanner(new File("lib/corpora/Hamlet.txt")));

    @Test
    void playArrayMatchesText() throws FileNotFoundException {
        File hamlet = new File("lib/corpora/Hamlet.txt");
        Scanner input = new Scanner(hamlet);
        int playArrayIndex = 0;
        while (input.hasNext()) {
            assertEquals(input.next(),playArray.get(playArrayIndex));
            playArrayIndex += 1;
        }
    }
}