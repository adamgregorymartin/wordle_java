import java.util.Set;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        IO io = new IO();
        io.print("Wordle!");

        Set<Short> allIndices = Words.getAllIndices();

        WordsProfile profile = new WordsProfile(allIndices);

        /*
         * Clue clue = new Clue();
         * 
         * while (!clue.isCorrect()) {
         * Word guess = io.requestGuess();
         * String feedback = io.requestFeedback();
         * clue.buildClue(guess, feedback);
         * }
         */

        /*
         * // Play with a hard coded solution
         * Word solution = new Word("apple");
         * ClueBuilder clueBuilder = new ClueBuilder();
         * clueBuilder.initSolution(solution);
         * Clue clue = new Clue();
         * 
         * byte rounds = 0;
         * while (!clue.isCorrect()) {
         * ++rounds;
         * io.print(String.format("\nTurn %d:", rounds));
         * Word guess = io.requestGuess();
         * 
         * clueBuilder.buildClue(clue, guess);
         * 
         * io.print(guess.toString());
         * io.print(clue.toString());
         * }
         * 
         * io.print(String.format("\nSolved in %d turns", rounds));
         */

        /*
         * Create basic AI algorithm
         * Optimize Clue to target select indices
         * Add brute force
         * Add mutli threading
         */
    }
}