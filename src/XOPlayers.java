abstract class XOPlayers {
    private String name;
    private int score;
    private char key;

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }

    char getKey() {
        return key;
    }

    void setKey(char key) {
        this.key = key;
    }
}
