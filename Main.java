public class Main {
    public static void main(String[] args) {
        IO io = new IO();
        io.print("Wordle!");
        Word word = io.requestWord();
        io.print(word.toString());

        /*
         * Add classes:
         * - Word
         * - Feedback
         * - FeedbackBuilder
         * - Player
         * - Game
         * - WordManager?
         */
    }
}