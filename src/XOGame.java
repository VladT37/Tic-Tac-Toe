class XOGame {
    private char[][] table;
    private boolean roundHasEnded = false;
    private boolean newRound = true;
    private static boolean turnPlayer1 = false;
    private int round = 1;

    static void setTurnPlayer1(boolean trueOrFalse) {
        turnPlayer1 = trueOrFalse;
    }

    private void fillTable() {
        // Iterates through the table and sets every cell to the default key
        // that has been set earlier by the user

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = XOKeys.defaultKey;
            }
        }
    }

    private void showGameInfo() {
        System.out.println("\nThe player whose key is assigned to 'X' moves first \n" +
                "Type 'Q' at any time to quit the game \n" +
                "Type 'C' after the round ends to continue playing \n" +
                "\nType a one digit number from 0 to 2 (inclusive) for each row \n" +
                "Followed by another one from 0 to 2 (inclusive) for each column \n" +
                "Separated by a new line\n\n");
    }

    private void showTurn() {
        // Checks if the boolean turnPlayer1 is true or false.
        // If it's true, then it's the first player's turn.
        // Otherwise, it's the second player's turn.

        if (turnPlayer1) {
            System.out.println("\nIt's " + Main.player1.getName()+"'s" + " Turn");
        } else {
            System.out.println("\nIt's " + Main.player2.getName()+"'s" + " Turn");
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

        System.out.println("\n" + Main.player1.getName() + " || " + Main.player1.getScore() + " - "
                + Main.player2.getScore() + " || " + Main.player2.getName());
    }

    private void showKeys() {
        // At the start of the round, this pops up, getting Player 1's name + its key and Player2's name + its key
        // and the default key that has been set earlier + the current Round, printing them all out.

        System.out.println("\n" + Main.player1.getName() + " - " + Main.player1.getKey() + " || " +
                Main.player2.getName() + " - " + Main.player2.getKey() + " || " + "DEFAULT KEY: " + XOKeys.getDefaultKey());
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
            String tester = XOKeys.scanner.nextLine();
            if (tester.length() < 1) {
                System.out.print("ERROR! The row you entered is a blank space! Pick another one: ");
                continue;
            }
            if (tester.length() > 1) {
                System.out.print("ERROR! The row you entered contains more than one character! Pick another one: ");
                continue;
            }
            row = tester.charAt(0);
            if (Character.hashCode(row) >= (int)'0' && Character.hashCode(row) <= (int)'9') {
                if (Character.hashCode(row) <= (int)'2') {
                    break;
                } else {
                    System.out.print("ERROR! The row you entered isn't in the range of 0-2! Pick another one: ");
                }
            } else if (Character.toUpperCase(row) == XOKeys.QUIT_KEY) {
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
            String tester = XOKeys.scanner.nextLine();
            if (tester.length() < 1) {
                System.out.print("ERROR! The column you entered is a blank space! Pick another one: ");
                continue;
            }
            if (tester.length() > 1) {
                System.out.print("ERROR! The column you entered contains more than one character! Pick another one: ");
                continue;
            }
            column = tester.charAt(0);
            if (Character.hashCode(column) >= (int)'0' && Character.hashCode(column) <= (int)'9') {
                if (Character.hashCode(column) <= (int)'2') {
                    break;
                } else {
                    System.out.print("ERROR! The column you entered isn't in the range of 0-2! Pick another one: ");
                }
            } else if (Character.toUpperCase(column) == XOKeys.QUIT_KEY) {
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

    private void checkRowColumn(int row, int column) {
        // This function verifies if the given row and column can fit the table.
        // Otherwise it throws an error based on the key that already occupied the given cell.
        // If the cell isn't occupied, then, that cell is gonna be marked by that player's key.

        if (table[row][column] == XOKeys.X_KEY) {
            if (Main.player1.getKey() == XOKeys.X_KEY) {
                System.out.println("ERROR! That cell is already occupied by " + Main.player1.getName() +
                        "'s key - " + Main.player1.getKey() + "\n");
                return;
            } else {
                System.out.println("ERROR! That cell is already occupied by " + Main.player2.getName() +
                        "'s key - " + Main.player2.getKey() + "\n");
                return;
            }
        }

        if (table[row][column] == XOKeys.O_KEY) {
            if (Main.player1.getKey() == XOKeys.O_KEY) {
                System.out.println("ERROR! That cell is already occupied by " + Main.player1.getName() +
                        "'s key - " + Main.player1.getKey() + "\n");
                return;
            } else {
                System.out.println("ERROR! That cell is already occupied by " + Main.player2.getName() +
                        "'s key - " + Main.player2.getKey() + "\n");
                return;
            }
        }

        if (turnPlayer1) {
            table[row][column] = Main.player1.getKey();
            turnPlayer1 = false;
        } else {
            table[row][column] = Main.player2.getKey();
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

        if ((table[0][0] == XOKeys.X_KEY && table[0][1] == XOKeys.X_KEY && table[0][2] == XOKeys.X_KEY) ||
                (table[1][0] == XOKeys.X_KEY && table[1][1] == XOKeys.X_KEY && table[1][2] == XOKeys.X_KEY) ||
                (table[2][0] == XOKeys.X_KEY && table[2][1] == XOKeys.X_KEY && table[2][2] == XOKeys.X_KEY) ||
                (table[0][0] == XOKeys.X_KEY && table[1][0] == XOKeys.X_KEY && table[2][0] == XOKeys.X_KEY) ||
                (table[0][1] == XOKeys.X_KEY && table[1][1] == XOKeys.X_KEY && table[2][1] == XOKeys.X_KEY) ||
                (table[0][2] == XOKeys.X_KEY && table[1][2] == XOKeys.X_KEY && table[2][2] == XOKeys.X_KEY) ||
                (table[0][0] == XOKeys.X_KEY && table[1][1] == XOKeys.X_KEY && table[2][2] == XOKeys.X_KEY) ||
                (table[0][2] == XOKeys.X_KEY && table[1][1] == XOKeys.X_KEY && table[2][0] == XOKeys.X_KEY)) {
            if (!turnPlayer1) {
                System.out.println(Main.player1.getName() + " HAS WON THE ROUND!");
                Main.player1.setScore(Main.player1.getScore()+1);
            } else {
                System.out.println(Main.player2.getName() + " HAS WON THE ROUND!");
                Main.player2.setScore(Main.player2.getScore()+1);
            }
            roundHasEnded = true;
            newRound = false;
            return true;
        }

        if ((table[0][0] == XOKeys.O_KEY && table[0][1] == XOKeys.O_KEY && table[0][2] == XOKeys.O_KEY) ||
                (table[1][0] == XOKeys.O_KEY && table[1][1] == XOKeys.O_KEY && table[1][2] == XOKeys.O_KEY) ||
                (table[2][0] == XOKeys.O_KEY && table[2][1] == XOKeys.O_KEY && table[2][2] == XOKeys.O_KEY) ||
                (table[0][0] == XOKeys.O_KEY && table[1][0] == XOKeys.O_KEY && table[2][0] == XOKeys.O_KEY) ||
                (table[0][1] == XOKeys.O_KEY && table[1][1] == XOKeys.O_KEY && table[2][1] == XOKeys.O_KEY) ||
                (table[0][2] == XOKeys.O_KEY && table[1][2] == XOKeys.O_KEY && table[2][2] == XOKeys.O_KEY) ||
                (table[0][0] == XOKeys.O_KEY && table[1][1] == XOKeys.O_KEY && table[2][2] == XOKeys.O_KEY) ||
                (table[0][2] == XOKeys.O_KEY && table[1][1] == XOKeys.O_KEY && table[2][0] == XOKeys.O_KEY)) {
            if (!turnPlayer1) {
                System.out.println(Main.player1.getName() + " HAS WON THE ROUND!");
                Main.player1.setScore(Main.player1.getScore()+1);
            } else {
                System.out.println(Main.player2.getName() + " HAS WON THE ROUND!");
                Main.player2.setScore(Main.player2.getScore()+1);
            }
            roundHasEnded = true;
            newRound = false;
            return true;
        }

        int countOccupiedCells = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == XOKeys.defaultKey) {
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
        // This function gets called at the end of the round and asks the user if he wants to continue the game.
        // it loops until the user types Q or C

        System.out.println("\nType 'Q' to quit the game \n" +
                "Type 'C' to continue playing \n");
        while (true) {
            String tester = XOKeys.scanner.nextLine();
            if (tester.length() < 1) {
                System.out.print("ERROR! The key you entered is a blank space! Pick another one: ");
                continue;
            }
            if (tester.length() > 1) {
                System.out.print("ERROR! The key you entered contains more than one character! Pick another one: ");
                continue;
            }

            char inputKey = tester.charAt(0);
            if (Character.toUpperCase(inputKey) == XOKeys.QUIT_KEY) {
                if (confirmExit()){
                    System.exit(0);
                } else {
                    continueRound();
                    break;
                }
            } else if (Character.toUpperCase(inputKey) == XOKeys.CONTINUE_KEY) {
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
            String tester = XOKeys.scanner.nextLine();
            if (tester.length() < 1) {
                System.out.print("ERROR! The key you entered is a blank space! Pick another one: ");
                continue;
            }
            if (tester.length() > 1) {
                System.out.print("ERROR! The key you entered contains more than one character! Pick another one: ");
                continue;
            }

            char inputKey = tester.charAt(0);
            if (Character.toUpperCase(inputKey) == XOKeys.YES_KEY) {
                System.out.println("Exiting the game...");
                return true;
            } else if (Character.toUpperCase(inputKey) == XOKeys.NO_KEY) {
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
        showKeys();
    }

    void startGame() {
        // This function is called once from the Main class, as the program runs

        showGameInfo(); // Instructions
        Main.player1.insertPlayer(); // Player 1's name
        Main.player2.insertPlayer(); // Player 2's name
        Main.keys.insertDefaultKey(); // Default key for the table
        fillTable(); // Fills the table with the default key that has been set earlier.
        showKeys(); // It shows the keys that have been set by the user
        while (newRound) { // As long as the user doesn't quit the game, this keeps going
            while (!roundHasEnded) { // This keeps going until the round ends
                showTable(); // Shows the current table after every move
                if (checkTable()) { // Checks if there's a winner or if the round has ended with a draw
                    break; // If so, the round ends.
                }
                showTurn(); // Shows whose turn it is.
                checkRowColumn(pickRow(),pickColumn());
                // After checking if the inputs aren't incorrect it checks if the row-column combination is correct
                // for the current table.
                // Otherwise, it throws an error, and that player has to repeat its turn.
            }
            showScore(); // By the end of the round, this shows the score
            gameOptions(); // Gives the user the options to either continue playing or to guit the game
        }
    }

    XOGame(){
        table = new char[3][3];
    }
}
