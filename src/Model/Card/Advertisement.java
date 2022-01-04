package Model.Card;

import Model.Board.Tile.Jackpot;
import Model.Deck.Deck;
import Model.Player.player;

public class Advertisement extends Mail_Card {

    public Advertisement(String action,String toDo,int price) {
        super("Advertisement", action,toDo,price);
    }

    /**<b>Transformer</b>
     * <b>player one get's +20 money</b>
     * @param player1
     */
@Override
    public void card_action(player player1, player p2, Jackpot j) {
        player1.setMoney(player1.getMoney()+getPrice());
    }
}
