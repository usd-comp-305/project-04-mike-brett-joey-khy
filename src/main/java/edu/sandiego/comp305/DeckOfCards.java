package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {

    private DeckOfCards(){}

    public static ArrayList<Card> createDeckOfCards(){
        final ArrayList<Card> deck = new ArrayList<>();
        for(Suit suit : Suit.values()){
            for(CardValues cardValue : CardValues.values()){
                final Card card = new Card(suit, cardValue);
                deck.add(card);
            }
        }
        return deck;
    }

    public static ArrayList<Card> shuffleDeck(final ArrayList<Card> deck){
        Collections.shuffle(deck);
        return deck;
    }

    public static Card dealCard(final ArrayList<Card> deck){
        final Card dealtCard = deck.getFirst();
        deck.remove(dealtCard);
        return dealtCard;
    }

}
