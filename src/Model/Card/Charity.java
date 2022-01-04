package Model.Card;

import Model.Board.Tile.Jackpot;
import Model.Deck.Deck;
import Model.Player.player;

public class Charity extends Mail_Card {


    public Charity(String action,String toDo,int price) {
        super("Charity", action, toDo,price);
    }

    /**
     * <b>Transformer</b>
     * <b>Pre : p1.getMoney > 50</b><b>else pare daneio</b>
     * <b>Post : p1.getMoney-50 , Jackpot +50</b>
     *
     * @param p1;
     * @param jackpot;
     */

    public void card_action(player p1, player p2, Jackpot jackpot) {
        if (p1.getMoney() < 50) {
           p1.Loan();
        }
        jackpot.setJackpot_value(jackpot.getJackpot_value() + getPrice());
        p1.setMoney(p1.getMoney() - getPrice());
    }
}