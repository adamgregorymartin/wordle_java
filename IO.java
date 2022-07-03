class IO {
    /*
     * This class wraps command line interface IO.
     */

    public void print(String string) {
        System.out.println(string);
    }

    public Word requestWord() {
        System.out.print("Guess: ");
        while (true) {
            String input = System.console().readLine();
            input = input.toLowerCase().trim();
            if (Word.isValid(input)) {
                return new Word(input);
            }
        }
    }
}