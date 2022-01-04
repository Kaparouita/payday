package Model.Card;

import Model.Board.Dice;
import Model.Board.Tile.Jackpot;
import Model.Deck.Deck;
import Model.Player.player;

public abstract class Mail_Card extends Dice implements Card  {
    private String name,action_Str,toDo_str;
private int price;

Mail_Card(String name,String action_Str,String toDo_str,int price)
{
    this.price=price;
    this.name=name;
    this.action_Str=action_Str;
    this.toDo_str=toDo_str;
}
    /**<b>Accessor</b><p>
     *<b>get the price of the card</b>
     * @return card's price
     */
    public int getPrice() {
        return price;
    }
    /**<b>Transformer</b><p>
     *<b>set the price of the card</b>
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**<b>Accessor</b><p>
     *<b>get the name of the card</b>
     * @return card's name
     */
    public String getName() {
        return name;
    }
    /**<b>Accessor</b><p>
     *<b>get the string of the action of the card</b>
     * @return card's action info
     */
    public String getAction_Str() {
        return action_Str;
    }
    /**<b>Accessor</b><p>
     *<b>get the string of the "wat you have to do" of the card</b>
     * @return what player has to do info<b>Example(Pay 50 euros)</b>
     */
    public String getToDo_str() {
        return toDo_str;
    }

    /**<b>Transformer</b><p>
     *<b>depends on the card an action happens</b>
     */
    public abstract void card_action(player p1, player p2, Jackpot j);
    @Override
    public String toString() {
        return "Mail" +
                ", name='" + name + '\'' +
                ", action Str='" + action_Str + '\'' +
                ", toDo str='" + toDo_str + '\''
                ;
    }
}
