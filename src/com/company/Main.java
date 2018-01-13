package com.company;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.company.Cards.*;

/*
    Prepare: Prepare the deck Array and initialize the player arrays to hold the hands. Initialize a new PlayerDecisions class and variables for scoring and in case
    the user decides to ask for a flip.

    Input: Get user input to select sir value. If not decided, then flip cards and get sir value again, or print a short tutorial if the user asks. After that,
    loop through the game until all the cards have been played. The input is the card and we separate it into a suit and number so that it can be compared to the
    AI hand.

    Process: Get user input for all 15 cards. Make sure AI makes the correct decision.
    Steps:
    1. AI uses the type of card available.
    2. AI uses the highest value if possible, if not use lowest value.
    3. AI uses sir if no card of the same suit is available.
    4. AI uses lowest card if no sir or same suit is available.

    Repeat for all 15 cards with new card input from the user.

    Output: After each card is played, output what each player played and which player won the hand.
    After all 15 cards are played, output the number of hands won for each player, the player with more hands is the winner.

 */

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        ArrayList<String> deck = new ArrayList<String>(Arrays.asList("H7", "H8", "H9", "H10", "H11", "H12", "H13", "H14", "D8",
                "D9", "D10", "D11", "D12", "D13", "D14", "S7", "S8", "S9", "S10", "S11", "S12", "S13", "S14", "C8", "C9",
                "C10", "C11", "C12", "C13", "C14"));
        ArrayList<String> player1 = new ArrayList<String>();
        ArrayList<String> player2 = new ArrayList<String>();

        PlayerDecisions pd = new PlayerDecisions();

        String sir;
        String[] flip = new String[2];
        String flip1;
        String flip2;
        int player1Score;
        int player2Score;
        String winningPlayer;

        player1Score = 0;
        player2Score = 0;
        winningPlayer = "";

        Collections.shuffle(deck);

        DealCards(deck, player1);
        DealCards(deck, player2);

        do {

            System.out.println("Here is your hand. Please enter a letter to represent the sir, or ask for a flip by entering 'F.' or press 'T' for a tutorial.");
            ShowHands(player1, player2);

            sir = s.nextLine();
            System.out.println(sir);

            if (sir.equalsIgnoreCase("f")) {

                FlipCards(deck, player1, player2, flip);

                do {

                    System.out.println("Please select a sir from one of the flipped cards: ");
                    System.out.println("Hand: " + player1);
                    System.out.println("Flipped: " + Arrays.toString(flip));

                    flip1 = flip[0].substring(0, 1);
                    flip2 = flip[1].substring(0, 1);

                    sir = s.nextLine();

                } while (!(sir.equalsIgnoreCase(flip1) || sir.equalsIgnoreCase(flip2)));

                FinishFlip(deck, player1);

            }else if (sir.equalsIgnoreCase("t")){

                System.out.println("The letter represents the suit of the card, and the number represent a higher card strength. \n" +
                        "Cards with the same suit are superior to those of different suits unless the other card is a sir, then the highest sir wins. \n");

            } else if(sir.equalsIgnoreCase("h") || sir.equalsIgnoreCase("d") || sir.equalsIgnoreCase("c") || sir.equalsIgnoreCase("s"))
            {

                FinishCards(deck, player1);
                FinishCards(deck, player2);

            }

        }
        while (!(sir.equalsIgnoreCase("h") || sir.equalsIgnoreCase("d") || sir.equalsIgnoreCase("c") || sir.equalsIgnoreCase("s")));

        player1 = organizeCards(player1);

        do{

                ShowHands(player1, player2);

                //Flip Comments Here
                //System.out.println("Please place a card from your hand.");
                //String card = s.nextLine();
            String card = player1.get(0);

            String type = card.substring(0, 1);

            //set card values
            for(int i = 0; i < player2.size(); i++){

                String tempType = player2.get(i).substring(0, 1);
                int tempNumber;

                tempNumber = pd.GetNumber(player2.get(i));

                //if card is same type, function for max/min
                if(tempType.equalsIgnoreCase(type)){

                    pd.SameType(tempNumber, tempType);

                } else

                    //if card is a sir, function for max sir
                    if(tempType.equalsIgnoreCase(sir)){

                        pd.SirType(tempNumber, sir);

                        //if card is neither, find the lowest
                    } else {

                        pd.NoType(tempNumber, tempType);

                    }

                    //If the last card is checked. Decide which card to play.
                if(i == player2.size() - 1){

                    String player2Card = pd.CheckCard();
                    int winner = pd.CheckWinner(card, player2Card, sir);

                    if(winner == 1){

                        player1Score++;

                    } else if(winner == 2){

                        player2Score++;

                    } else if(winner == 0){

                        System.out.print("An error has occurred.");

                    }

                    player1.remove(card.toUpperCase());
                    player2.remove(player2Card);

                    System.out.println("P1: " + card);
                    System.out.println("P2: " + player2Card);
                    System.out.println("Winner: Player " + winner);

                }

            }

            card = "";
         }while(player1.size() > 0);


        if(player1Score > player2Score){

            winningPlayer = "Congrats Player 1, You Have Won!!";

        } else if(player1Score < player2Score){

            winningPlayer = "Congrats Player 2, You Have Won!!";

        }

        System.out.println("Player 1 Score: " + player1Score + "\nPlayer 2 Score: " + player2Score + "\n" + winningPlayer);

        s.close();
    }
 }