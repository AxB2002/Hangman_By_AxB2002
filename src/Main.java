import hangedMan.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        char selected = 'n';
        do {
            try {
                hangedMan.Game game = new hangedMan.Game("WordList.txt");
                game.setDifficulty();
                game.start();
            } catch (IOException e) {
                TerminalPrinter.println("There was a problem trying to read the word list.");
            }
            TerminalPrinter.println();
            selected = TerminalReader.readChar("\nPlay again? (S/n): ");
        } while (selected == 'S' || selected == 's');
    }
}