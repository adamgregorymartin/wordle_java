import java.util.List;

abstract class Guesser {

    List<Short> validIndices;

    public Guesser(List<Short> validIndices) {
        this.validIndices = validIndices;
    }

    public Guesser() {
        this(Words.getAllIndices());
    }

    public void provideClue(Clue clue) {
        validIndices = Words.getConsistentIndices(validIndices, clue);
    }

    public abstract Word getGuess();
}