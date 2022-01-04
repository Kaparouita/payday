package Model.Card;

import Model.Board.Tile.Jackpot;
import Model.Deck.Deck;
import Model.Player.player;

public class Pay_the_neighbour extends Mail_Card {

    public Pay_the_neighbour(String action,String toDo,int price) {
        super("Pay_the_neighbour", action, toDo,price);
    }

    /**
     * <b>Transformer</b>
     * <b>Pre : p1.getMoney > 60</b><b>else pare daneio</b>
     * <b>Post : p1.getMoney-60 , p2.getMoney +60</b>
     *
     * @param p1;
     * @param p2;
     */
    public void card_action(player p1, player p2, Jackpot j) {
        if (p1.getMoney() < getPrice()) {
            p1.Loan();
        }
        p1.setMoney(p1.getMoney() - getPrice());
        p2.setMoney(p2.getMoney() + getPrice());
    }
}
