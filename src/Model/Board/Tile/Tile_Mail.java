package Model.Board.Tile;

import Model.Board.Dice;
import Model.Card.*;
import Model.Deck.Deck;
import Model.Player.player;

import javax.swing.*;

public class Tile_Mail extends Tile {


    /**
     * <b>Constructor</b>
     * Creates a new TILE
     *
     * @param day
     * @param str_day
     */
    public Tile_Mail(int day, String str_day) {
        super(day, str_day, "mc1");
    }

    public Tile_Mail() {
        super(0,"","mc1");
    }

    /**
     * <b>Transformer</b>
     * player draws a mail card
     * <b>ONLY PARAM NEEDED ARE :</b>
     * @param deck
     * @param p1
     * @param j
     * @param p2
     */

    public void tile_action(player p1, player p2, Deck deck,Deck ddeck, Jackpot j) {

        JOptionPane.showMessageDialog(null, "Player 1 drew "+deck.Draw());
        deck.Draw().card_action(p1,p2,j);
        deck.remove_card(deck.getTop_card());

     /*   if (tmp.getName().equals("Advertisement")) {

        } else if (tmp.getName().equals("Bill")) {

        } else if (tmp.getName().equals("Charity")) {

        } else if (tmp.getName().equals("Get_paid_from_neighbour")) {

        } else if (tmp.getName().equals("Move_to")) {

        } else if (tmp.getName().equals("Pay_the_neighbour")) {

        }*/

    }
}
