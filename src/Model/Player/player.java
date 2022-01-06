package Model.Player;

import Model.Deck.Deck;
import Model.Position.position;
import Model.Board.Dice;

public class player extends Dice {
private String name;
private int Money,Bills,Loan;
private final int id;
private Deck Deal_Cards=null;
private position Curr_Position;
private int position;
private boolean has_finished,is_first_turn=true;
private  Dice pdice;


    /**<b>Constructor</b>
     *<b>Constructs a new player</b>
     * @param name the name of the player
     * @param id the id of the player
     */
    public player(String name, int id) {

    this.id=id;
    this.name=name;
    this.Money=3500;
    this.Bills=0;
    this.Loan=0;
    this.Deal_Cards=new Deck();

    this.has_finished=false;
}
    /**<b>Transformer</b><p>
     * <b>Post :</b> set if player played his first turn
     * @param is_first_turn
     */
    public void setIs_first_turn(boolean is_first_turn) {
        this.is_first_turn = is_first_turn;
    }
    /**
     *<b>Accessor</b><p>
     *<b>Post :</b> Returns if its first turn
     * @return is_first_turn
     */
    public boolean isIs_first_turn() {
        return is_first_turn;
    }
    /**<b>Transformer</b><p>
     * <b>Post :</b> set players dice
     * @param p1dice
     */
    public void setPdice(Dice p1dice) {
        this.pdice = p1dice;
    }
    /**
     *<b>Accessor</b><p>
     *<b>Post :</b> Returns the dice of the player
     * @return dice
     */
    public Dice getPdice() {
        return pdice;
    }

    /**<b>Transformer</b><p>
     * <b>Post :</b> set bills of the player
     * @param bills
     */
    public void setBills(int bills) {
        Bills = bills;
    }

    /**<b>Transformer</b><p>
     * <b>Post :</b> set Loan of the player
     * @param loan
     */
    public void setLoan(int loan) {
        Loan = loan;
    }

    /**<b>Transformer</b><p>
     *<b>Post :</b> set money of the player
     * @param money
     */
    public void setMoney(int money) {
        Money = money;
    }

    /**
     *<b>Accessor</b><p>
     *<b>Post :</b> Returns the Bills of the player
     * @return Bills
     */
    public int getBills() {
        return Bills;
    }
    /**
     *<b>Accessor</b><p>
     *<b>Post :</b> Returns the Loan of the player
     * @return Loan
     */
    public int getLoan() {
        return Loan;
    }

    /**<b>Transformer</b>
     * <b>get a 1000 euro loan</b>
     */
    public void Loan(){
        this.Loan+=1000;
        this.Money+=1000;
    }
    /**
     *<b>Accessor</b><p>
     *<b>Post :</b> Returns the Money of the player
     * @return Money
     */
    public int getMoney() {
        return Money;
    }
    /**
     *<b>Accessor</b><p>
     *<b>Post :</b> Returns the ID of the player
     * @return ID
     */
    public int getId() {
        return id;
    }

    /**<b>Accessor</b><p>
     * <b>Post :</b> Returns the DEAL cards of the player
     * @return The array with the deal cards the player owns
     * <b>returns null if owns nothing</b>
     */
    public Deck getDeal_Cards() {
        return Deal_Cards;
    }

    /**<b>Transformer</b><p>
     * <b>Post :</b> Set the new position (set sto board)
     * @param new_Position
     */
    public void setCurr_Position(position new_Position) {
        Curr_Position = new_Position;
    }

    /**<b>Accessor</b><p>
     * <b>Post :</b> Returns the position of the player
     * @return the current position of the player
     */
    public position getCurr_Position() {
        return Curr_Position;
    }

    /**<b>Transformer</b>
     * set the position
     * @param position number [1,31]
     */
    public void setPosition(int position) {
        this.position = position;
        this.Curr_Position.setCurr_tile(position);
    }

    public void setDeal_Cards(Deck deal_Cards) {
        Deal_Cards = deal_Cards;
    }

    /**<b>Accessor</b>
     * @return the number of the position [1,31]
     */
    public int getPositionNumber() {
        return Curr_Position.getNumber_of_the_tile();
    }

    /**<b>Transformer</b>
     * sets finished
     * @param has_finished
     */
    public void setHas_finished(boolean has_finished) {
        this.has_finished = has_finished;
    }

    /**
     * <b>Accessor</b><p>
     * @return true if finished
     */
    public boolean getHas_finished(){
        return has_finished;
    }


    /**<b>Accessor</b><p>
     * <b>Post :</b> Returns player's info
     * @return player's info
     */
    @Override
    public String toString() {
        return "player{" +
                "name='" + name + '\'' +
                ", Money=" + Money +
                ", Bills=" + Bills +
                ", Loan=" + Loan +
                ", id=" + id +
                ", Curr_Position=" + Curr_Position +
                ", has_finished=" + has_finished +
                '}';
    }
}

