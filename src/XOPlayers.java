import java.util.Scanner;

class XOPlayers {
    private String player1;
    private String player2;
    private int scorePlayer1;
    private int scorePlayer2;
    private Scanner scanner = new Scanner(System.in);

    private void showGameInfo() {
        System.out.println("\nThe player whose key is assigned to 'X' moves first \n" +
                        "Type 'Q' at any time to quit the game \n" +
                        "Type 'C' after the round ends to continue playing \n" +
                        "\nType a one digit number from 0 to 2 (inclusive) for each row \n" +
                        "Followed by another one from 0 to 2 (inclusive) for each column \n" +
                        "Separated by a new line\n\n");
    }

    int getScorePlayer1() {
        return scorePlayer1;
    }

    void setScorePlayer1(int scorePlayer1) {
        this.scorePlayer1 = scorePlayer1;
    }

    int getScorePlayer2() {
        return scorePlayer2;
    }

    void setScorePlayer2(int scorePlayer2) {
        this.scorePlayer2 = scorePlayer2;
    }

    String getPlayer1() {
        return player1;
    }

    String getPlayer2() {
        return player2;
    }

    private void insertPlayer1() {
        // Sets the string 'player 1' to the name given by the user.
        // Then it verifies if the string is shorter than a character or longer than 32 characters
        // If so, the string is set to - 'Player 1' and it throws an exception.

        System.out.print("Insert name for Player 1 (1-32 characters): ");
        try {
            player1 = scanner.nextLine();
            if (player1.trim().length() <= 0) {
                player1 = "Player 1";
                throw new XOException("ERROR! Player 1's name is too short! It has been set to: Player 1");
            }
            if (player1.trim().length() > 32) {
                player1 = "Player 1";
                throw new XOException("ERROR! Player 1's name is too long! It has been set to: Player 1");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertPlayer2() {
        // Sets the string 'player 2' to the name given by the user.
        // Then it verifies if the string is shorter than a character or longer than 32 characters.
        // If so, the string is set to - 'Player 2' and it throws an exception.

        System.out.print("Insert name for Player 2 (1-32 characters): ");
        try {
            player2 = scanner.nextLine();
            if (player2.trim().length() <= 0) {
                player2 = "Player 2";
                throw new XOException("ERROR! Player 2's name is too short! It has been set to: Player 2");
            }
            if (player2.trim().length() > 32) {
                player2 = "Player 2";
                throw new XOException("ERROR! Player 2's name is too long! It has been set to: Player 2");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void insertDefaultKey() {
        // Firstly, it checks if the user's input isn't a blank space or longer than a character.
        // If so, the char is set to '~' and it throws an exception.
        // After that it sets the char 'defaultKey' to the character given by the user.
        // Then it verifies if the key is X or O
        // If so, the char is set to '~' and it throws an exception.

        System.out.print("Insert the default key for the table(e.g., '~'); 'X' and 'O' are not allowed: ");
        String tester = scanner.nextLine();
        if (tester.length() < 1) {
            XOGame.setDefaultKey('~');
            System.out.println("ERROR! The key you entered is a blank space! It has been set to: ~");
            return;
        }
        if (tester.length() > 1) {
            XOGame.setDefaultKey('~');
            System.out.println("ERROR! The key you entered contains more than one character! It has been set to: ~");
            return;
        }

        try {
            XOGame.setDefaultKey(tester.charAt(0));
            if (XOGame.getDefaultKey() == 'X' || XOGame.getDefaultKey() == 'O' ||
                    XOGame.getDefaultKey() == 'x' || XOGame.getDefaultKey() == 'o') {
                XOGame.setDefaultKey('~');
                throw new XOException("ERROR! You can't play with X or O as default key for the table! It has been set to: ~");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    XOPlayers() {
        showGameInfo(); // Instructions
        insertPlayer1(); // Player 1's name
        insertPlayer2(); // Player 2's name
        insertDefaultKey(); // Default key for the table
    }
}
