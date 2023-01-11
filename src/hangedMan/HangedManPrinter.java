package hangedMan;

public class HangedManPrinter extends TerminalPrinter {
    private static final String[] HANGED_MAN = {
            "\t\t|\\_____",
            "\t\t/\n" +
                    "\t\t|\n" +
                    "\t\t|\n" +
                    "\t\t|\n" +
                    "\t\t|\n" +
                    "\t\t|\n" +
                    "\t\t|\\_____",
            "\t\t/----\n" +
                    "\t\t|\n" +
                    "\t\t|\n" +
                    "\t\t|\n" +
                    "\t\t|\n" +
                    "\t\t|\n" +
                    "\t\t|\\_____",
            "\t\t/----\n" +
                    "\t\t|   |\n" +
                    "\t\t|\n" +
                    "\t\t|\n" +
                    "\t\t|\n" +
                    "\t\t|\n" +
                    "\t\t|\\_____\n",
            "\t\t/----\n" +
                    "\t\t|   |\n" +
                    "\t\t|   0\n" +
                    "\t\t|\n" +
                    "\t\t|\n" +
                    "\t\t|\n" +
                    "\t\t|\\_____",
            "\t\t/----\n" +
                    "\t\t|   |\n" +
                    "\t\t|   0\n" +
                    "\t\t|   |\n" +
                    "\t\t|\n" +
                    "\t\t|\n" +
                    "\t\t|\\_____",
            "\t\t/----\n" +
                    "\t\t|   |\n" +
                    "\t\t|   0\n" +
                    "\t\t|   |/\n" +
                    "\t\t|\n" +
                    "\t\t|\n" +
                    "\t\t|\\_____",
            "\t\t/----\n" +
                    "\t\t|   |\n" +
                    "\t\t|   0\n" +
                    "\t\t|  \\|/\n" +
                    "\t\t|\n" +
                    "\t\t|\n" +
                    "\t\t|\\_____",
            "\t\t/----\n" +
                    "\t\t|   |\n" +
                    "\t\t|   0\n" +
                    "\t\t|  \\|/\n" +
                    "\t\t|   |\n" +
                    "\t\t|\n" +
                    "\t\t|\\_____",
            "\t\t/----\n" +
                    "\t\t|   |\n" +
                    "\t\t|   0\n" +
                    "\t\t|  \\|/\n" +
                    "\t\t|   |\n" +
                    "\t\t|  /\n" +
                    "\t\t|\\_____\n",
            "\t\t/----\n" +
                    "\t\t|   |\n" +
                    "\t\t|   0\n" +
                    "\t\t|  \\|/\n" +
                    "\t\t|   |\n" +
                    "\t\t|  / \\\n" +
                    "\t\t|\\_____"
    };
    private static final int NUM_STATES = 10;

    public static void printHangedMan(double percent) {
        int state = (int) (percent * NUM_STATES);
        try {
            for (int j = 0; j < 2; j++)
                for (int i = 0; i < state + 1; i++) {
                    TerminalPrinter.clearScreen();
                    TerminalPrinter.print(HANGED_MAN[i]);
                    TerminalPrinter.clearLines(5);
                    Thread.sleep(100);
                }
            TerminalPrinter.clearScreen();
            TerminalPrinter.print(HANGED_MAN[state]);
            TerminalPrinter.clearLines(5);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            TerminalPrinter.println("Game stopped");
        }
    }
}