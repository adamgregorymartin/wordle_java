import java.util.Random;

class RandomGuesser extends Guesser {

    Random random = new Random();

    @Override
    public Word getGuess() {
        short index = validIndices.get(random.nextInt(validIndices.size()));
        return Words.at(index);
    }
}