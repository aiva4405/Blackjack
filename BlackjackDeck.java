/*
 * Made by Artem Ivanov
 * Documented on 2/8/25
 * 
 * A variant of the Deck class that replaces all Card references with BlackjackCard
 * Removes the selection/insertion sort methods as well as the sorting methods; neither are necessary in Blackjack
 */

import java.util.ArrayList;
public class BlackjackDeck{
    private ArrayList<BlackjackCard> cards;
    
    public BlackjackDeck(){
        resetDeck();
    }
    
    public BlackjackCard getCard(int index){
        return cards.get(index);
    }
    
    public ArrayList getCardList(){
        return cards;
    }
    
    public BlackjackCard drawCard(boolean flipCard){
        if(cards.size() > 0){
            if(flipCard){
                BlackjackCard temp = cards.remove(0);
                temp.flipCard();
                return temp;
            }
            else {
                return cards.remove(0);
            }
        }
        else {
            resetDeck();
            shuffleDeck();
            System.out.println("New deck");
            if(flipCard){
                BlackjackCard temp = cards.remove(0);
                temp.flipCard();
                return temp;
            }
            else {
               return cards.remove(0); 
            }
        }
    }
    
    public void setCardList(ArrayList<BlackjackCard> newList){
        cards = newList;
    }
    
    public void resetDeck(){
        cards = new ArrayList<>();
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        int value = 0;
        
        for(String suit : suits){
            for(String rank: ranks){
                cards.add(new BlackjackCard(suit, rank, value));
                value++;
            }
        }
    }
    
    public void shuffleDeck(){
        ArrayList<BlackjackCard> oldList = cards;
        ArrayList<BlackjackCard> newList = new ArrayList<>();
        
        while(oldList.size() > 0){
            int randIndex = (int)(Math.random() * oldList.size());
            newList.add(oldList.remove(randIndex));
        }
        
        cards = newList;
    }
    
} 