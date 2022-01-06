package Controller;

import Model.Board.Dice;
import Model.Board.Tile.*;
import Model.Card.Card;
import Model.Card.Deal_Card;
import Model.Deck.Deck;
import Model.Player.player;
import Model.Position.position;
import View.view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;

import static java.lang.System.exit;


public class controller {
    private int allmonths, curr_month1, curr_month2;
    private player p1, p2;
    private Deck Mail_Deck = new Deck();
    private Deck Deal_Deck = new Deck();
    private Deck P1_Deck = new Deck(), P2_Deck = new Deck();
    private Dice dice1 = new Dice();
    private Dice dice2 = new Dice();
    private Jackpot jackpot = new Jackpot();
    private position position1 = new position();
    private position position2 = new position();
    private ArrayList<Tile> board = new ArrayList<Tile>();
    private boolean is_finished;
    private boolean dealdraw = false;
    private boolean maildraw = false, maildraw2 = false;
    private boolean maildraw2_moveto = false;
    private view view;

    public controller() throws IOException {
        p1 = new player("P1", 1);
        p2 = new player("P2", 2);
        p1.setPdice(dice1);
        p2.setPdice(dice2);
        p1.setDeal_Cards(P1_Deck);
        p2.setDeal_Cards(P2_Deck);
        start();
        init_board();
        view = new view(this);
        position1.setBoard(board);
        position2.setBoard(board);
        p1.setCurr_Position(position1);
        p2.setCurr_Position(position2);
        p1.setPosition(0);
        p2.setPosition(0);
        Mail_Deck.init_Mail_Deck();
        Deal_Deck.init_Deal_Deck();
        Mail_Deck.shuffle();
        Deal_Deck.shuffle();
        view.infoBox();
        view.change_values_of_money();
    }

