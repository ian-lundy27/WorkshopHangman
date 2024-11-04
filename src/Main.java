import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        doTheThing();

    }

    public static void doTheThing() {

        Hangman hangman = new Hangman();

        char guess;
        do {

            System.out.print("Make a guess: ");
            guess = in.nextLine().charAt(0);
            hangman.makeGuess(guess);

        } while (!(hangman.gameOver || hangman.winCondition()));

        if (hangman.winCondition()) System.out.println("You win!");
        else System.out.println("You are so f------ bad that you couldn't guess the word '" + hangman.word + "'");
        System.out.println("Play again? y/n");
        if (in.nextLine().equalsIgnoreCase("y")) doTheThing();
        else System.out.println("Goodbye!");

    }


}
