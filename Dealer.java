/*
 * Made by Artem Ivanov
 * Documented on 2/8/25
 * 
 * A class for the Dealer in Blackjack
 * Has an Arraylist of BlackjackCards to keep track of the dealer's hand
 * Also has an int variable to make it so that you can customize the max score in a game
 * hit() takes a BlackjackCard and adds it to the hand
 * displayHand() prints out the full hand, hiding cards that are face down, also prints out the total score of the hand 
 * getScore() gets the total score of the hand, making it -1 if there are any hidden cards
 */
import java.util.ArrayList;
public class Dealer{
    private ArrayList<BlackjackCard> hand;
    private int bustScore;
    
    public Dealer(int bust){
        hand = new ArrayList<BlackjackCard>();
        bustScore = bust;
    }
    
    public ArrayList<BlackjackCard> getHand(){
        return hand;
    }
    
    public int getBustScore(){
        return bustScore;
    }
    
    public void clearHand(){
        hand = new ArrayList<BlackjackCard>();
    }
    
    public void hit(BlackjackCard c){
        hand.add(c);
    }
    
    public void displayHand(){
        System.out.print("The dealer's hand contains: ");
        
        for(int i = 0; i < hand.size(); i++){
            if(!hand.get(i).isHidden()){
                System.out.print(hand.get(i));
            }
            else {
                System.out.print("? of ?");
            }
            if(i != hand.size() - 1){
                System.out.print(", ");
            }
        }
        
        if(getScore() < 0){
            System.out.print(" (? points)\n");
        }
        else {
            System.out.print(" (" + getScore() + " points)\n");
        }
    }
    
    public int getScore(){
        int score = 0;
        int acesInHand = 0;
        boolean hasHiddenCard = false;
        for(BlackjackCard c : hand){
            if(c.isHidden()){
                hasHiddenCard = true;
            }
            int cardScore = c.getScore();
            if(cardScore == 11){
                acesInHand++;
            }
            score += cardScore;
        }
        
        while(acesInHand > 0 && score > bustScore){
            acesInHand--;
            score -= 10;
        }
        
        if(hasHiddenCard){
            score = -1;
        }
        return score;
    }
}