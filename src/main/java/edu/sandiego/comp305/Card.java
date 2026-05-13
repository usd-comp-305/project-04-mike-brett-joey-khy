package edu.sandiego.comp305;

public class Card {
    private final Suit suit;

    private final CardValues cardValue;

    Card(final Suit suit,final CardValues cardValue){
        this.cardValue = cardValue;
        this.suit = suit;
    }

    Suit getSuit(){

        return suit;
    }

    CardValues getFaceValue(){

        return cardValue;
    }
}
