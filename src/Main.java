public class Main {
    static XOPlayer1 player1 = new XOPlayer1();
    static XOPlayer2 player2 = new XOPlayer2();
    static XOKeys keys = new XOKeys();
    private static XOGame game = new XOGame();

    public static void main(String[] args) {
        game.startGame();
    }
}
