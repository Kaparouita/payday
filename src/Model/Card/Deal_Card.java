package Model.Card;

import Model.Board.Tile.Jackpot;
import Model.Deck.Deck;
import Model.Player.player;
import Model.Board.Dice;

public class Deal_Card extends Dice implements Card {
    private int buy_value, sell_value;
    private player card_owner;
    private String name, action_Str, toDo_str;

    public Deal_Card(int buy_value, int sell_value, String name, String action_Str, String toDo_str) {
        this.buy_value = buy_value;
        this.sell_value = sell_value;
        this.name = name;
        this.action_Str = action_Str;
        this.toDo_str = toDo_str;
    }


    /**
     * <b>Transformers</b>
     * <b>set card owner</b>
     *
     * @param card_owner
     */
    public void setCard_owner(player card_owner) {
        this.card_owner = card_owner;
    }

    /**
     * <b>Accessor</b><p>
     * <b>returns card owner</b>
     *
     * @return card owner
     */
    public player getCard_owner() {
        return card_owner;
    }

    /**
     * <b>Accessor</b><p>
     * <b>post : returns owners ID</b>
     *
     * @return Owners ID
     */
    public int getCard_ownerID() {
        return card_owner.getId();
    }

    /**
     * <b>Accessor</b><p>
     * <b>post : returns owners buy value</b>
     */
    public int getBuy_value() {
        return buy_value;
    }

    /**
     * <b>Accessor</b><p>
     * <b>post : returns sell value</b>
     *
     * @return sell_value
     */
    public int getSell_value() {
        return sell_value;
    }

    /**
     * <b>Transformer</b>
     * <b>set buy value</b>
     *
     * @param buy_value
     */
    public void setBuy_value(int buy_value) {
        this.buy_value = buy_value;
    }

    /**
     * <b>Transformer</b>
     * <b>set sell value</b>
     *
     * @param sell_value
     */
    public void setSell_value(int sell_value) {
        this.sell_value = sell_value;
    }


    /**
     * <b>Transformer</b>
     * <b>setters for the name , action , to do String of the card</b>
     */
    public void setAction_Str(String action_Str) {
        this.action_Str = action_Str;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToDo_str(String toDo_str) {
        this.toDo_str = toDo_str;
    }

    /**
     * <b>Accessor</b><p>
     * <b>get the name of the card</b>
     *
     * @return card's name
     */

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * <b>Accessor</b><p>
     * <b>get the string of the action of the card</b>
     *
     * @return card's action info
     */
    @Override
    public String getAction_Str() {
        return this.action_Str;
    }

    /**
     * <b>Accessor</b><p>
     * <b>get the string of the "wat you have to do" of the card</b>
     *
     * @return what player has to do info<b>Example(Pay 50 euros)</b>
     */
    @Override
    public String getToDo_str() {
        return this.toDo_str;
    }

    @Override
    public void card_action(player p1, player p2, Jackpot j) {

    }

    @Override
    public String toString() {
        return "Deal_Card{ name : " + action_Str  +
                "\nbuy_value=" + buy_value +
                ",          sell_value=" + sell_value +
                '}';
    }
}
