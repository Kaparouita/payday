package Model.Board.Tile;

import Model.Board.Dice;
import Model.Deck.Deck;
import Model.Player.player;

import javax.swing.*;

public class Casino_Night extends Tile {


    /**
     * <b>Constructor</b>
     * Creates a new TILE
     * Casino night
     *
     * @param day
     * @param str_day
     */
    public Casino_Night(int day, String str_day) {
        super(day, str_day, "casino");
    }

    public Casino_Night() {
        super(0,"","casino");
    }

    /**
     * <b>Transformer</b>
     * <b>Depends on last the last dice value</b>
     * <b>if [1,3,5] player money -500 , jackpot +500</b>
     * <b>else if [2,4,6]player money +500</b>
     *<b>ONLY PARAM NEEDED ARE :</b>
     * @param p1
     * @param jackpot
     */
    public void tile_action(player p1, player p2, Deck deck,Deck ddeck, Jackpot jackpot) {
        int d_value = p1.getPdice().getDice_value();
        if (d_value == 1 || d_value == 3 || d_value == 5) {
            if (p1.getMoney() < 500)
                p1.Loan();
            p1.setMoney(p1.getMoney() - 500);
            JOptionPane.showMessageDialog(null, "" +
                            "Tile Casino Night \n" +
                            "If your last dice value was [1,3,5] you lose 500 else if [2,4,6] you win 500\n" +
                            "Your last dice value was "+p1.getPdice().getDice_value()+" so you have to pay!",
                    "Casino_night",JOptionPane.INFORMATION_MESSAGE);
            jackpot.setJackpot_value(jackpot.getJackpot_value() + 500);
        } else if (d_value == 2 || d_value == 4 || d_value == 6) {
            p1.setMoney(p1.getMoney() + 500);
            JOptionPane.showMessageDialog(null, "Tile Casino Night \n" +
                    "If your last dice value was [1,3,5] you lose 500 else you win 500\n" +
                            "Your last dice value was "+p1.getPdice().getDice_value()+" so you win 500!",
                    "Casino_night",JOptionPane.INFORMATION_MESSAGE);

        }
    }
}
