import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    // global variables
    Scanner hamlet = new Scanner(new File("lib/corpora/Hamlet.txt"));
    List<String> annotations = Arrays.asList("Ber.","Fran.","Hor.","Mar.","King.",
            "Laer.","Pol.","Ham.","Queen.","Oph.","Ghost.","Rey.","Guil.","Fort.","Clown.","Ambassador");
    List<String> hamletList = Main.buildHamletList(hamlet);

    MainTest() throws FileNotFoundException {
    }

    @Test
    void HamletListIsCorrectSize() {
        int numHamWords = 0;
        int numHamAnnotations = 0;
        while (hamlet.hasNext()) {
            String word = hamlet.next();
            if (annotations.contains(word)) {
                numHamAnnotations += 1;
            }
            numHamWords += 1;
        }
        assertTrue(numHamWords == hamletList.size());
        //assertTrue(numHamWords - numHamAnnotations == hamletList.size());
    }

    @Test
    void HamletListIsNotEmpty() {
        assertFalse(hamletList.size() == 0);
    }

    @Test
    void HamletListOmitsAnnotations() {
        for (String word : hamletList) {
            assertFalse(annotations.contains(word));
        }
    }
}