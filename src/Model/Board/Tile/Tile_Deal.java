package Model.Board.Tile;

import Model.Board.Dice;
import Model.Card.Card;
import Model.Card.Deal_Card;
import Model.Deck.Deck;
import Model.Player.player;

public class Tile_Deal extends Tile {


    /**
     * <b>Constructor</b>
     * Creates a new TILE
     * Deal tile
     *
     * @param day
     * @param str_day
     */
    public Tile_Deal(int day, String str_day) {
        super(day, str_day, "deal");
    }

    public Tile_Deal() {
        super(0, "", "deal");
    }

    /**
     * <b>Transformer</b>
     * draw deal card
     * if buy is true he is willing to buy the card
     * <b>ONLY PARAM NEEDED ARE :</b>
     *
     * @param buy
     * @param p1
     * @param deck
     */
    public void tile_action(player p1, player p2, Deck deck, Deck ddeck, Jackpot j) {
        Deal_Card tmp = (Deal_Card) ddeck.Draw();
        p1.getDeal_Cards().add_card(tmp);
        while (p1.getMoney() < tmp.getBuy_value())
            p1.Loan();
        p1.setMoney(p1.getMoney() - tmp.getBuy_value());
        for (int i = 0; i < ddeck.size(); i++) {
            if (tmp.getName().equals(ddeck.getCard(i).getName()))
                ddeck.remove_card(i);
        }
    }
}