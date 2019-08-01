import java.util.Scanner;

class XOGame {
    private char[][] table;
    private static char defaultKey;
    private boolean roundHasEnded = false;
    private boolean newRound = true;
    private static boolean turnPlayer1 = false;
    private int round = 1;
    private Scanner scanner = new Scanner(System.in);

    static void setDefaultKey(char defaultKey) {
        XOGame.defaultKey = defaultKey;
    }

    static char getDefaultKey() {
        return defaultKey;
    }

    static void setTurnPlayer1(boolean trueOrFalse) {
        turnPlayer1 = trueOrFalse;
    }

    private void fillTable() {
        // Iterates through the table and sets every cell to the default key
        // that has been set earlier by the user

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = defaultKey;
            }
        }
    }

    private void showTurn() {
        // Checks if the boolean turnPlayer1 is true or false.
        // If it's true, then it's the first player's turn.
        // Otherwise, it's the second player's turn.

        if (turnPlayer1) {
            System.out.println("\nIt's " + Main.players.getPlayer1()+"'s" + " Turn");
        } else {
            System.out.println("\nIt's " + Main.players.getPlayer2()+"'s" + " Turn");
        }
        System.out.println("PICK A ROW AND A COLUMN");

    }

    private void showTable() {
        // Iterates through the current table while printing it out

        System.out.println("CURRENT TABLE");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(table[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private void showScore() {
        // By the end of the round, this pops up, getting the name of Player 1 + its score and Player 2 + its score
        // and printing them out

        System.out.println("\n" + Main.players.getPlayer1() + " || " + Main.players.getScorePlayer1() + " - "
                + Main.players.getScorePlayer2() + " || " + Main.players.getPlayer2());
    }

    private void showKeys() {
        // At the start of the round, this pops up, getting Player 1's name + its key and Player2's name + its key
        // and the default key that has been set earlier + Round 1, printing them all out.
        // This function is only called at the start of the game
        // As the game moves to the next rounds, the function that's gonna be called is the one below

        System.out.println("\n" + Main.players.getPlayer1() + " - " + Main.keys.getKeyPlayer1() + " || " +
                Main.players.getPlayer2() + " - " + Main.keys.getKeyPlayer2() + " || " + "DEFAULT KEY: " + XOGame.getDefaultKey());
        System.out.println("\nROUND 1\n");
    }

    private void showKeys(XOKeys newKeys) {
        // ^ as mentioned above
        // This function is getting called after playing more than a round.
        // Also, the parameter used, calls XOKeys' constructor, which shuffles players' keys and
        // determines who starts the round.

        System.out.println(Main.players.getPlayer1() + " - " + newKeys.getKeyPlayer1() + " || " +
                Main.players.getPlayer2() + " - " + newKeys.getKeyPlayer2() + " || " + "DEFAULT KEY: " + XOGame.getDefaultKey());
        System.out.println("\nROUND " + round + "\n");
    }

    private int pickRow() {
        // This function checks if the given row is a correct input.
        // The loop keeps going until the user gives a correct input for the row,
        // that being either a digit from 0 to 2 or C/Q.
        // At the end of the loop, it returns a converted int,
        // that will later be verified if it can fit the table.

        char row;
        while (true) {
            String tester = scanner.nextLine();
            if (tester.length() < 1) {
                System.out.print("ERROR! The row you entered is a blank space! Pick another one: ");
                continue;
            }
            if (tester.length() > 1) {
                System.out.print("ERROR! The row you entered contains more than one character! Pick another one: ");
                continue;
            }
            row = tester.charAt(0);
            if (Character.hashCode(row) >= 48 && Character.hashCode(row) <= 57) {
                if (Character.hashCode(row) <= 50) {
                    break;
                } else {
                    System.out.print("ERROR! The row you entered isn't in the range of 0-2! Pick another one: ");
                }
            } else if (row == 'Q' || row == 'q') {
                if (confirmExit()) {
                    System.exit(0);
                } else {
                    System.out.print("Pick a row: ");
                }
            } else {
                System.out.print("ERROR! The row you entered isn't a digit! Pick another one: ");
            }
        }
        return (int) row - '0';
    }

    private int pickColumn() {
        // This function checks if the given column is a correct input.
        // The loop keeps going until the user gives a correct input for the column,
        // that being either a digit from 0 to 2 or C/Q.
        // At the end of the loop, it returns a converted int,
        // that will later be verified if it can fit the table.

        char column;
        while (true) {
            String tester = scanner.nextLine();
            if (tester.length() < 1) {
                System.out.print("ERROR! The column you entered is a blank space! Pick another one: ");
                continue;
            }
            if (tester.length() > 1) {
                System.out.print("ERROR! The column you entered contains more than one character! Pick another one: ");
                continue;
            }
            column = tester.charAt(0);
            if (Character.hashCode(column) >= 48 && Character.hashCode(column) <= 57) {
                if (Character.hashCode(column) <= 50) {
                    break;
                } else {
                    System.out.print("ERROR! The column you entered isn't in the range of 0-2! Pick another one: ");
                }
            } else if (column == 'Q' || column == 'q') {
                if (confirmExit()){
                    System.exit(0);
                } else {
                    System.out.print("ERROR! Pick a column: ");
                }
            } else {
                System.out.print("ERROR! The column you entered isn't a digit! Pick another one: ");
            }
        }
        return (int) column - '0';
    }

    private void checkRowColumn(int row, int column) throws XOException {
        // This function verifies if the given row and column can fit the table.
        // Otherwise it throws an exception based on the key that already occupied the given cell.
        // If the cell isn't occupied, then, that cell is gonna be marked by that player's key.

        if (table[row][column] == 'X') {
            if (Main.keys.getKeyPlayer1() == 'X') {
                throw new XOException("ERROR! That cell is already occupied by " + Main.players.getPlayer1() +
                        "'s key - " + Main.keys.getKeyPlayer1() + "\n");
            } else {
                throw new XOException("ERROR! That cell is already occupied by " + Main.players.getPlayer2() +
                        "'s key - " + Main.keys.getKeyPlayer2() + "\n");
            }
        }

        if (table[row][column] == 'O') {
            if (Main.keys.getKeyPlayer1() == 'O') {
                throw new XOException("ERROR! That cell is already occupied by " + Main.players.getPlayer1() +
                        "'s key - " + Main.keys.getKeyPlayer1() + "\n");
            } else {
                throw new XOException("ERROR! That cell is already occupied by " + Main.players.getPlayer2() +
                        "'s key - " + Main.keys.getKeyPlayer2() + "\n");
            }
        }

        if (turnPlayer1) {
            table[row][column] = Main.keys.getKeyPlayer1();
            turnPlayer1 = false;
        } else {
            table[row][column] = Main.keys.getKeyPlayer2();
            turnPlayer1 = true;
        }
        System.out.println();
    }

    private boolean checkTable() {
        // This function checks if the player whose key is 'X' has got 3 in a line
        // Then checks if the player whose key is 'O' has got 3 in a line
        // If so, the game has a winner.
        // After these two checks, it iterates through the table and counts all the occupied cells
        // If the number of occupied cells is equal to 9 (all cells of the table), the game ends in a draw
        // If none of the above are true, the function returns false

        if ((table[0][0] == 'X' && table[0][1] == 'X' && table[0][2] == 'X') ||
                (table[1][0] == 'X' && table[1][1] == 'X' && table[1][2] == 'X') ||
                (table[2][0] == 'X' && table[2][1] == 'X' && table[2][2] == 'X') ||
                (table[0][0] == 'X' && table[1][0] == 'X' && table[2][0] == 'X') ||
                (table[0][1] == 'X' && table[1][1] == 'X' && table[2][1] == 'X') ||
                (table[0][2] == 'X' && table[1][2] == 'X' && table[2][2] == 'X') ||
                (table[0][0] == 'X' && table[1][1] == 'X' && table[2][2] == 'X') ||
                (table[0][2] == 'X' && table[1][1] == 'X' && table[2][0] == 'X')) {
            if (!turnPlayer1) {
                System.out.println(Main.players.getPlayer1() + " HAS WON THE ROUND!");
                Main.players.setScorePlayer1(Main.players.getScorePlayer1()+1);
            } else {
                System.out.println(Main.players.getPlayer2() + " HAS WON THE ROUND!");
                Main.players.setScorePlayer2(Main.players.getScorePlayer2()+1);
            }
            roundHasEnded = true;
            newRound = false;
            return true;
        }

        if ((table[0][0] == 'O' && table[0][1] == 'O' && table[0][2] == 'O') ||
                (table[1][0] == 'O' && table[1][1] == 'O' && table[1][2] == 'O') ||
                (table[2][0] == 'O' && table[2][1] == 'O' && table[2][2] == 'O') ||
                (table[0][0] == 'O' && table[1][0] == 'O' && table[2][0] == 'O') ||
                (table[0][1] == 'O' && table[1][1] == 'O' && table[2][1] == 'O') ||
                (table[0][2] == 'O' && table[1][2] == 'O' && table[2][2] == 'O') ||
                (table[0][0] == 'O' && table[1][1] == 'O' && table[2][2] == 'O') ||
                (table[0][2] == 'O' && table[1][1] == 'O' && table[2][0] == 'O')) {
            if (!turnPlayer1) {
                System.out.println(Main.players.getPlayer1() + " HAS WON THE ROUND!");
                Main.players.setScorePlayer1(Main.players.getScorePlayer1()+1);
            } else {
                System.out.println(Main.players.getPlayer2() + " HAS WON THE ROUND!");
                Main.players.setScorePlayer2(Main.players.getScorePlayer2()+1);
            }
            roundHasEnded = true;
            newRound = false;
            return true;
        }

        int countOccupiedCells = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == defaultKey) {
                    return false;
                } else {
                    countOccupiedCells++;
                }
            }
        }
        if (countOccupiedCells == 9) {
            System.out.println("NOBODY HAS WON THE ROUND!");
            roundHasEnded = true;
            newRound = false;
            return true;
        }
        return false;
    }

    private void gameOptions() {
        System.out.println("\nType 'Q' to quit the game \n" +
                "Type 'C' to continue playing \n");
        while (true) {
            String tester = scanner.nextLine();
            if (tester.length() < 1) {
                System.out.print("ERROR! The key you entered is a blank space! Pick another one: ");
                continue;
            }
            if (tester.length() > 1) {
                System.out.print("ERROR! The key you entered contains more than one character! Pick another one: ");
                continue;
            }

            char key = tester.charAt(0);

            if (key == 'Q' || key == 'q') {
                if (confirmExit()){
                    System.exit(0);
                } else {
                    continueRound();
                    break;
                }
            } else if (key == 'C' || key == 'c') {
                continueRound();
                break;
            } else {
                System.out.print("ERROR! Wrong key! Choose between Q to Quit or C to Continue: ");
            }
        }
    }

    private boolean confirmExit() {
        // This function is called after the user types 'Q' or 'q'
        // It loops until the user types either Y to confirm or N to return to the game

        System.out.print("Are you sure you want to exit the game? (Y/N): ");
        while (true) {
            String tester = scanner.nextLine();
            if (tester.length() < 1) {
                System.out.print("ERROR! The key you entered is a blank space! Pick another one: ");
                continue;
            }
            if (tester.length() > 1) {
                System.out.print("ERROR! The key you entered contains more than one character! Pick another one: ");
                continue;
            }
            char key = tester.charAt(0);
            if (key == 'Y' || key == 'y') {
                System.out.println("Exiting the game...");
                return true;
            } else if (key == 'N' || key == 'n') {
                return false;
            } else {
                System.out.print("ERROR! The key you entered isn't Y or N! Choose again!");
            }
        }
    }

    private void continueRound() {
        // This function is called from gameOptions() when the user types 'C' or 'c'
        // Or when the user doesn't confirm the exit
        // This function prints the round that it moves to
        // It also creates a new table and fills it with the default key that has been set earlier
        // By the end of this function, it shows the new keys that have been set ('X' or 'O')

        System.out.println("\nMoving to ROUND " + ++round + "...");
        newRound = true;
        roundHasEnded = false;
        table = new char[3][3];
        fillTable();
        XOKeys newKeys = new XOKeys();
        showKeys(newKeys);
    }

    void startGame() {
        // This function is called once from the Main class, as the program runs

        showKeys(); // It shows the keys that have been set by the user
        while (newRound) { // As long as the user doesn't quit the game, this keeps going
            while (!roundHasEnded) { // This keeps going until the round ends
                showTable(); // Shows the current table after every move
                if (checkTable()) { // Checks if there's a winner or if the round has ended with a draw
                    break; // If so, the round ends.
                }
                showTurn(); // Shows whose turn it is.
                try {
                    checkRowColumn(pickRow(),pickColumn()); // After checking if the inputs aren't incorrect
                } catch (Exception e) {                     // it checks if the row-column combination is correct
                    System.out.println(e.getMessage());     // on the table. Otherwise, it throws an exception, and
                }                                           // that player has to repeat its turn.
            }
            showScore(); // By the end of the round, this shows the score
            gameOptions(); // Gives the user the options to either continue playing or to guit the game
        }
    }

    XOGame(){
        table = new char[3][3];
        fillTable(); // Fills the table with the default key that has been set earlier.
    }
}
