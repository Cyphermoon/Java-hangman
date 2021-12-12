import java.util.Scanner;

public class Player{
    String name;
    int score;
    int trialnum;

    public Player(String name){
        this.name = name;
    }

    public String guess(){
        Scanner scannerObj = new Scanner(System.in);
        System.out.print("Enter your guess: ");
        String value = scannerObj.nextLine();
        return value;
    }
}