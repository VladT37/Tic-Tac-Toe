class XOPlayer1 extends XOPlayers {
    void insertPlayer() {
        // Sets the string 'player 1' to the name given by the user.
        // Then it verifies if the string is shorter than a character or longer than 32 characters
        // If so, it throws an error and the string is set to - 'Player 1'.

        System.out.print("Insert name for Player 1 (1-32 characters): ");
        setName(XOKeys.scanner.nextLine());
        if (getName().trim().length() <= 0) {
            setName("Player 1");
            System.out.println("ERROR! Player 1's name is too short! It has been set to: Player 1");
            return;
        }
        if (getName().trim().length() > 32) {
            setName("Player 1");
            System.out.println("ERROR! Player 1's name is too long! It has been set to: Player 1");
        }
    }
}
