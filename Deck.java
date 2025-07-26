/*
 * Made by Artem Ivanov
 * Documented on 2/8/25
 * 
 * A basic deck class that utilizes the Card class
 * Stores an Arraylist of Cards that is filled in order using resetDeck()
 * drawCard() removes the first Card from the list, refilling the deck if it's empty
 * getIndexFromSuitRank() is used for binary search
 * Includes a linear and binary Card search
 * Also includes selection sort and insertion sort functions
 * shuffleDeck() takes the current deck and randomly moves the cards around
 * The main method tests the functionality of the sort and search functions
 */

import java.util.ArrayList;
public class Deck{
    private ArrayList<Card> cards;
    
    public Deck(){
        resetDeck();
    }
    
    public Card getCard(int index){
        return cards.get(index);
    }
    
    public ArrayList getCardList(){
        return cards;
    }
    
    public Card drawCard(boolean flipCard){
        if(cards.size() > 0){
            if(flipCard){
                Card temp = cards.remove(0);
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
                Card temp = cards.remove(0);
                temp.flipCard();
                return temp;
            }
            else {
               return cards.remove(0); 
            }
        }
    }
    
    public void setCardList(ArrayList<Card> newList){
        cards = newList;
    }
    
    public void resetDeck(){
        cards = new ArrayList<>();
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        int value = 0;
        
        for(String suit : suits){
            for(String rank: ranks){
                cards.add(new Card(suit, rank, value));
                value++;
            }
        }
    }
    
    public int getIndexFromSuitRank(String suit, String rank){
        int multiplier = 0;
        int index = 0;
        if(suit.equals("Diamonds")){
            multiplier = 1;
        }
        else if(suit.equals("Hearts")){
            multiplier = 2;
        }
        else if(suit.equals("Spades")){
            multiplier = 3;
        }
        
        if(rank.equals("Ace")){
            index = 0;
        }
        else if(rank.equals("Jack")){
            index = 10;
        }
        else if(rank.equals("Queen")){
            index = 11;
        }
        else if(rank.equals("King")){
            index = 12;
        }
        else{
            index = Integer.valueOf(rank) - 1;
        }
        
        return index + (13 * multiplier);
    }
    
    public int findCardLinear(String suit, String rank){
        int index = 0;
        
        for(Card c : cards){
            if(c.getSuit().equals(suit) && c.getRank().equals(rank)){
                return index;
            }
            index++;
        }
        
        return -1;
    }
    
    public int findCardBinary(String suit, String rank){
        int lowBound = 0;
        int highBound = cards.size() - 1;
        
        while(lowBound <= highBound){
            int center = lowBound + (highBound - lowBound) / 2;
            
            Card centerCard = getCard(center);
            int targetIndex = getIndexFromSuitRank(suit, rank);
            
            if(centerCard.getValue() == targetIndex){
                return center;
            }
            
            if(centerCard.getValue() < targetIndex){
                lowBound = center + 1;
            }
            else {
                highBound = center - 1;
            }
        }
        return -1;
    }
    
    public void shuffleDeck(){
        ArrayList<Card> oldList = cards;
        ArrayList<Card> newList = new ArrayList<>();
        
        while(oldList.size() > 0){
            int randIndex = (int)(Math.random() * oldList.size());
            newList.add(oldList.remove(randIndex));
        }
        
        cards = newList;
    }
    
    public void selectionSort(){
        for(int i = 0; i < cards.size(); i++){
            int minIndex = i;
            
            for(int j = i + 1; j < cards.size(); j++){
                if(getCard(j).getValue() < getCard(minIndex).getValue()){
                    minIndex = j;
                }
            }
            
            Card temp = getCard(i);
            cards.set(i, getCard(minIndex));
            cards.set(minIndex, temp);
        }
    }
    
    public void insertionSort(){
       for(int i = 0; i < cards.size(); i++){
           Card key = getCard(i);
           int leftIndex = i - 1;
           
           while(leftIndex >= 0 && getCard(leftIndex).getValue() > key.getValue()){
               cards.set(leftIndex + 1, getCard(leftIndex));
               leftIndex--;
           }
           cards.set(leftIndex + 1, key);
       }
    }

    // Tester main method
    public static void main(String[] args){
        Deck test = new Deck();
        int linIndex = test.findCardLinear("Hearts", "5");
        int binIndex = test.findCardBinary("Hearts", "5");
        
        System.out.println(test.getCard(linIndex));
        System.out.println(test.getCard(binIndex));
        System.out.println("\n");
        
        linIndex = test.findCardLinear("Clubs", "Jack");
        binIndex = test.findCardBinary("Clubs", "Jack");
        
        System.out.println(test.getCard(linIndex));
        System.out.println(test.getCard(binIndex));
        System.out.println("\n");
        
        test.shuffleDeck();
    
        test.selectionSort();
        System.out.println("\n");
        for(int i = 0; i < test.cards.size(); i++){
            System.out.println(test.getCard(i));
        }
        
        test.shuffleDeck();
        
        test.insertionSort();
        System.out.println("\n");
        for(int j = 0; j < test.cards.size(); j++){
            System.out.println(test.getCard(j));
        }
    } 
} 