package com.company;

import java.util.ArrayList;
import java.util.Collections;


/*

    This class handles all the activities that pertain to the deck of cards.

    dealCards - Deals the first 5 cards in the deck to both players.
    flipCards - Function that is called if player asks for a flip. It gives the first 2 cards to the first player and then the rest to the other
    player.
    showHands - Shows the player their hand.
    finishCards - Used when a flip is not called. Finishes giving the players their cards.
    finishFlip - Finishes dealing the cards if flip was selected.
    organizeCards - Organizes the cards by both suit and number.

 */
public class Cards {

    static void DealCards(ArrayList<String> array, ArrayList<String> player) {

        do {

            player.add(player.size(), array.get(0));
            array.remove(0);

        }while(player.size() < 5);

    }

    static void FlipCards(ArrayList<String> array, ArrayList<String> player, ArrayList<String> player2, String[] flip){

        player.add(player.size(), array.get(0));
        player.add(player.size(), array.get(1));
        flip[0] = array.get(0);
        flip[1] = array.get(1);
        array.remove(0);
        array.remove(1);

        do{

            player2.add(player2.size(), array.get(0));
            array.remove(0);

        }while(player2.size() < 15);

    }

    static void ShowHands(ArrayList<String> player, ArrayList<String> player2){

        System.out.println("Player 1 Hand: " + player);
        //System.out.println("Player 2 Hand: " + player2 + " " + player2.size());


    }

    static void FinishCards(ArrayList<String> array, ArrayList<String> player){

        do{

            player.add(player.size(), array.get(0));
            array.remove(0);

        }while(player.size() < 15);

    }

    static void FinishFlip(ArrayList<String> array, ArrayList<String> player){

        do{

            player.add(player.size(), array.get(0));
            array.remove(0);

        }while(player.size() < 15);

    }

    static ArrayList<String> organizeCards(ArrayList<String> player){

        PlayerDecisions pd = new PlayerDecisions();

        ArrayList<Integer> hearts = new ArrayList<Integer>();
        ArrayList<Integer> diamonds = new ArrayList<Integer>();
        ArrayList<Integer> clovers = new ArrayList<Integer>();
        ArrayList<Integer> spades = new ArrayList<Integer>();

        ArrayList<String> organized = new ArrayList<String>();

        for(int i = 0; i < player.size(); i++){

            String tempCard = player.get(i);

            if(tempCard.contains("H")){

                hearts.add(pd.GetNumber(tempCard));

            } else if(tempCard.contains("D")){

                diamonds.add(pd.GetNumber(tempCard));

            } else if(tempCard.contains("C")){

                clovers.add(pd.GetNumber(tempCard));

            } else if(tempCard.contains("S")){

                spades.add(pd.GetNumber(tempCard));

            }
        }

        Collections.sort(hearts);
        Collections.sort(diamonds);
        Collections.sort(clovers);
        Collections.sort(spades);

        for(int j = 0; j < hearts.size(); j++){

            organized.add("H" + hearts.get(j));

        }
        for(int k = 0; k < diamonds.size(); k++){

            organized.add("D" + diamonds.get(k));

        }
        for(int l = 0; l < clovers.size(); l++){

            organized.add("C" + clovers.get(l));

        }
        for(int n = 0; n < spades.size(); n++){

            organized.add("S" + spades.get(n));

        }

        return organized;
    }


}
