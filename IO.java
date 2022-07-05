class IO {
    /*
     * Wraps command line interface IO.
     */

    public void print(String string) {
        System.out.println(string);
    }

    public Word requestGuess() {
        System.out.print("Guess: ");
        while (true) {
            String input = System.console().readLine();
            input = input.toLowerCase().trim();
            Word guess = Word.valueOf(input);
            if (guess != null)
                return guess;
        }
    }

    public String requestFeedback() {
        System.out.print("Feedback: ");
        while (true) {
            String input = System.console().readLine();
            input = input.toLowerCase().trim();
            if (Clue.isValidFeedback(input)) {
                return input;
            }
        }
    }
}