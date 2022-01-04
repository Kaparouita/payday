package Model.Board.Tile;

import Model.Board.Dice;
import Model.Board.Tile.Tile;
import Model.Deck.Deck;
import Model.Player.player;

import javax.swing.*;

import static Controller.controller.isInteger;

public class Lottery extends Tile {


    /**
     * <b>Constructor</b>
     * Creates a new TILE
     * Lottery Tile
     *
     * @param day
     * @param str_day
     */
    public Lottery(int day, String str_day) {
        super(day, str_day, "lottery");
    }

    public Lottery() {
        super(0,"","lottery");
    }

    /**
     * <b>Transformer</b>
     * roll the dice till getdice=p1value or p2value
     * <b>p1 or p2 money +1000</b>
     *<b>ONLY PARAM NEEDED ARE</b>
     * <b>rolling again is auto</b>
     * @param p1
     * @param p2
     * @param p1value
     * @param p2value
     */
    public void lottery_action(player p1,player p2,int p1value,int p2value){
        boolean flag = true;
        int value=0;
        while (flag) {
            value = roll_Dice();
            if (value==p1value){
                p1.setMoney(p1.getMoney()+1000);
                flag=false;
                JOptionPane.showMessageDialog(null, "Player "+p1.getId()+" won! his number was "+value,"Lottery",JOptionPane.INFORMATION_MESSAGE);
            }
            else if(value==p2value) {
                p2.setMoney(p2.getMoney() + 1000);
                flag = false;
                JOptionPane.showMessageDialog(null, "Player "+p2.getId()+" won! his number was "+value,"Lottery",JOptionPane.INFORMATION_MESSAGE);
            }
            else
                System.out.println("None one the number was "+value+"\n Rolling again!");
        }
    }/**
 * <b>Transformer</b>
 * roll the dice till getdice=p1value or p2value
 * <b>p1 or p2 money +1000</b>
 *<b>with dialogs</b>
 */
    public void tile_action(player p1, player p2, Deck deck,Deck ddeck, Jackpot j) {
        String reply2;
        boolean flag = true;
        while (flag) {
            String reply1 = JOptionPane.showInputDialog(null, "Tile Lottery\n" +
                    "Both players enter a number and a dice rolls until its equal to a player number\n" +
                    "The winner gets 1000 euros\n" +
                    "Player" + p1.getId() + " enter the number between 1-6 to win the loterry! : \n");
            if (p1.getId() == 1)
                reply2 = JOptionPane.showInputDialog(null,
                        "Player2 enter the number between 1-6 to win the lottery! :\n");
            else
                reply2 = JOptionPane.showInputDialog(null,
                        "Player1 enter the number between 1-6 to win the lottery! :\n");
            if (isInteger(reply1, 10) && isInteger(reply2, 10)) {
                int x1 = Integer.parseInt(reply1);
                int x2 = Integer.parseInt(reply2);
                if (x1 == x2) {
                    JOptionPane.showMessageDialog(null, "You cant pick the same number", "ERROR Lottery", JOptionPane.ERROR_MESSAGE);
                } else if (x1 > 0 && x1 < 7 && x2 > 0 && x2 < 7) {
                    lottery_action(p1,p2,x1,x2);
                    flag = false;
                } else
                    JOptionPane.showMessageDialog(null, "Wrong number", "ERROR Lottery", JOptionPane.ERROR_MESSAGE);

            } else
                JOptionPane.showMessageDialog(null, "This is not a number", "ERROR Lottery", JOptionPane.ERROR_MESSAGE);
        }
    }
}