    /**
     * <b>Transformer</b>
     * Before the game starts ask the players how many
     * months the game should last
     */
    private void start() {
        boolean b = true;
        while (b) {
            String months = (String) JOptionPane.showInputDialog(null, "Before we start the game," +
                            "how many months you want to last(choose 1-3)", "Start"
                    , JOptionPane.QUESTION_MESSAGE);
            if (isInteger(months, 5)) {
                int months_ofthegame = Integer.parseInt(months);
                if (months_ofthegame > 0 && months_ofthegame < 4) {
                    allmonths = months_ofthegame;
                    curr_month1 = months_ofthegame;
                    curr_month2 = months_ofthegame;
                    b = false;
                } else {
                    JOptionPane.showMessageDialog(null, "The number has to be [1,3]", "Start",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "This is not a number", "Start",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        if (first()) {
            p1.setHas_finished(true);
            p2.setHas_finished(false);
            JOptionPane.showMessageDialog(null, "Player 2 will go first!", "Start",JOptionPane.INFORMATION_MESSAGE);
        } else {
            p2.setHas_finished(true);
            p1.setHas_finished(false);
            JOptionPane.showMessageDialog(null, "Player 1 will go first!", "Start",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**<b>Accessor</b>
     * return true if the player need to draw2 cards
     * and the first one is move to
     * @return
     */
    public boolean isMaildraw2_moveto() {
        return maildraw2_moveto;
    }
    /**<b>Transformer</b>
     * set true if the player need to draw2 cards
     * and the first one is move to
     *
     */
    public void setMaildraw2_moveto(boolean maildraw2_moveto) {
        this.maildraw2_moveto = maildraw2_moveto;
    }
    /**<b>Transformer</b>
     * set true if the player need to draw a mail card
     */
    public void setMaildraw(boolean maildraw) {
        this.maildraw = maildraw;
    }
    /**<b>Accessor</b>
     * return true if the player need to draw a mail card
     * @return maildraw
     */
    public boolean isMaildraw() {
        return maildraw;
    }
    /**<b>Accessor</b>
     * return the graphic module of the game
     * @return view
     */
    public View.view getView() {
        return view;
    }

    /**
     * <b>Transformer</b>
     * At the start of the game ask the player
     * how many months the game is going to last
     * [1,3]
     */
    public void setAllmonths(int allmonths) {
        this.allmonths = allmonths;
    }

    /**
     * <b>Accessor</b>
     * choose who is playing first
     *
     * @return true if p1 is plays first
     * false if p2 plays first
     */
    private boolean first() {
        Random random = new Random();
        int tmp = random.nextInt(2) + 1;
        if (tmp == 1) {
            return true;
        } else return false;
    }

    /**
     * <b>Accessor</b>
     * returns the Jackpot of the game
     *
     * @return
     */
    public Jackpot getJackpot() {
        return jackpot;
    }

    /**
     * <b>Accessor</b>
     * returns the mail deck of the game
     *
     * @return
     */
    public Deck getMail_Deck() {
        return Mail_Deck;
    }

    /**
     * <b>Accessor</b>
     * returns the Deal deck of the game
     *
     * @return
     */
    public Deck getDeal_Deck() {
        return Deal_Deck;
    }

    /**
     * <b>Observer</b>
     * return true if a player has to draw a deal card
     *
     * @return
     */
    public boolean isDealdraw() {
        return dealdraw;
    }

    /**
     * <b>Transformer</b>
     * sets if a player has to draw a deal card
     * set true if he needs to
     */
    public void setDealdraw(boolean dealdraw) {
        this.dealdraw = dealdraw;
    }

    /**
     * <b>Observer</b>
     * return true if a player has to draw a <b>2</b> mail cards
     *
     * @return
     */
    public boolean isMaildraw2() {
        return maildraw2;
    }

    /**
     * <b>Transformer</b>
     * sets if a player has to draw 2 mail cards
     * set true if he needs to
     */
    public void setMaildraw2(boolean maildraw2) {
        this.maildraw2 = maildraw2;
    }

    /**
     * <b>Transformer</b>
     * players swap turns
     */
    public void swapturns() {
        if (getturn() == 2) {
            p1.setHas_finished(false);
            p2.setHas_finished(true);
        } else {
            p1.setHas_finished(true);
            p2.setHas_finished(false);
        }
    }

    /**
     * <b>Transformer</b>
     * Player rolls the dice and move
     * in graphics
     */
    public void rollDiceAndMove(player p1) {
        int tmp = 0;
        player dummy = new player(",", 0);

        /*roll the dice and change the dice value*/
        if (p1.getId() == 1) {
            tmp = dice1.roll_Dice();
            view.Dice1move(tmp);
            dummy = this.p2;
        } else if (p1.getId() == 2) {
            tmp = dice2.roll_Dice();
            view.Dice2move(tmp);
            dummy = this.p1;
        }
        /*check if tmp=6 in order to win the jackpot */
        Jackpot(tmp);

        if (p1.getPositionNumber() + tmp > 30) {
            tmp = 30;
            p1.setPosition(tmp);
            view.PawnsMove(tmp + 1, p1.getId());
        } else if (p1.isIs_first_turn()) {
            tmp = tmp - 1;
            p1.setIs_first_turn(false);
            p1.setPosition(p1.getPositionNumber() + tmp);
            view.PawnsMove(p1.getPositionNumber() + 1, p1.getId());
        } else {
            p1.setPosition(p1.getPositionNumber() + tmp);
            view.PawnsMove(p1.getPositionNumber() + 1, p1.getId());
        }
        //--------------if its deal---------------------------------------------------
        switch (p1.getCurr_Position().getCurr_tile().getName()) {
            case "deal" -> {
                JOptionPane.showMessageDialog(null, "Draw a deal card", "dealtile", JOptionPane.INFORMATION_MESSAGE);
                dealdraw = true;
                view.setInfo_toDo("-->Player " + p1.getId() + " draw a deal card!");
            }
//---------------------------------------if its Payday-------------------------------------
            case "payday" -> {
                if (p1.getId() == 1) {
                    curr_month1--;
                    if (curr_month1 == 0) {
                        this.p1.setHas_finished(true);
                        this.p1.setMoney(this.p1.getMoney() - this.p1.getLoan());
                        this.p1.setLoan(0);
                    }/*--An einai teleutaios mhnas plhrwse anagastika to loan----------------*/
                } else {
                    curr_month2--;
                    if (curr_month2 == 0) {
                        this.p2.setHas_finished(true);
                        this.p2.setMoney(this.p2.getMoney() - this.p2.getLoan());
                        this.p2.setLoan(0);
                    }
                }
                view.setInfo_months();
                p1.getCurr_Position().getCurr_tile().tile_action(p1, dummy, Mail_Deck, Deal_Deck, jackpot);
                boolean flag = true;
                p1.setIs_first_turn(true);
                view.PawnStarterPos(p1);
                // ean exei teliwsei to paixnidi
                if (is_game_finished()) {
                    String s = null;
                    view.change_values_of_money();
                    if (this.p1.getMoney() > p2.getMoney())
                        s = "P1 IS THE WINNER !!";
                    else
                        s = "P2 IS THE WINNER !!";
                    JOptionPane.showMessageDialog(null, "NO MORE MONTHS LEFT" +
                            "\n Player1 Money : " + this.p1.getMoney()
                            + "\nPlayer2 Money : " + this.p2.getMoney()
                            + "\n" + s, "GAME END", JOptionPane.INFORMATION_MESSAGE);
                    exit(0);
                }
            }

            case "mc1" -> {
                JOptionPane.showMessageDialog(null, "Draw a mail card", "Mailtile", JOptionPane.INFORMATION_MESSAGE);
                maildraw = true;
                view.setInfo_toDo("-->Player " + p1.getId() + " draw a mail card!");
            }
            case "mc2" -> {
                JOptionPane.showMessageDialog(null, "Draw 2 mail card", "Mailtile", JOptionPane.INFORMATION_MESSAGE);
                maildraw = true;
                maildraw2 = true;
                view.setInfo_toDo("-->Player " + p1.getId() + " draw 2 mail cards!");
            }
            case "yard", "casino", "radio", "sweep", "lottery", "buyer" -> {
                p1.getCurr_Position().getCurr_tile().tile_action(p1, dummy, Mail_Deck, Deal_Deck, jackpot);
            }
        }
        /*if its thursday or sunday ask for crypto or football*/
        performDayAction(p1);
        view.change_values_of_money();
        if (p1.getId() == 1 && !maildraw && !dealdraw && !maildraw2) {
            view.setInfo_turn("Turn : Player2");
        } else if (p1.getId() == 2 && !maildraw && !dealdraw && !maildraw2) {
            view.setInfo_turn("Turn : Player1");
        }

    }

    /**
     * <b>Accessor</b>
     * returns the curr month for the p1
     * curr month represents how many months left for p1
     *
     * @return
     */
    public int getCurr_month1() {
        return curr_month1;
    }

    /**
     * <b>Accessor</b>
     * returns the curr month for the p2
     * curr month represents how many months left for p2
     *
     * @return
     */
    public int getCurr_month2() {
        return curr_month2;
    }

    /**
     * <b>Accessor</b>
     * returns 1 if its player 1 turn ,returns 2 if its player 2 turn
     */
    public int getturn() {
        if (p1.getHas_finished())
            return 2;
        if (p2.getHas_finished())
            return 1;
        return 0;
    }

    /**
     * <b>Transformer</b>
     * Player roll to a day equals Thursday|Sunday
     */
   private void performDayAction(player p1) {
        JOptionPane q = new JOptionPane();
        if (p1.getCurr_Position().getCurr_tile().getStr_day().equals("Thursday")) {
            ImageIcon image2 = new ImageIcon(new ImageIcon("./src/View/resources/images/crypto.jpg")
                    .getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
            Object[] options1 = {"Gamble on crypto", "Ignore Crypto"};
            int k = q.showOptionDialog(null,
                    "Gamble 300 euros on crypto", "Crypto Thursday", JOptionPane.OK_OPTION,
                    0, image2, options1, options1[0]);
            if (k == 0) {
                int value = new Dice().roll_Dice();
                /*ean einai 1-2 xanei ta lefta
                 *ean einai 3-4 pernei ta lefta pisw
                 *ean einai 5-6 kerdizei ta dipla
                 */
                if (value == 1 || value == 2) {
                    p1.setMoney(p1.getMoney() - 300);
                    JOptionPane.showMessageDialog(null, "You lost your money!"
                            , "Crypto", JOptionPane.INFORMATION_MESSAGE, image2);
                } else if (value == 5 || value == 6) {
                    JOptionPane.showMessageDialog(null, "You won 600 euros!"
                            , "Crypto", JOptionPane.INFORMATION_MESSAGE, image2);
                    p1.setMoney(p1.getMoney() + 300);
                } else
                    JOptionPane.showMessageDialog(null, "You didn't lose but you didn't win either!"
                            , "Crypto", JOptionPane.INFORMATION_MESSAGE, image2);
            }


        } else if (p1.getCurr_Position().getCurr_tile().getStr_day().equals("Sunday")) {
            ImageIcon image2 = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/Barcelona_Real.jpg")
                    .getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
            Object[] options1 = {"Barcelona wins", "Real wins", "Its a draw", "I don't want to bet"};
            int k = q.showOptionDialog(null,
                    "Gamble on football", "Football Sunday", JOptionPane.OK_OPTION,
                    0, image2, options1, options1[0]);
            int value = new Dice().roll_Dice();
            if (k == 3) return;
            boolean bet = false;
            if (value == 1 || value == 2) value = 0;
            if (value == 3 || value == 4) value = 1;
            if (value == 5 || value == 6) value = 2;
            if (k == 0 && value == 0) bet = true;
            if (k == 1 && value == 1) bet = true;
            if (k == 2 && value == 2) bet = true;
            /*ean einai 1-2 nikh barcelona
             *ean einai 3-4 isopalia
             *ean einai 5-6 nikh real
             */
            if (bet) {
                p1.setMoney(p1.getMoney() + 500);
                JOptionPane.showMessageDialog(null, "You guessed correct! you win 500 euros!"
                        , "Football", JOptionPane.INFORMATION_MESSAGE, image2);

            } else {
                p1.setMoney(p1.getMoney() - 500);
                JOptionPane.showMessageDialog(null, "You guessed wrong you lose 500.."
                        , "Football", JOptionPane.INFORMATION_MESSAGE, image2);
            }

        }
    }

    /**
     * <b>Transformer</b>
     * Player roll 6 on
     */
    private void Jackpot(int tmp) {
        if (tmp == 6) {
            if (getturn() == 1)
                jackpot.tile_action(p1, p2, Mail_Deck, Deal_Deck, jackpot);
            else
                jackpot.tile_action(p2, p1, Mail_Deck, Deal_Deck, jackpot);

        }
    }


    /**
     * <b>Transformer</b>
     * int board with random order
     * 30=mail tile , 31=payday
     */
    private void init_board() {
        Buyer buyer = new Buyer();
        Casino_Night casino = new Casino_Night();
        Lottery lottery = new Lottery();
        Payday payday = new Payday();
        Radio radio = new Radio();
        Sweepstakes sweep = new Sweepstakes();
        Tile_Mail mc1 = new Tile_Mail();
        Tile_Mail2 mc2 = new Tile_Mail2();
        Tile_Deal deal = new Tile_Deal();
        Yard_Sale yard = new Yard_Sale();

        for (int i = 0; i < 2; i++) {
            board.add(casino);
            board.add(yard);
            board.add(radio);
            board.add(sweep);
            board.add(deal);//2 out of 5
            board.add(buyer);//2 out of 6
        }
        for (int i = 0; i < 3; i++) {
            board.add(deal);
            board.add(mc1);
            board.add(mc2);
            board.add(lottery);
            board.add(buyer);
        }
        board.add(mc2);
        board.add(mc1);
        Collections.shuffle(board);
        board.add(buyer);
        board.add(payday);
        ArrayList<String> days = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            days.add("Monday");
            days.add("Tuesday");
            days.add("Wednes");
            days.add("Thursday");
            days.add("Friday");
            days.add("Saturday");
            days.add("Sunday");
        }
        for (int i = 0; i < 31; i++) {
            int day = i + 1;
            if (board.get(i).getName().equals("mc1")) {
                board.set(i, new Tile_Mail(day, days.get(i)));
            } else if (board.get(i).getName().equals("mc2")) {
                board.set(i, new Tile_Mail2(day, days.get(i)));
            } else if (board.get(i).getName().equals("casino")) {
                board.set(i, new Casino_Night(day, days.get(i)));
            } else if (board.get(i).getName().equals("sweep")) {
                board.set(i, new Sweepstakes(day, days.get(i)));
            } else if (board.get(i).getName().equals("buyer")) {
                board.set(i, new Buyer(day, days.get(i)));
            } else if (board.get(i).getName().equals("lottery")) {
                board.set(i, new Lottery(day, days.get(i)));
            } else if (board.get(i).getName().equals("radio")) {
                board.set(i, new Radio(day, days.get(i)));
            } else if (board.get(i).getName().equals("yard")) {
                board.set(i, new Yard_Sale(day, days.get(i)));
            } else if (board.get(i).getName().equals("deal")) {
                board.set(i, new Tile_Deal(day, days.get(i)));
            }
        }
    }


    public ArrayList<Tile> getBoard() {
        return board;
    }

    /**
     * <b>Transformer</b>
     * Returns if game is finished
     *
     * @return if allmonths=curr_month and players finished
     * returns true
     */
    public boolean is_game_finished() {
        if (curr_month1 == 0 && curr_month2 == 0)
            return true;
        else
            return false;
    }

    /**
     * <b>Accessor</b>
     * Checks if a string is integer;
     *
     * @return true if the string is integer
     */
    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) return false;
                else continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0) return false;
        }
        return true;
    }

    /**
     * <b>Accessor</b>
     * if p1 is initialized return p1
     *
     * @return p1;
     * else return null
     */
    public player getP1() {
        return p1;
    }

    /**
     * <b>Accessor</b>
     * if p2 is initialized return p2
     *
     * @return p2;
     * else return null
     */
    public player getP2() {
        return p2;
    }

    /**
     * start the game
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        controller c = new controller();
    }
}
