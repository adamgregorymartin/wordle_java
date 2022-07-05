import java.util.List;

public class Main {
    public static void main(String[] args) {
        IO io = new IO();
        io.print("Wordle!");

        // Play with a hard coded solution
        Word solution = new Word("cigar");
        ClueBuilder clueBuilder = new ClueBuilder();
        clueBuilder.initSolution(solution);

        Clue clue = new Clue();

        int nGuesses = 0;
        Guesser guesser = new RandomGuesser();

        while (!clue.isCorrect()) {
            ++nGuesses;

            Word guess = guesser.getGuess();
            System.out.println(guess);

            clueBuilder.buildClue(clue, guess);
            guesser.provideClue(clue);
        }

        io.print(String.format("\nSolved in %d guesses", nGuesses));

        // Clue clue = new Clue();
        // while (!clue.isCorrect()) {
        // Word guess = io.requestGuess();
        // String feedback = io.requestFeedback();
        // clue.buildClue(guess, feedback);
        // }

        /*
         * rewrite Word.isValid to Word.tryMakingWord
         * Guesser based on WordsProfile
         * Guesser based on brute force
         * Change words weight to log normal probability
         * Add multi threading
         * Allow Clue to target select indices?
         */
    }
}