package edu.sandiego.comp305;

public class Card {
    Suit suit;
    CardValues cardValue;

    Card(Suit suit, CardValues cardValue){
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
