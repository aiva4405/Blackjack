/*
 * Made by Artem Ivanov
 * Documented on 2/8/25
 * 
 * An extension of the Card class that includes extra features necessary for Blackjack
 * A new score integer variable
 * getScoreFromRank() uses the rank of the card to set the score as to what it should be for Blackjack
 */

public class BlackjackCard extends Card{
    private int score; // Score of a card in blackjack(1-11)
    
    public BlackjackCard(String suit, String rank, int value){
        super(suit,rank,value);
        score = getScoreFromRank(rank);
    }
    
    public int getScore(){
        return score;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public int getScoreFromRank(String rank){
        int score = 0;
        if(rank.equals("Ace")){
            score = 11;
        }
        else if(rank.equals("Jack") || rank.equals("King") || rank.equals("Queen")){
            score = 10;
        }
        else {
            score = Integer.valueOf(rank);
        }
        return score;
    }
}