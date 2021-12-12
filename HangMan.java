import java.util.Scanner;

public class HangMan{ 
    private String secretWord;
    private String[] internalWord;
    private String[] indicies;
    private int playerCount;
    private boolean running;

    public void setSecretWord(){
        String[] words = {"Liberia", " Singapore", "America", "Japan", "Finland"};
        int randindex = (int)(Math.random() * words.length);
        
        this.secretWord = words[randindex].toLowerCase();
    }

    
    public void setPlayerCount(){
        Scanner scannerObj = new Scanner(System.in);
        System.out.print("How many players do you want to play: ");
        this.playerCount = scannerObj.nextInt();
    }


    public void setInternalWord(String delimiter){
        this.internalWord = this.secretWord.split("");

        for(int i = 0; i < internalWord.length; i++){
            internalWord[i] = delimiter;
        }
    }


    public Player[] createPlayers(){
        Player[] players = new Player[this.playerCount];
        for(int i = 0;  i < playerCount; i++){
            int playerid = i+1;

            Scanner scannerOb3 = new Scanner(System.in);
            System.out.print("player" + playerid + " name: ");
            String playerName = scannerOb3.nextLine();
            
            if(playerName.equals("")){
                playerName =  "player" +  playerid;
            }

            players[i] = new Player(playerName);
        }

        return players;
    }


    public void startGame(){
        this.setSecretWord();
        this.setPlayerCount();
        this.setInternalWord("_");
        boolean running = true;
        Player[] players = this.createPlayers();
        this.indicies = this.secretWord.split("");

        System.out.println("\nThe length of your word is " + this.secretWord.length());

        while(running){
            for(Player player : players){
                System.out.println("\nThe current player is: " + player.name);
                player.trialnum++;
                String currentPlayerGuess =  player.guess();
                

                if(this.secretWord.contains(currentPlayerGuess)){
                    player.score++;

                    for(int i = 0; i < this.indicies.length; i++){
                        if(this.indicies[i].equals(currentPlayerGuess)){
                            internalWord[i] = currentPlayerGuess;
                        }
                    }
                }else{
                    System.out.println("Incorrect word, try again");
                    continue;
                }
    
                if(String.join("", internalWord).equals(secretWord)){
                    System.out.println("\nGame Over!!!!!!");
                    System.out.println("\t winner:" + player.name);
                    System.out.println("\t score:" + player.score);
                    System.out.println("\t trials:" + player.trialnum);
                    
                    Scanner scannerObj3 = new Scanner(System.in);
                    System.out.print("Try again (Y/N): ");
                    String value = scannerObj3.next();

                    if(value.equalsIgnoreCase("y")){
                        this.setSecretWord();
                        this.setInternalWord("_");
                        this.indicies = this.secretWord.split("");

                        //reset trialnum
                        for(int i = 0; i < this.playerCount; i++){
                            players[i].trialnum = 0;
                        }

                    }

                    else if (value.equalsIgnoreCase("n")){
                        running = false;
                    }

                    break; 
                }
    
                System.out.println(String.join("", this.internalWord));
            }
        }

    }

}
