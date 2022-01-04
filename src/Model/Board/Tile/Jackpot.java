package Model.Board.Tile;

import Model.Board.Dice;
import Model.Board.Tile.Tile;
import Model.Deck.Deck;
import Model.Player.player;

import javax.swing.*;

public class Jackpot extends Tile {

private int jackpot_value=0;

    /**<b>Constructor</b>
     * Creates a new TILE
     *
     */
    public Jackpot() {
        super(32, "","JACKPOT");
        this.jackpot_value=0;
    }

    /**<b>Transformer</b>
     * set jackpot value
     * @param jackpot_value
     */
    public void setJackpot_value(int jackpot_value) {
        this.jackpot_value = jackpot_value;
    }

    /**<b>Accessor</b>
     * get jackpot value
     * @return jackpot value
     */
    public int getJackpot_value() {
        return jackpot_value;
    }

    /**<b>Transformer</b>
     * jackpot=0 ,player money +=jackpot
     * <b>ONLY PARAM NEED ARE :</b>
     * @param p1
     */
    public void tile_action(player p1, player p2, Deck deck,Deck ddeck, Jackpot j) {
        p1.setMoney(p1.getMoney()+jackpot_value);
        if(jackpot_value!=0)
        JOptionPane.showMessageDialog(null, "You won the Jackpot! \n Jackpot value :"+jackpot_value,"JACKPOT",JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, "You won the Jackpot but jackpot has no money on it!","JACKPOT",JOptionPane.INFORMATION_MESSAGE);

        jackpot_value=0;
    }
}
