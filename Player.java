/*
 * Made by Artem Ivanov
 * Documented on 2/8/25
 * 
 * A class for the players in Blackjack that extends the Dealer class
 * Includes a int to number the players and an int to keep track of how many times the player has won
 * Overrides displayHand() to no longer take hidden cards into account, as players don't hide their cards
 * Also overrides getScore() to no longer take hidden cards into account
 */

public class Player extends Dealer{
    private int playerNum;
    private int wins;
    public Player(int num, int bustScore){
        super(bustScore);
        playerNum = num;
        wins = 0;
    }
    
    public int getPlayerNum(){
        return playerNum;
    }
    
    public int getWins(){
        return wins;
    }
    
    public void addWin(){
        wins++;
    }
    
    @Override
    public void displayHand(){
        System.out.print("Player " + playerNum + "'s hand contains: ");
        
        for(int i = 0; i < this.getHand().size(); i++){
            System.out.print(this.getHand().get(i));
            
            if(i != this.getHand().size() - 1){
                System.out.print(", ");
            }
        }
        
        System.out.print(" (" + getScore() + " points)\n");
    }
    
    @Override
    public int getScore(){
        int score = 0;
        int acesInHand = 0;
        for(BlackjackCard c : this.getHand()){
            int cardScore = c.getScore();
            if(cardScore == 11){
                acesInHand++;
            }
            score += cardScore;
        }
        
        while(acesInHand > 0 && score > this.getBustScore()){
            acesInHand--;
            score -= 10;
        }
        
        return score;
    }
}