package Model.Position;

import Model.Board.Dice;
import Model.Board.Tile.*;
import Model.Card.*;
import Model.Deck.Deck;
import Model.Player.player;

import java.util.ArrayList;

public class position {
    private int curr_position;
    private Tile curr_tile;
    private ArrayList<Tile> board=new ArrayList<>();

    /**
     * <b>Constructor</b>
     */
    public position() {
        int curr_position = 0;
    }

    /**
     * <b>Accessor</b><p>
     * <b>Return the tile</b>
     *
     * @return current tile
     */
    public Tile getCurr_tile() {
        return curr_tile;
    }

    /**
     * <b>Transformer</b>
     * <b>moves + the next position</b>
     *
     * @param next_position
     */
    public void move_tile(int next_position) {
        this.curr_position += next_position;
        this.curr_tile = board.get(curr_position);
    }

    /**
     * <b>Transformer</b>
     * sets the board of the position
     * HAS TO BE 32
     *
     * @param board
     */
    public void setBoard(ArrayList<Tile> board) {
        this.board = board;
    }

    /**
     * <b>Transformer</b>
     * sets the position
     *
     * @param curr_tile
     */
    public void setCurr_tile(int curr_tile) {
        this.curr_position = curr_tile;
        this.curr_tile = board.get(curr_position);
    }

    /**
     * <b>Accessor</b>
     * returns tiles position
     *
     * @return Tiles position
     */
    public String toString() {
        return curr_tile.toString();
    }

    /**
     * <b>Accessor</b>
     * returns position in days [1,31]
     *
     * @return curr_position
     */
    public int getNumber_of_the_tile() {
        return curr_position;
    }

    /**
     * <b>Transformer</b>
     * set's position in days [1,31]
     *
     * @param day (example (8))
     */
    public void setNumber_of_the_tile(int day) {
        this.curr_position = day;
    }

    /**
     * Tester without view
     * @param args
     */
    public static void main(String[] args) {

        Jackpot jackpot = new Jackpot();
        Dice p1dice = new Dice();
        Dice p2dice = new Dice();
        Tile[] board = new Tile[32];
        position pos1 = new position();
        player p1 = new player("p1", 1);
        player p2 = new player("p2", 2);
        for (int i = 0; i < 5; i++) {
            board[i] = new Casino_Night(i, "Monday");

        }

        //----------------------TEST TILES---------------------------------//
        /*
        Deal_Card deal_card = new Deal_Card(200, 500, "name", "", "");
        Deal_Card deal_card2 = new Deal_Card(400, 1000, "name2", "", "");

        for (int i = 0; i < 4; i++)
            p1.getDeal_Cards().add_card(deal_card);
        p1.getDeal_Cards().add_card(deal_card2);
        System.out.println(p1.getDeal_Cards().getCard(4));
        Buyer buyer = new Buyer(6, "Monday");
        Casino_Night casino_night = new Casino_Night(2, "trith");
        Lottery lottery = new Lottery(3, "tetarth");
        Payday payday = new Payday();
        Radio radio = new Radio(3, "pem");
        Sweepstakes sweepstakes = new Sweepstakes(3, "par");
        Tile_Deal tile_deal = new Tile_Deal(4, "sav");
        Yard_Sale yard_sale = new Yard_Sale(5, "hai");
        Tile_Mail tile_mail=new Tile_Mail(6,"hi");
        Tile_Mail2 tile_mail2=new Tile_Mail2(7,"hellp");
        board[5] = new Tile_Mail2(5, "Tuesday");
        board[6] = buyer;
        board[7] = casino_night;
        board[8] = jackpot;
        board[9] = lottery;
        board[10] = payday;
        board[11] = radio;
        board[12] = sweepstakes;
        board[13] = tile_deal;
        board[14] = yard_sale;
        board[15] = tile_mail;
        board[16] =tile_mail2;
        pos1.setBoard(board);
        p1.setCurr_Position(pos1);
        p2.setCurr_Position(pos1);

        p1.setPosition(16);
        Tile tile;
        tile = p1.getCurr_Position().getCurr_tile();

        Deck mail_deck = new Deck();
        Deck deal_deck = new Deck();
        deal_deck.init_Deal_Deck();
        mail_deck.init_Mail_Deck();
        deal_deck.shuffle();
        mail_deck.shuffle();

            tile.tile_action(p1,p2,mail_deck,jackpot,true,1,2,p1dice,p2dice);

        System.out.println(p1.toString());
        System.out.println(p2.toString());*/
        // System.out.println(jackpot.getJackpot_value());
        //  pos1.move_tile(2);

        //  System.out.println(pos1.getCurr_tile());
        // System.out.println(p1.toString());


      /*  --CHECK CARDS---
      Jackpot jackpot=new Jackpot();
        Pay_the_neighbour card2 =new Pay_the_neighbour();
        Advertisement card3 = new Advertisement();
        Bill card4 = new Bill();
        Charity card5=new Charity();
        Get_paid_from_neighbour card6=new Get_paid_from_neighbour();
        p1.setMoney(0);
        p2.setMoney(0);
        card6.card_action(p1,p2);
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(jackpot.getJackpot_value());
*/

    }
}
