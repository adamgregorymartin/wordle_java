public class Main {
    public static void main(String[] args) {
        IO io = new IO();
        io.print("Wordle!");

        Word solution = new Word("apple");

        Word guess = io.requestWord();

        ClueBuilder clueBuilder = new ClueBuilder();
        Clue clue = new Clue();
        clueBuilder.initSolution(solution);
        clueBuilder.buildClue(guess, clue);

        io.print(clue.toString());

        /*
         * Add classes:
         * - Player
         * - Game
         * - WordManager?
         */
    }
}