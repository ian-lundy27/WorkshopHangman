import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Hangman {

    static ArrayList<String> words = WordReader.readWordFile("src/basic_english_850.txt");
    final static int MAX_STRIKES = 6;

    Random rdm;
    String word;
    HashSet<Character> guesses;
    int strikes = 0;
    boolean gameOver = false;
    boolean won;

    public Hangman() {
        this.rdm = new Random();
        this.word = wordSelector();
        this.guesses = new HashSet<>();
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
        System.out.println("Remaining guesses:\t" + this.strikes);
    }

    public boolean winCondition() {
        for (char c : word.toCharArray()) {
            if (!guesses.contains(c)) return false;
        }
        return true;
    }

    public void displayGuesses() {
        System.out.println(this.guesses);
    }

    public void makeGuess(char c) {
        if (this.gameOver) {
            System.out.println("GAME OVER STOP PLAYING BOZO");
            return;
        };
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
            displayWord();
        }
        if (checkStrikes())
            gameOver();
    }

    public boolean checkStrikes() {
        return this.strikes >= MAX_STRIKES;
    }

    public void gameOver() {
        System.out.println("GAME OVER MAN, GAME OVER");
        this.gameOver = true;
    }

    public void hangTheMan() {
        switch (this.strikes) {


            case 1:
                System.out.println("" +
                        "-----------|           \n" +
                        " |         |           \n" +
                        " |         O           \n" +
                        " |                     \n" +
                        " |                     \n" +
                        " |                     \n" +
                        "/_\\");
                break;

            case 2:
                System.out.println("" +
                        "-----------|           \n" +
                        " |         |           \n" +
                        " |         O           \n" +
                        " |         |           \n" +
                        " |         |           \n" +
                        " |                     \n" +
                        "/_\\");
                break;

            case 3:
                System.out.println("" +
                        "-----------|           \n" +
                        " |         |           \n" +
                        " |         O           \n" +
                        " |        /|           \n" +
                        " |         |           \n" +
                        " |                     \n" +
                        "/_\\");
                break;

            case 4:
                System.out.println("" +
                        "-----------|           \n" +
                        " |         |           \n" +
                        " |         O           \n" +
                        " |        /|\\         \n" +
                        " |         |           \n" +
                        " |                     \n" +
                        "/_\\");
                break;

            case 5:
                System.out.println("" +
                        "-----------|           \n" +
                        " |         |           \n" +
                        " |         O           \n" +
                        " |        /|\\         \n" +
                        " |         |           \n" +
                        " |        /          \n" +
                        "/_\\");
                break;

            case 6:
                System.out.println("" +
                        "-----------|           \n" +
                        " |         |           \n" +
                        " |         O           \n" +
                        " |        /|\\         \n" +
                        " |         |           \n" +
                        " |        / \\         \n" +
                        "/_\\");
                break;
            default:
                System.out.println("" +
                        "-----------|           \n" +
                        " |         |           \n" +
                        " |                     \n" +
                        " |                     \n" +
                        " |                     \n" +
                        " |                     \n" +
                        "/_\\");
                break;
        }

    }

}
