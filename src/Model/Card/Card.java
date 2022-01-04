package Model.Card;

import Model.Board.Tile.Jackpot;
import Model.Deck.Deck;
import Model.Player.player;

public interface Card {

    /**
     * <b>post returns the info of the card</b>
     * @return the info of the card
     */
    String toString();

    /**<b>Accessor</b><p>
     *<b>get the name of the card</b>
     * @return card's name
     */
    String getName();
    /**<b>Accessor</b><p>
     *<b>get the string of the action of the card</b>
     * @return card's action info
     */
    String getAction_Str() ;
    /**<b>Accessor</b><p>
     *<b>get the string of the "wat you have to do" of the card</b>
     * @return what player has to do info<b>Example(Pay 50 euros)</b>
     */
     String getToDo_str();
    /**<b>Transformer</b><p>
     *<b>depends on the card an action happens</b>
     */
     void card_action(player p1, player p2, Jackpot j);
}
