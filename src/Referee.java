import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Referee {
    ArrayList<Player> listPlayers = new ArrayList<>();
    ArrayList<VirtualPlayer> listVirtualPlayers = new ArrayList<>();
    ArrayList<Dice> listDices = new ArrayList<>();
    ArrayList<Integer> iterationPoint = new ArrayList<>();
    int numHumanPlayers;
    int numVirtualPlayers; 
    boolean isWON = false;
    ArrayList<Player> wonPlayer = new ArrayList<>();

    public void playerInitiate(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("How many human players?: ");
        numHumanPlayers = scanner.nextInt();
        scanner.nextLine();
        while(numHumanPlayers > Player.MAX_NUMBER_PLAYERS){
            System.out.println("The number of players exceeded the maximum! Please re-enter the number: ");
            numHumanPlayers = scanner.nextInt();
            scanner.nextLine();
        }
        while(numHumanPlayers < 0){
            System.out.println("The number of players must not be smaller than 0! Please re-enter the number: ");
            numHumanPlayers = scanner.nextInt();
            scanner.nextLine();
        }
        numVirtualPlayers = Player.MAX_NUMBER_PLAYERS - numHumanPlayers;

        for(int i = 0; i<numHumanPlayers; i++){
            Player newPlayer = new Player();
            System.out.print("Enter player's " + (i+1) + " name: ");
            newPlayer.setPlayerName(scanner.nextLine());
            listPlayers.add(newPlayer);
        }
        for(int i = 0; i<numVirtualPlayers; i++){
            VirtualPlayer newVirtualPlayer = new VirtualPlayer();
            listPlayers.add(newVirtualPlayer);
            listVirtualPlayers.add(newVirtualPlayer);
            VirtualPlayer.count++;
        }
        scanner.close();
    }

    public void diceInitiate(){
        Dice dice1 = new Dice(1);
        Dice dice2 = new Dice(2);
        Dice dice3 = new Dice(3);
        Dice dice4 = new Dice(4);
        listDices.add(dice1);
        listDices.add(dice2);
        listDices.add(dice3);
        listDices.add(dice4);
    }
    public void iterationPointInitiate(){
        iterationPoint.add(0);
        iterationPoint.add(0);
        iterationPoint.add(0);
        iterationPoint.add(0);
    }

    public int getPoint(){
        Random random = new Random();
        int rantDice = (int) Math.floor(4*random.nextDouble());
        return listDices.get(rantDice).role();
        
    }

    public void printContinue(int iteration){
        System.out.println("******************ITERATION"+ iteration +"******************");
        System.out.println("Name\t\t"+ "Point\t\t" + "Total Point");
        for(int i = 0; i< Player.MAX_NUMBER_PLAYERS; i++){
            System.out.println(listPlayers.get(i).getPlayerName() + "\t\t" + "+" + iterationPoint.get(i)+ "\t\t" + listPlayers.get(i).getCurrentPoint());
        }
        System.out.println("************************************************");
    }

    public void printWon(int iteration){
        printContinue(iteration);
        System.out.print("The winner is: ");
        for(int i =0; i<wonPlayer.size(); i++) {
            System.out.print(wonPlayer.get(i).getPlayerName());
            System.out.println();
        }
        for(int i = 0; i<numVirtualPlayers; i++){
            if(!wonPlayer.contains(listVirtualPlayers.get(i))){
                System.out.println(listVirtualPlayers.get(i).getPlayerName() + ": "+ listVirtualPlayers.get(i).displayMessage());
            }
        }
    }

    public void play(){
        playerInitiate();
        diceInitiate();
        iterationPointInitiate();
        int iteration = 0;
        while(!isWON){
            for(int i = 0; i<Player.MAX_NUMBER_PLAYERS; i++){
                int point = getPoint();
                iterationPoint.set(i, point);
                if(point +listPlayers.get(i).getCurrentPoint() == 21) {
                    wonPlayer.add(listPlayers.get(i));
                    listPlayers.get(i).setCurrentPoint(21);
                    isWON = true;
                }
                else if(point + listPlayers.get(i).getCurrentPoint() > 21) listPlayers.get(i).setCurrentPoint(0);
                else listPlayers.get(i).setCurrentPoint(point + listPlayers.get(i).getCurrentPoint());
            }
        if(!isWON) {
            printContinue(iteration);
            iteration++;
        }
        else printWon(iteration);
        }
    }
}
