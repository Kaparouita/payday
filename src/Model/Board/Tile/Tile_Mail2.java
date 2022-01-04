package Model.Board.Tile;


import Model.Board.Dice;
import Model.Card.Card;
import Model.Deck.Deck;
import Model.Player.player;

import javax.swing.*;

public class Tile_Mail2 extends Tile {

    /**
     * <b>Constructor</b>
     * Creates a new TILE
     *
     * @param day
     * @param str_day
     */
    public Tile_Mail2(int day, String str_day) {
        super(day, str_day, "mc2");
    }

    public Tile_Mail2() {
        super(0,"","mc2");
    }

    /**
     * <b>Transformer</b>
     * player draws 2 mail card
     * if mail card =Move_to draw the second one then move
     * <b>ONLY PARAM NEEDED ARE :</b>
     *
     * @param deck
     * @param p1
     * @param j
     * @param p2
     */

    public void tile_action(player p1, player p2, Deck deck,Deck ddeck, Jackpot j) {
        Card card = deck.Draw();
        JOptionPane.showMessageDialog(null, "Player 1 drew "+deck.Draw());
        if (card.getName().equals("Move_to")) {

            deck.remove_card(deck.getTop_card());
            JOptionPane.showMessageDialog(null, "Lets draw the next 1 first! : ");
            deck.Draw().card_action(p1, p2, j);
            card.card_action(p1, p2, j);
            deck.remove_card(deck.getTop_card());
        } else {
            card.card_action(p1, p2, j);
            deck.remove_card(deck.getTop_card());
            JOptionPane.showMessageDialog(null, "The second card is! : "+deck.Draw());
            deck.Draw().card_action(p1, p2, j);
            deck.remove_card(deck.getTop_card());


        }
    }
}
