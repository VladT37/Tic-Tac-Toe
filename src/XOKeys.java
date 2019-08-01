import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class XOKeys {
    private static char keyPlayer1;
    private static char keyPlayer2;

    char getKeyPlayer1() {
        return keyPlayer1;
    }

    char getKeyPlayer2() {
        return keyPlayer2;
    }

    private void whoIsX() {
        // After randomly setting the keys for the players, this function checks whose player's key has been set to 'X'.
        // According to that, it sets 'turnPlayer1' either True or False;

        if (keyPlayer1 == 'X') {
            XOGame.setTurnPlayer1(true);
        } else {
            XOGame.setTurnPlayer1(false);
        }
    }

    XOKeys() {
        List<Character> keyList = Arrays.asList('X', 'O');
        Collections.shuffle(keyList);
        keyPlayer1 = keyList.get(0);
        keyPlayer2 = keyList.get(1);
        whoIsX(); // Determines who starts this round
    }
}
