class XOPlayer2 extends XOPlayers {
    void insertPlayer() {
        // Sets the string 'player 2' to the name given by the user.
        // Then it verifies if the string is shorter than a character or longer than 32 characters.
        // If so, it throws an error and the string is set to - 'Player 2'.

        System.out.print("Insert name for Player 2 (1-32 characters): ");
        setName(XOKeys.scanner.nextLine());
        if (getName().trim().length() <= 0) {
            setName("Player 2");
            System.out.println("ERROR! Player 2's name is too short! It has been set to: Player 2");
            return;
        }
        if (getName().trim().length() > 32) {
            setName("Player 2");
            System.out.println("ERROR! Player 2's name is too long! It has been set to: Player 2");
        }
    }
}
