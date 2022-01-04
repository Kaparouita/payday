package Model.Card;

import Model.Board.Tile.Jackpot;
import Model.Deck.Deck;
import Model.Player.player;

public class Bill extends Mail_Card {


    public Bill(String action,String toDo,int price) {
        super("Bill", action,toDo,price);
    }

    /**
     * <b>Transformer</b>
     * <b>post : player bill +500</b>
     *
     * @param player1
     */
    @Override
    public void card_action(player player1, player p2, Jackpot j) {
        player1.setBills(player1.getBills() + getPrice());
    }
}
