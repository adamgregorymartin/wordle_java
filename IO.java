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
            if (Word.isValid(input)) {
                return new Word(input);
            }
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