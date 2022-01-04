package Model.Board.Tile;

import Model.Board.Dice;
import Model.Board.Tile.Tile;
import Model.Deck.Deck;
import Model.Player.player;

import javax.swing.*;

public class Radio extends Tile {

    /**<b>Constructor</b>
     * Creates a new TILE
     * Radio
     * @param day
     * @param str_day
     */
    public Radio(int day, String str_day) {
        super(day, str_day,"radio");
    }

    public Radio() {
        super(0,"","radio");
    }

    /**<b>Transformer</b>
     * <b>Roll the dice highest value wins +1000</b>
     * <b> if same value reroll</b>
     * <b>ONLY PARAM NEEDED ARE</b>
     * @param p1
     * @param p2
     * @param dice_p1
     * @param dice_p2
     */
    public void tile_action(player p1, player p2, Deck deck,Deck ddeck, Jackpot j) {
boolean flag=true;
while(flag){
    p1.getPdice().roll_Dice();
    p2.getPdice().roll_Dice();
    JOptionPane.showMessageDialog(null, "Tile Radio \n" +
            "Both players roll the dice highest vaule wins 1000 euros !","Radio",JOptionPane.INFORMATION_MESSAGE);

    if(p1.getPdice().getDice_value()>p2.getPdice().getDice_value()){
        p1.setMoney(p1.getMoney()+1000); flag=false;
        JOptionPane.showMessageDialog(null, "Player1 rolled : "+ p1.getPdice().getDice_value()+"\n" +
                "Player2 rolled : "+p2.getPdice().getDice_value()+"" +
                "\nPlayer1  won!","Radio",JOptionPane.INFORMATION_MESSAGE);
    }
    else if(p1.getPdice().getDice_value()<p2.getPdice().getDice_value()){
        p2.setMoney(p2.getMoney()+1000); flag=false;
        JOptionPane.showMessageDialog(null, "Player1 rolled : "+ p1.getPdice().getDice_value()+"\n" +
                        "Player2 rolled : "+p2.getPdice().getDice_value()+
                "\nPlayer2  won!","Radio",JOptionPane.INFORMATION_MESSAGE);
    }
    else
        JOptionPane.showMessageDialog(null, "Tile Radio \n" +
                "Both players rolled "+p1.getPdice().getDice_value()+" Rolling again!","Radio",JOptionPane.INFORMATION_MESSAGE);
}
    }
}
