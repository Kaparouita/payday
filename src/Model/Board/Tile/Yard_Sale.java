package Model.Board.Tile;

import Model.Board.Dice;
import Model.Board.Tile.Tile;
import Model.Deck.Deck;
import Model.Player.player;

import javax.swing.*;

public class Yard_Sale extends Tile {


    /**
     * <b>Constructor</b>
     * Creates a new TILE
     *
     * @param day
     * @param str_day
     */
    public Yard_Sale(int day, String str_day) {
        super(day, str_day, "yard");
    }

    public Yard_Sale() {
        super(0,"","yard");
    }

    /**
     * <b>Transformer</b>
     * <b>roll the dice pays 100*dice value</b>
     * <b>gets a deal card</b>
     *<b>ONLY PARAM NEEDED ARE :</b>
     * @param p1
     * @param ddeck
     */
    public void tile_action(player p1, player p2, Deck deck,Deck ddeck, Jackpot j) {
        p1.getPdice().roll_Dice();
        int pay = p1.getPdice().getDice_value() * 100;
        if (p1.getMoney() < pay)
            p1.Loan();
        JOptionPane.showMessageDialog(null, "Tile Yard \n" +
                        "You roll the dice and pay 100 * its value,but you get a random deal card\n" +
                        "The dice value was "+pay/100+" so you have to pay "+pay+
                        " but you get this card : \n"+ddeck.Draw()
        ,"Yard",JOptionPane.INFORMATION_MESSAGE);
        p1.getDeal_Cards().add_card(ddeck.Draw());
        p1.setMoney(p1.getMoney()-pay);
        ddeck.remove_card(ddeck.getTop_card());
    }
}
