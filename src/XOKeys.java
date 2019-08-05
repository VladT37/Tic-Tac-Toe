import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class XOKeys {
    static char defaultKey;
    final static Scanner scanner = new Scanner(System.in);
    final static char X_KEY = 'X';
    final static char O_KEY = 'O';
    final static char QUIT_KEY = 'Q';
    final static char CONTINUE_KEY = 'C';
    final static char YES_KEY = 'Y';
    final static char NO_KEY = 'N';

    static char getDefaultKey() {
        return defaultKey;
    }

    void insertDefaultKey() {
        // Firstly, it checks if the user's input isn't a blank space or longer than a character.
        // If so, the char is set to '~' and it throws an exception.
        // After that it sets the char 'defaultKey' to the character given by the user.
        // Then it verifies if the key is X or O
        // If so, the char is set to '~' and it throws an exception.

        System.out.print("Insert the default key for the table(e.g., '~'); 'X' and 'O' are not allowed: ");
        String tester = XOKeys.scanner.nextLine();
        if (tester.length() < 1) {
            defaultKey = '~';
            System.out.println("ERROR! The key you entered is a blank space! It has been set to: ~");
            return;
        }
        if (tester.length() > 1) {
            defaultKey = '~';
            System.out.println("ERROR! The key you entered contains more than one character! It has been set to: ~");
            return;
        }

        defaultKey = tester.charAt(0);
        if (Character.toUpperCase(defaultKey) == XOKeys.X_KEY ||
                Character.toUpperCase(defaultKey) == XOKeys.O_KEY ) {
            defaultKey = '~';
            System.out.println("ERROR! You can't play with X or O as default key for the table! It has been set to: ~");
        }
    }

    private void whoIsX() {
        // After randomly setting the keys for the players, this function checks whose player's key has been set to 'X'.
        // According to that, it sets 'turnPlayer1' either True or False;

        if (Main.player1.getKey() == X_KEY) {
            XOGame.setTurnPlayer1(true);
        } else {
            XOGame.setTurnPlayer1(false);
        }
    }

    XOKeys() {
        List<Character> keyList = Arrays.asList(X_KEY, O_KEY);
        Collections.shuffle(keyList);
        Main.player1.setKey(keyList.get(0));
        Main.player2.setKey(keyList.get(1));
        whoIsX(); // Determines who starts this round
    }
}
