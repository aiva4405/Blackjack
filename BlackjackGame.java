/*
 * Made by Artem Ivanov
 * Documented on 2/8/25
 * 
 * Plays through a game of Blackjack using a text interface
 * Includes a BlackjackDeck, a Dealer, as well as an Arraylist of Players and an int to keep track of the round
 * 
 * Starts off by asking for how many players there need to be, then populates the Arraylist with the correct amount
 * Next the round loop starts:
 * Dealer recieves 2 cards, one visible and one hidden
 * Then each player gets a turn
 * For each turn a player gets 2 cards, then chooses whether to hit or not if they don't already have blackjack(21)
 * After everyone gets a turn the dealer reveals their other card and draws if they have less than 17
 * Once the dealer draws or doesn't, their score is compared to every player to see if they won or lost
 * Then the players have a choice of whether or not to continue playing
 * If they decide to stop playing, their wins are displayed and the game ends
 */

import java.util.ArrayList;
import java.util.Scanner;
public class BlackjackGame{
    private static BlackjackDeck gameDeck = new BlackjackDeck();
    private static Dealer dealer = new Dealer(21);
    private static ArrayList<Player> playerList = new ArrayList<Player>();
    private static int round = 1;
    
    private static void wait(int seconds){
        try {
          Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
    }
    
    public static void main(String[] args){
        System.out.println("IMPORTANT: Do not type in the response box if the game hasn't asked, you will be responding to a question you don't know yet");
        wait(5);
        System.out.print("\u000C"); //Clears screen
        gameDeck.shuffleDeck();
        Scanner input = new Scanner(System.in);
        //Choose player amount
        int playerCount = 0;
        boolean correctInput = false;
        while(correctInput != true){
            System.out.println("Input player amount:");
            if(input.hasNextInt()){
                int temp = input.nextInt();
                if(temp > 0){
                correctInput = true;
                playerCount = temp;
                }
                else {
                    System.out.println("Error: input must be a positive integer");
                }
            }
            else{
                System.out.println("Error: input must be a positive integer");
                input.nextLine();
            }
        }
        
        for(int i = 1; i <= playerCount; i++){
            Player newPlayer = new Player(i, 21);
            playerList.add(newPlayer);
        }
        //Play round loop
        System.out.print("\u000C"); 
        boolean wantsToPlay = true;
        while(wantsToPlay){
           dealer.hit(gameDeck.drawCard(false));
           dealer.hit(gameDeck.drawCard(true));
           System.out.println("Round " + round);
           for(int i = 0; i<playerList.size(); i++){
               Player currentPlayer = playerList.get(i);
               currentPlayer.hit(gameDeck.drawCard(false));
               currentPlayer.hit(gameDeck.drawCard(false));
               boolean continuePlayerLoop = true;
               while(continuePlayerLoop){
               System.out.print("Player " + currentPlayer.getPlayerNum() + "'s turn");
               System.out.println(" (" + currentPlayer.getWins() + " Wins)");
               dealer.displayHand();
               currentPlayer.displayHand();
               int currScore = currentPlayer.getScore();
               
               if(currScore < 21){
                   System.out.println("Would you like to hit?(Type y or n)");
                   correctInput = false;
                   String temp = input.next();
                   while(correctInput != true){
                       if(temp.equals("y") || temp.equals("n")){
                           correctInput = true;
                       }
                       else {
                           System.out.println("Error: input must be y or n");
                           System.out.println("Would you like to hit?(Type y or n)");
                           temp = input.next();
                       }
                   }
                   if(temp.equals("y")){
                       currentPlayer.hit(gameDeck.drawCard(false));
                       System.out.print("\u000C");
                   }
                   else if(temp.equals("n")){
                       continuePlayerLoop = false;
                       System.out.print("\u000C");
                   }
               }
               else if(currScore == 21){
                   System.out.println("You have Blackjack!");
                   continuePlayerLoop = false;
               }
               else {
                   System.out.println("You busted");
                   continuePlayerLoop = false;
               }
               }
               playerList.set(i, currentPlayer);
           }
           System.out.print("\u000C");
           dealer.getHand().get(1).flipCard();
           dealer.displayHand();
           wait(3);
           while(dealer.getScore() < 17){
               System.out.print("\u000C");
               dealer.hit(gameDeck.drawCard(false));
               dealer.displayHand();
               wait(3);
           }
           
           int dealerScore = dealer.getScore();
           if(dealerScore > 21){
               System.out.println("The dealer busted");
           }
           for(int i = 0; i < playerList.size(); i++){
               Player currentPlayer = playerList.get(i);
               int score = currentPlayer.getScore();
               currentPlayer.displayHand();
               if(score > 21){
                   System.out.print("Player " + currentPlayer.getPlayerNum() + " busted");
               }
               else {
                   if(dealerScore > 21){
                       System.out.print("Player " + currentPlayer.getPlayerNum() + " won!");
                       currentPlayer.addWin();
                   }
                   else {
                       if(score > dealerScore){
                           System.out.print("Player " + currentPlayer.getPlayerNum() + " won!");
                           currentPlayer.addWin();
                       }
                       else if(score == dealerScore){
                           System.out.print("Player " + currentPlayer.getPlayerNum() + " tied with the dealer");
                        }
                       else {
                           System.out.print("Player " + currentPlayer.getPlayerNum() + " lost");
                       }
                   }
               }
               System.out.println(" (" + currentPlayer.getWins() + " Wins)");
           }
           
           System.out.println("Would you like to play again?(Type y or n)");
           correctInput = false;
           String temp = input.next();
           while(correctInput != true){
               if(temp.equals("y") || temp.equals("n")){
                   correctInput = true;
               }
               else {
                   System.out.println("Error: input must be y or n");
                   System.out.println("Would you like to play again?(Type y or n)");
                   temp = input.next();
               }
           }
           if(temp.equals("y")){
                       wantsToPlay = true;
                       dealer.clearHand();
                       for(int i = 0; i< playerList.size();i++){
                           playerList.get(i).clearHand();
                       }
                       System.out.print("\u000C");
                   }
                   else if(temp.equals("n")){
                       wantsToPlay = false;
                       System.out.print("\u000C");
                       for(int i = 0; i < playerList.size();i++){
                           Player currentPlayer = playerList.get(i);
                           System.out.println("Player " + currentPlayer.getPlayerNum() + " had " + currentPlayer.getWins() + " wins");
                       }
                       System.out.print("Thanks for playing Blackjack! Made by Artem Ivanov");
                   }
        }
    }
}