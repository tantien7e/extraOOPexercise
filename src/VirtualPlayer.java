public class VirtualPlayer extends Player{
    public static int count = 0;
    String[] failingMessagesArray = {"So sad! I lose huhu", "You just got lucky this time", "Hic, how unfortunate!", "I don't wanna play anymore"};
    String showEmotion;
    
    public VirtualPlayer(){
        super();
        this.setPlayerName("bot"+ count);
        this.setShowEmotion(this.failingMessagesArray[count]);
    }

    public String displayMessage(){
        return this.showEmotion;
    }

    public String getShowEmotion() {
        return showEmotion;
    }
    public void setShowEmotion(String showEmotion) {
        this.showEmotion = showEmotion;
    }
}
