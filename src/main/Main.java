import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner hamletFile = new Scanner(new File("lib/corpora/Hamlet.txt"));
        HamWordBank hamWords = new HamWordBank(hamletFile);

    }
}
