package Model.Deck;

import Model.Card.*;
import Model.Player.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private player owner;
    private ArrayList<Card> deck;
    private int top_card=0;

    /**
     * <b>Constructor</b>
     * Creates a new deck
     */
    public Deck() {
        deck = new ArrayList<Card>();
    }

    /**
     * <b>Accessor:</b>
     * returns the deck
     *
     * @return deck
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * <b>Transformer</b>
     * sets the deck
     *
     * @param deck
     */
    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    /**
     * <b>Transformer</b>
     * sets the deck's owner
     */
    public void setOwner(player owner) {
        this.owner = owner;
    }

    /**
     * <b>Accessor:</b>
     * returns the deck's owner
     *
     * @return deck
     */
    public player getOwner() {
        return owner;
    }

    /**
     * <b>Transformer</b>
     * <b>init a new Mail Deck</b>
     */
    public void init_Mail_Deck() {
      /*  for(int i=0;i<8;i++){
             deck.add(new Pay_the_neighbour());
            deck.add(new Move_to());
            deck.add(new Get_paid_from_neighbour());
            deck.add(new Charity());
            deck.add(new Bill());
            deck.add(new Advertisement());
        }*/
    }

    /**
     * <b>Transformer</b>
     * <b>init a new Deal Deck</b>
     */
    public void init_Deal_Deck() {
        deck.add(new Deal_Card(7000, 10000, "tesla", "Agora Aftokinhtou se timh efkairias", ""));
        deck.add(new Deal_Card(500, 700, "iphone", "Agora i-phone 13 apo Iapwnia", ""));
        deck.add(new Deal_Card(3000, 5000, "Ethereum", "Agora Ethereum", ""));
        deck.add(new Deal_Card(4000, 7000, "Mhxanh", "Agora Mhxanhs", ""));
        deck.add(new Deal_Card(5000, 9000, "ploio", "Agora Taxyploou", ""));
        deck.add(new Deal_Card(700, 1400, "football ticket", "Agora Eisithriou VIP gia ton teliko tou Champions League", ""));
        deck.add(new Deal_Card(200, 500, "metal ticket", "Agora Eisithriou gia thn synavlia twn Iron Maiden", ""));
        deck.add(new Deal_Card(1000, 1700, "macbook", "Agora Macbook apo to eBay", ""));
        deck.add(new Deal_Card(2000, 4000, "watch", "Agora Xrysoy Rologioy", ""));
        deck.add(new Deal_Card(1200, 2000, "camera", "Agora Epaggelmatikhs Fwtografikhs Mhxanhs", ""));
        deck.add(new Deal_Card(1500, 3000, "oil", "Agora Elaioladou", ""));
        deck.add(new Deal_Card(250, 550, "doge", "Agora Skylou Yorkshire Terrier", ""));
        deck.add(new Deal_Card(1000, 2000, "facebook", "Agora Metoxwn sto Facebook", ""));
        deck.add(new Deal_Card(12000, 21000, "oikopedo", "Agora Oikopedou sto Elafonhsi", ""));
        deck.add(new Deal_Card(3000, 6000, "kithara", "Agora Syllektikhs Kitharas", ""));
        deck.add(new Deal_Card(10000, 18000, "katasthma", "Agora Katasthmatos sto Talos Plaza", ""));
        deck.add(new Deal_Card(900, 1800, "nba ticket", "Agora Eishthriou gia ton teliko tou NBA", ""));
        deck.add(new Deal_Card(700, 1200, "cat", "Agora Spanias Gatas", ""));
        deck.add(new Deal_Card(5000, 9500, "bakery", "Agora Zaxaroplasteiou", ""));

    }

    /**
     * <b>Observer</b>
     * Returns true if this list contains no elements.
     * <b>Post:</b> Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {
        if(deck.size()==0){
            return true;
        }
        return false;
    }


    public Card Draw(){
       return getCard(deck.size()-1);
    }
    /**
     * <b>Transformer</b>
     * <b>add a card to the deck</b>
     *
     * @param card
     */
    public void add_card(Card card) {
        deck.add(card);
    }

    /**
     * <b>Transformer</b>
     * <b>remove a card from the deck</b>
     */
    public void remove_card(int c) {
        deck.remove(c);
    }

    /**
     * <b>Transformer</b>
     * anakateyei to deck
     */
    public void shuffle() {
        Collections.shuffle(deck);
    }

    /**
     * <b>Accessor:</b>
     * Returns the top card.
     *
     * @return size of the list
     */
    public int getTop_card() {
        top_card=deck.size()-1;
        return top_card;
    }

    /**
     * <b>Accessor:</b>
     * <b>Post : return the card</b>
     *
     * @return the card in position if there is a card else return null
     */
    public Card getCard(int position) {
        return deck.get(position);
    }


    public int size() {
       return deck.size();
    }
}
