package Model.Board.Tile;

import Model.Board.Dice;
import Model.Board.Tile.Tile;
import Model.Deck.Deck;
import Model.Player.player;

import javax.swing.*;

public class Sweepstakes extends Tile {


    /**
     * <b>Constructor</b>
     * Creates a new TILE
     *
     * @param day
     * @param str_day
     */
    public Sweepstakes(int day, String str_day) {
        super(day, str_day, "sweep");
    }

    public Sweepstakes() {
        super(0,"","sweep");
    }

    /**
     * <b>Transformer</b>
     * <b>p1 rolls the dice and wins
     * 1000 * the value</b>
     * <b>ONLY PARAM NEEDED ARE</b>
     * @param p1
     */
    public void tile_action(player p1, player p2, Deck deck,Deck ddeck,Jackpot j) {
       int value = p1.getPdice().roll_Dice() * 1000;
        p1.setMoney(p1.getMoney() + value);
        JOptionPane.showMessageDialog(null, "Tile Sweepstakes\n" +
                "Player roll the dice and wins 1000*the value" +
                "The dice value was "+(value/1000)+"\n" +
                "Player "+p1.getId()+" won : "+value+"!!!!","Sweepstakes",JOptionPane.INFORMATION_MESSAGE);
    }
}
