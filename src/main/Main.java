import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner hamlet = new Scanner(new File("lib/corpora/Hamlet.txt"));
        List<String> hamletList = buildHamletList(hamlet);
    }

    public static List<String> buildHamletList(Scanner hamlet) {
        List<String> hamletList = new ArrayList();
        List<String> annotations = Arrays.asList("Ber.","Fran.","Hor.","Mar.","King.",
                "Laer.","Pol.","Ham.","Queen.","Oph.","Ghost.","Rey.","Guil.","Fort.","Clown.","Ambassador");
        while (hamlet.hasNext()) {
            String word = hamlet.next();
            if (!annotations.contains(word)) {
                hamletList.add(word);
            }
        }
        return hamletList;
    }
}
