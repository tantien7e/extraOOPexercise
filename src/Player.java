public class Player {
    String playerName;
    int currentPoint;
    public static final int MAX_NUMBER_PLAYERS = 4;
    
    public Player(String playerName){
        this.setPlayerName(playerName);
        setCurrentPoint(0);
    }

    public Player(){
        setCurrentPoint(0);
    }

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    public int getCurrentPoint() {
        return currentPoint;
    }
    public void setCurrentPoint(int point) {
        this.currentPoint = point;
    }
    
}
