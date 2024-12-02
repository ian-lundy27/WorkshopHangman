import java.util.*;

public class Hangman {

    static ArrayList<String> words = WordReader.readWordFile("src/basic_english_850.txt");
    final static int MAX_STRIKES = 6;

    Random rdm;
    String word;
    HashSet<Character> guesses;
    int strikes = 0;
    boolean gameOver = false;

    public Hangman() {
        this.rdm = new Random();
        this.word = wordSelector();
        this.guesses = new HashSet<>();
    }

    public void pickDifficulty(Scanner in) {
        System.out.print("Pick a difficulty from 1-3 (adjusts length of word): ");
        int diff;
        if (in.hasNextInt()) diff = in.nextInt();
        else diff = 0;
        if (diff <= 3 && diff >= 1) {
            Object[] temp = words.stream().filter(w -> w.length() >= 2 + 2 * diff).toArray();
            this.word = (String) temp[rdm.nextInt(temp.length)];
        } else {
            System.out.println("Using default difficulty.");
        }
        in.nextLine();
    }

    public String wordSelector(){
        return words.get(rdm.nextInt(words.size()));
    }

    public boolean validGuess(char c) {
        return !guesses.contains(c) && Character.isLetter(c);
    }

    public void displayWord() {
        for (char c : word.toCharArray()) {
            if (guesses.contains(c)) System.out.print(c);
            else System.out.print('_');
        }
        System.out.println();
    }

    public void displayDetailed() {
        System.out.print("Word:\t\t"); displayWord();
        System.out.print("Guesses:\t"); displayGuesses();
        System.out.println("Remaining guesses:\t" + (Hangman.MAX_STRIKES - this.strikes));
    }

    public boolean winCondition() {
        for (char c : word.toCharArray()) {
            if (!guesses.contains(c)) return false;
        }
        return true;
    }

    public void displayGuesses() {
        System.out.println(Arrays.toString(this.guesses.stream().sorted().toArray()));
    }

    public void makeGuess(char c) {
        if (this.gameOver) {
            System.out.println("This message shouldn't display.");
            return;
        }
        if (!this.validGuess(c)) {
            System.out.println("Invalid guess");
            displayGuesses();
        } else {
            guesses.add(c);
            if (!word.contains(Character.toString(c))){
                strikes++;
                System.out.println("Wrong guess");
                hangTheMan();
            } else {
                System.out.println("Correct guess");
            }
            displayDetailed();
        }
        if (checkStrikes())
            gameOver();
    }

    public boolean checkStrikes() {
        return this.strikes >= MAX_STRIKES;
    }

    public void gameOver() {
        System.out.println("GAME OVER!");
        this.gameOver = true;
    }

    public void hangTheMan() {
        switch (this.strikes) {
            case 1:
                System.out.println("""
                        \
                        -----------|          \s
                         |         |          \s
                         |         O          \s
                         |                    \s
                         |                    \s
                         |                    \s
                        /_\\""");
                break;

            case 2:
                System.out.println("""
                        \
                        -----------|          \s
                         |         |          \s
                         |         O          \s
                         |         |          \s
                         |         |          \s
                         |                    \s
                        /_\\""");
                break;

            case 3:
                System.out.println("""
                        \
                        -----------|          \s
                         |         |          \s
                         |         O          \s
                         |        /|          \s
                         |         |          \s
                         |                    \s
                        /_\\""");
                break;

            case 4:
                System.out.println("""
                        \
                        -----------|          \s
                         |         |          \s
                         |         O          \s
                         |        /|\\        \s
                         |         |          \s
                         |                    \s
                        /_\\""");
                break;

            case 5:
                System.out.println("""
                        \
                        -----------|          \s
                         |         |          \s
                         |         O          \s
                         |        /|\\        \s
                         |         |          \s
                         |        /         \s
                        /_\\""");
                break;

            case 6:
                System.out.println("""
                        \
                        -----------|          \s
                         |         |          \s
                         |         O          \s
                         |        /|\\        \s
                         |         |          \s
                         |        / \\        \s
                        /_\\""");
                break;
            default:
                System.out.println("""
                        \
                        -----------|          \s
                         |         |          \s
                         |                    \s
                         |                    \s
                         |                    \s
                         |                    \s
                        /_\\""");
                break;
        }

    }

}
