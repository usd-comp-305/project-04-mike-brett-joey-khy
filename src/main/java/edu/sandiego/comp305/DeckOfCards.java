package edu.sandiego.comp305;
import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {

    public static ArrayList<Card> createDeckOfCards(){
        ArrayList<Card> deck = new ArrayList<>();
        for(Suit suit : Suit.values()){
            for(CardValues cardValue : CardValues.values()){
                Card card = new Card(suit, cardValue);
                deck.add(card);
            }
        }
        return deck;
    }

    public static ArrayList<Card> shuffleDeck(ArrayList<Card> deck){
        Collections.shuffle(deck);
        return deck;
    }

    public static Card dealCard(ArrayList<Card> deck){
        Card dealtCard = deck.getFirst();
        deck.remove(dealtCard);
        return dealtCard;
    }

}
