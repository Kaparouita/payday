package Model.Board.Tile;

import Model.Board.Dice;
import Model.Board.Tile.Tile;
import Model.Deck.Deck;
import Model.Player.player;

import javax.swing.*;

import static Controller.controller.isInteger;

public class Payday extends Tile {


    /**
     * <b>Constructor</b>
     * Creates a new TILE
     * PAYDAY
     */
    public Payday() {
        super(31, "Wednes", "payday");
    }

    /**
     * <b>Transformer</b>
     * <b> 1: p1.setMoney +500</b>
     * <b> 2: Money-bill</b>
     * <b> 3: Pays 10% of the Loan if Money < 10% of the Loan pare daneio</b>
     * <b> 4: Pays the Loan or pays a part of the Loan in 1000</b>
     * <b> 5: if last month discard deal cards</b>
     * <b> 6: if !last month player position =1</b>
     *<b>ONLY PARAM NEEDED ARE :</b>
     * @param p1
     * @param pay_value poso p thelei na plhrwsei
     * @param pay       an einai true thelei na plhrwsei daneio
     *                  (prepei na ginete elenxos gia an diathetei to poso)
     */
    public void paydayAction(player p1,boolean pay,int pay_value){
        // 1:
        p1.setMoney(p1.getMoney() + 500);
        //2:
        if (p1.getMoney()<p1.getBills())
            p1.Loan();
        p1.setMoney(p1.getMoney()-p1.getBills());
        p1.setBills(0);
        //3
        int foros=p1.getLoan()*10/100;
        if(p1.getMoney()<foros)
            p1.Loan();
        p1.setMoney(p1.getMoney()-foros);
        //4
        if(pay){
            if(p1.getMoney()>pay_value) {
                p1.setMoney(p1.getMoney() - pay_value);
                p1.setLoan(p1.getLoan() - pay_value);
            }
        }
        //5
        if(p1.getHas_finished())
        {
           for(int i=0;i<p1.getDeal_Cards().size();i++)
               p1.getDeal_Cards().remove_card(i);
        }
        //6
        p1.setPosition(0);
    }

    /*
    me dialogs
     */

    public void tile_action(player p1, player p2, Deck deck,Deck ddeck, Jackpot j) {
        boolean flag = true;
        p1.setIs_first_turn(true);
        JOptionPane.showMessageDialog(null, "Tile Payday \b" +
                        "You get 500 euros,but you have to pay the bills and at least 10% of your Loan "
                , "Payday", JOptionPane.INFORMATION_MESSAGE);
        if (p1.getLoan() > 0) {
            while (flag) {
                int reply = JOptionPane.showConfirmDialog(null, "Your loan is :" + p1.getLoan() + "\n Do you want to pay?", "Payday", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_NO_OPTION) {
                    String loan = JOptionPane.showInputDialog(null, "Enter how much you want to pay(has to be 1000 multiple\n" + "Your current money is : " + p1.getMoney());
                    if (isInteger(loan, 5)) {
                        int pay = Integer.parseInt(loan);
                        if (pay % 1000 == 0) {
                            flag = false;
                            paydayAction(p1,true,pay);
                        } else
                            JOptionPane.showMessageDialog(null, "You cant pay if its not multiple of 1000! : " + loan, "ERROR Payday", JOptionPane.ERROR_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(null, "This is not a number", "ERROR Payday", JOptionPane.ERROR_MESSAGE);
                } else {
                    flag = false;
                }
            }
        } else
            paydayAction(p1,false,0);
    }
}
