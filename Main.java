public class Main {
    public static void main(String[] args) {
        IO io = new IO();
        io.print("Wordle!");
        String word = io.requestWord();
        io.print(word);
    }
}
