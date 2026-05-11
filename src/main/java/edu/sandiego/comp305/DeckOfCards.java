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

    private ArrayList<Card> shuffle(ArrayList<Card> deck){
        return deck;
    }

    public Card dealCard(){
        return null;
    }

    void removeCard(){}

}
