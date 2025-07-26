/*
 * Made by Artem Ivanov
 * Documented on 2/8/25
 * 
 * A basic card class that:
 *  Stores a string that represents the suit of the card, as well as one that represents the rank
 *  Has an integer that helps sort the deck for binary search
 *  Uses a boolean variable to keep track of if the card is face down or face up
 *  Overrides toString to print the card in terms of how you would say it vocally
 *  Overrides equals to take the parameters into account
 *  Contains all accessor and mutator methods
 */

public class Card{
    private String suit; // Hearts, Spades, etc.
    private String rank; // 1, 6, Jack, etc.
    private int value; // Value related to binary search
    private boolean isHidden; // Represents whether the card is face down or face up
    // Constructor
    public Card(String suit, String rank, int value){
        this.suit = suit;
        this.rank = rank;
        this.value = value;
        isHidden = false;
    }
    
    @Override
    public String toString(){
        return rank + " of " + suit;
    }
    
    @Override
    public boolean equals(Object otherObj){
        boolean isEqual = false;
        // Make sure it's not null
        if(otherObj == null){
            return isEqual;
        }
        // Check if it is a Card object
        if(otherObj.getClass() != this.getClass()){
            return isEqual;
        }
        // Check if self
        if(otherObj == this){
            isEqual = true;
            return isEqual;
        }
        
        Card otherCard = (Card) otherObj;
        //Check fields
        boolean sameSuit = otherCard.getSuit().equals(this.getSuit());
        boolean sameRank = otherCard.getRank().equals(this.getRank());
        
        isEqual = sameSuit && sameRank;
        return isEqual;
    }
    
    // Accessor Methods
    public String getSuit(){
        return suit;
    }
    
    public String getRank(){
        return rank;
    }
    
    public int getValue(){
        return value;
    }
    
    public boolean isHidden(){
        return isHidden;
    }
    
    // Mutator Methods
    public void setSuit(String suit){
        this.suit = suit;
    }
    
    public void setRank(String rank){
        this.rank = rank;
    }
    
    public void setValue(int value){
        this.value = value;
    }
    
    public void flipCard(){
        isHidden = !isHidden;
    }
}