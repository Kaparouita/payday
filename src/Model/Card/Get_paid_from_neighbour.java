package Model.Card;

import Model.Board.Tile.Jackpot;
import Model.Deck.Deck;
import Model.Player.player;

public class Get_paid_from_neighbour extends Mail_Card {

    public Get_paid_from_neighbour(String action,String toDo,int price) {
        super("Get_paid_from_neighbour", action,toDo,price);
    }

    /**
     * <b>Transformer</b>
     * <b>Pre : p2.getMoney > 50</b><b>else pare daneio</b>
     * <b>Post : p1.getMoney+50 , p2.getMoney -50</b>
     *
     * @param p1;
     * @param p2;
     */

    public void card_action(player p1, player p2, Jackpot j) {
        if (p2.getMoney() < getPrice()) {
            p2.Loan();
        }
        p2.setMoney(p2.getMoney() - getPrice());
        p1.setMoney(p1.getMoney() + getPrice());
    }
}