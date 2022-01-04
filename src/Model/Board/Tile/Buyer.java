package Model.Board.Tile;

import Model.Board.Dice;
import Model.Card.Deal_Card;
import Model.Deck.Deck;
import Model.Player.player;

import javax.swing.*;

import static Controller.controller.isInteger;

public class Buyer extends Tile {


    /**
     * <b>Constructor</b>
     * Creates a new TILE
     *
     * @param day
     * @param str_day
     */
    public Buyer(int day, String str_day) {
        super(day, str_day, "buyer");
    }

    public Buyer() {
        super(0, "", "buyer");
    }


    /**
     * <b>Transformer</b>
     * <b>has the option to sell a Deal card</b>
     * <b>if sell is true then sell</b>
     *
     * @param p1
     */
    public void tile_action(player p1, player p2, Deck deck, Deck ddeck, Jackpot j) {
        Deal_Card Dcard;
        boolean flag = true;
        if (p1.getDeal_Cards().size() != 0) {
            while (flag) {
                int reply = JOptionPane.showConfirmDialog(null, "Tile Buyer\n" +
                        "You can sell 1 of your deal cards \n" +
                        "Do you want to sell a card?", "Buyer", JOptionPane.YES_NO_OPTION);
                /*an eipe nai*/
                if (reply == JOptionPane.YES_NO_OPTION) {
                    String cards = "";
                    for (int i = 0; i < p1.getDeal_Cards().getDeck().size(); i++)
                        cards += "Card number : "+ i + "   " + p1.getDeal_Cards().getCard(i).toString() + "\n";
                    String card = JOptionPane.showInputDialog(null,
                            "Enter the number of the card you want to sell : \n" + cards);
                    if (isInteger(card, 5)) {
                        int x = Integer.parseInt(card);
                        if (x >= 0 && x < p1.getDeal_Cards().getDeck().size()) {
                            Dcard = (Deal_Card) p1.getDeal_Cards().getCard(x);
                            p1.setMoney(p1.getMoney() + Dcard.getSell_value());
                            p1.getDeal_Cards().remove_card(x);
                            flag = false;
                        } else JOptionPane.showMessageDialog(null, "This number doesnt match a card!", "buyer",
                                JOptionPane.ERROR_MESSAGE);


                    } else JOptionPane.showMessageDialog(null, "This is not a number", "buyer",

                           JOptionPane.ERROR_MESSAGE);
                    /*an eipe oxi*/
                } else
                    flag = false;
            }
        }/* an den exei katholou deal cards */
        else
            JOptionPane.showMessageDialog(null, "Tile Buyer\nYou dont own a deal card!", "buyer", JOptionPane.ERROR_MESSAGE);

    }
}
