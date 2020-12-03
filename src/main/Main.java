import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ProcessFile pf = new ProcessFile();
        Words w = new Words();
        Sentence s = new Sentence();
        List<String> wordList = pf.initWordList("lib/corpora/Hamlet.txt");
        w.initWordMap(wordList);
        w.setWeightedCounts();
        Scanner rootWordInput = new Scanner(System.in), continueInput = new Scanner(System.in);
        boolean keepSim = true;
        while (keepSim) {
            String rootWord = getUserInput(rootWordInput,w);
            System.out.println(returnSentence(rootWord,s,w));
            System.out.println();
            System.out.print("play again(y/n)? ");
            String p = continueInput.next();
            keepSim = p.equals("y");
        }
    }

    public static String getUserInput (Scanner userInput, Words w) {
        System.out.println();
        String rootWord = "";
        do {
            System.out.print("Here is a possible list of root words: ");
            System.out.println("Hamlet, thy, devil, hitherto, behooves, thou, O,");
            System.out.print("Please select a root word: ");
            rootWord = userInput.nextLine();
        } while (invalidUserInput(w,rootWord));
        return rootWord;
    }

    public static String returnSentence(String rootWord, Sentence s, Words w) {
        System.out.println();
        Random rand = new Random();
        do {
            s.clear();
            s.buildSentence(w,rootWord,rand);
        } while(!s.correctLen());
        return s.toString();
    }

    public static boolean invalidUserInput(Words w, String rootWord) {
        boolean input = false;
        if (rootWord.equals("Q") || rootWord.equals("q")) {
            System.exit(0);
        } else if (rootWord.contains(".") || rootWord.contains("?") || rootWord.contains("!")) {
            System.out.println();
            System.out.println("root word cannot contain a special character.");
            System.out.println();
            input = true;
        } else if (rootWord.contains(" ")) {
            System.out.println();
            System.out.println("root word must be a single word.");
            System.out.println();
            input = true;
        } else if (!w.getKeySet().contains(rootWord)) {
            System.out.println();
            System.out.println("root word not contained in corpora.");
            System.out.println();
            input = true;
        }
        return input;
    }
}
