package edu.sandiego.comp305;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {
    public List<Card> deck;

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

    public static ArrayList<Card> shuffle(ArrayList<Card> deck){
        Collections.shuffle(deck);
        return deck;
    }

    public static Card dealCard(ArrayList<Card> deck){
        Card dealtCard = deck.getFirst();
        deck.remove(dealtCard);
        return dealtCard;
    }

}
