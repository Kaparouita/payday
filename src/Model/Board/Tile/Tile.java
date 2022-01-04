package Model.Board.Tile;

import Model.Deck.Deck;
import Model.Player.player;
import Model.Board.Dice;

public abstract class Tile extends Dice {
    private int day;
    private String str_day;
    private String name;


    /**<b>Constructor</b>
     * Creates a new TILE
     *
     * @param day
     * @param str_day
     * @param name
     */
    public Tile(int day, String str_day, String name) {
        super();
        this.day = day;
        this.str_day = str_day;
        this.name = name;
    }

    /**<b>Transformer</b>
     * set name of the tile
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**<b>Transformer</b>
     * set number of the day of the tile
     * @param day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**<b>Transformer</b>
     * set name of day of the tile
     * @param str_day
     */
    public void setStr_day(String str_day) {
        this.str_day = str_day;
    }
    /**
     * <b>Accessor</b><p>
     * <b>post returns name of the tile</b>
     * @return the name of the tile
     */
    public String getName() {
        return name;
    }

    /**
     * <b>Accessor</b><p>
     * returns the number of the Tile
     * <b>post :return [1,32]</b>
     *
     * @return the number of the tile
     */
    public int getDay() {
        return day;
    }

    /**
     * <b>Accessor</b><p>
     * returns the Day of the Tile
     * @return the name of the day
     */
    public String getStr_day() {
        return str_day;
    }

    /**<b>Transformer</b>
     * <b>if day equals thursday you can bet on crypto</b>
     * <b>else if day equals Sunday you can bet on football</b>
     * @param p1
     */
    public void day_action(player p1){
        /*===========CRYPTO=================*/
        if (this.str_day.equals("Thursday"))
        {

        }
        /*===========FOOTBALL=================*/
        else if (this.str_day.equals("Sunday"))
        {

        }
        return;

    }

    /**<b>Accessor</b><p>
     * <b>Returns the info of the tile</b>
     * @return tile's info
     */
    @Override
    public String toString() {
        return "Tile{" +
                "day=" + day +
                ", str_day='" + str_day + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * <b>Transformer</b>
     * <b>has the option to sell a Deal card</b>
     * <b>if sell is true then sell</b>
     *
     * @param p1
     */
public abstract void tile_action(player p1, player p2, Deck deck,Deck ddeck, Jackpot j);
}
