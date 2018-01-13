package com.company;

/*
    This function handles the decision making for the ai player.
    sameType, sirType, noType - This is the decision maker for which card to place out of the 4 best options.
    checkCards - Decides which 4 cards the best to play depending on what cards are available.
    Max of the same type, minimum of the same type, max sir card available, and the lowest card in the entire hand.
    checkWinner - Checks both the player and ai card and decides which player has won the pair.
    getNumber - helper function that limits the amount of time that code is written. Gets the number from the card chosen.

 */

public class PlayerDecisions {

    private String maxSType = "";
    private String minSType = "";
    private String maxSir = "";
    private String lowestCard = "";

    public void SameType(int number, String type){

        int max;
        int min;

        //System.out.println("Current: " + maxSType + " + " + minSType);
        //System.out.println("New Card: " + type + number);

        if(maxSType.equals("")){

            maxSType = type + number;

        }

        if(minSType.equals("")){

            minSType = type + number;

        }

        max = GetNumber(maxSType);
        min = GetNumber(minSType);

        if(number > max){

            maxSType = type + number;

        }
        if(number < min){

            minSType = type + number;

        }

        //System.out.println("Final: " + maxSType + " + " + minSType);

    }

    public void SirType(int number, String type){

        int max;

        if(maxSir.equals("")){

            maxSir = type + number;

        }

        max = GetNumber(maxSir);

        if(number > max){

            maxSir = type + number;

        }
    }

    public void NoType(int number, String type){

        int min;

        if(lowestCard.equals("")){

            lowestCard = type + number;

        }

        min = GetNumber(lowestCard);

        if(number < min){

            lowestCard = type + number;

        }
    }

    public String CheckCard(){

        //System.out.println("Max Same:" + maxSType + "\n" + "Min Same:" + minSType + "\n" + "Sir:" + maxSir + "\n" + "Lowest: " + lowestCard + "\n");
        String finalCard = "";

        if(!maxSType.equals("")){

            finalCard = maxSType;

        } else if(!minSType.equals("")){

            finalCard = minSType;

        } else if(!maxSir.equals("")){

            finalCard = maxSir;

        } else if(!lowestCard.equals("")){

            finalCard = lowestCard;

        } else {

            finalCard = "ERROR";
        }

        maxSType = "";
        minSType = "";
        maxSir = "";
        lowestCard = "";

        return finalCard;
    }

    public int CheckWinner(String p1Card, String p2Card, String sir){

        int winner = 0;
        String p1Type;
        String p2Type;
        int p1Number;
        int p2Number;

        p1Type = p1Card.substring(0, 1);
        p2Type = p2Card.substring(0, 1);

        p1Number = GetNumber(p1Card);
        p2Number = GetNumber(p2Card);

        if(p1Type.equalsIgnoreCase(p2Type)){

            if(p1Number > p2Number){

                winner = 1;

            } else {

                winner = 2;

            }

        }

        if(p1Type.equalsIgnoreCase(sir) && !p2Type.equalsIgnoreCase(sir)){

            winner = 1;

        } else if(p2Type.equalsIgnoreCase(sir) && !p1Type.equalsIgnoreCase(sir)){

            winner = 2;

        }

        if(!p1Type.equalsIgnoreCase(p2Type) && !p2Type.equalsIgnoreCase(sir)){

            winner = 1;

        }

        return winner;
    }

    public int GetNumber(String number){

        int finalNumber = 0;

        if(number.length() == 3) {
            finalNumber = Integer.parseInt(number.substring(1, 3));
        } else {
            finalNumber = Integer.parseInt(number.substring(1, 2));
        }

        return finalNumber;
    }



}
