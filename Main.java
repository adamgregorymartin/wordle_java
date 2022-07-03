public class Main {
    public static void main(String[] args) {
        IO io = new IO();
        io.print("Wordle!");

        Word solution = new Word("apple");
        ClueBuilder clueBuilder = new ClueBuilder();
        clueBuilder.initSolution(solution);
        Clue clue = new Clue();

        byte rounds = 0;
        while (!clue.isCorrect()) {
            ++rounds;
            io.print(String.format("\nTurn %d:", rounds));
            Word guess = io.requestWord();

            clueBuilder.buildClue(clue, guess);

            io.print(guess.toString());
            io.print(clue.toString());
        }

        io.print(String.format("Solved in %d turns", rounds));
    }
}