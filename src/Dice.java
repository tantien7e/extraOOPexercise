import java.util.Random;

public class Dice {
    private int biasFace;

    public Dice(int biasFace){
        setBiasFace(biasFace);
    }

    public int role(){
        Random random = new Random();
        double rant = (int) (100*random.nextDouble()+0.0001);
        if(16*(biasFace-1)<rant && rant <= 100-16*(6-biasFace)) return biasFace;
        else if(rant <= 16*(biasFace-1)) return (int) Math.ceil(rant/16);
        else return (int) (biasFace + Math.ceil((rant -16*(biasFace-1)-20)/16));
    }

    public int getBiasFace() {
        return biasFace;
    }

    public void setBiasFace(int biasFace) {
        this.biasFace = biasFace;
    }

    
}
