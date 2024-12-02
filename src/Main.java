import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        doTheThing();

    }

    public static void doTheThing() {

        Hangman hangman = new Hangman();

        hangman.pickDifficulty(in);

        String input;
        in.useDelimiter("\n");
        do {

                System.out.print("Make a guess: ");
                input = in.next();
                if (!input.isEmpty()) hangman.makeGuess(input.charAt(0));
                else hangman.makeGuess('0');


        } while (!(hangman.gameOver || hangman.winCondition()));

        if (in.hasNextLine()) in.nextLine();

        if (hangman.winCondition()) System.out.println("You win!");
        else System.out.println("You lose. The word was '" + hangman.word + "'");
        System.out.print("Play again? y/n: ");
        if (in.nextLine().equalsIgnoreCase("y")) doTheThing();
        else System.out.println("Goodbye!");

    }


}
