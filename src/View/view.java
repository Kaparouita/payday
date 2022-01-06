package View;

import Controller.controller;
import Model.Card.Deal_Card;
import Model.Player.player;
import com.sun.source.tree.ArrayTypeTree;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class view extends JFrame implements ActionListener {

    public static JFrame frame = new JFrame();
    static JLayeredPane main = new JLayeredPane();
    private JPanel board = new JPanel(new GridLayout(5, 7, 1, 15));

    ArrayList<JOptionPane> mainCards = new ArrayList<>();


    static JButton dealButton = new JButton();
    static JButton mailButton = new JButton();
    private JButton roll_dice1 = new JButton("Roll Dice"), roll_dice2 = new JButton("Roll Dice");
    private JButton Deal_cards1 = new JButton("Deal Cards"), Deal_cards2 = new JButton("Deal Cards");
    private JButton Get_Loan1 = new JButton("Get Loan"), Get_Loan2 = new JButton("Get Loan");
    private JButton End_turn1 = new JButton("End Turn"), End_turn2 = new JButton("End Turn");

    private JPanel info_Box = new JPanel();
    private JLabel info_name = new JLabel("INFO BOX"), info_months = new JLabel("0 Months left");
    private JLabel info_turn = new JLabel("Turn : Player1"), info_toDo = new JLabel("-->Roll the dice");

    private JLabel Jackpot = new JLabel("<html><font color='white'>Jackpot : 0 euros</font></html>");
    private JLabel Pawn1 = new JLabel(), Pawn2 = new JLabel();
    private JLabel Money1 = new JLabel("Money : 3500"), Money2 = new JLabel("Money : 3500");
    private JLabel Loan1 = new JLabel("Loan : 0"), Loan2 = new JLabel("Loan : 0");
    private JLabel Bills1 = new JLabel("Bills : 0"), Bills2 = new JLabel("Bills : 0");
    private JLabel Name1 = new JLabel("PLAYER 1"), Name2 = new JLabel("PLAYER 2");
    private JPanel player1_layer = new JPanel();
    private JPanel player2_layer = new JPanel();
    private JLabel background = new JLabel(new ImageIcon("./src/View/resources/Board_images/bd.jpg"));
    private JPanel days_bd = new JPanel();
    private JLabel logo = new JLabel();


    private JLabel Dice1[] = new JLabel[6], Dice2[] = new JLabel[6], dicetest = new JLabel();
    private ArrayList<JLabel> tiles = new ArrayList<JLabel>();

    private static controller c;
    private int y_tmp;
    private int day;
    private int x_tmp;

    // ImageIcon dice1=new ImageIcon("View/resources/Board_images/dice1.jpg");

    public view(controller c) throws IOException {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        days_bd.setBackground(Color.YELLOW);
        player1_layer.setBackground(Color.lightGray);
        player2_layer.setBackground(Color.lightGray);
        board.setBackground(Color.YELLOW);
        main.setOpaque(true);

        this.c = c;
        initButtons();
        main.add(Name1);
        main.add(Name2);
        main.add(Bills1);
        main.add(Loan1);
        main.add(Money1);
        main.add(roll_dice1);
        main.add(Deal_cards1);
        main.add(Get_Loan1);
        main.add(End_turn1);

        main.add(info_name);
        main.add(info_months);
        main.add(info_turn);
        main.add(info_toDo);

        main.add(Bills2);
        main.add(Loan2);
        main.add(Money2);
        main.add(roll_dice2);
        main.add(Deal_cards2);
        main.add(Get_Loan2);
        main.add(End_turn2);
        main.add(info_Box);

        initDays();
        /*need the controller so the module and the view have the same board*/
        initBoard(c);
        initPawns();
        initDices();
        initObjsPositionOnTheBoard();

        PayDayCards pdv = new PayDayCards();
        pdv.readFile("View/resources/dealCards_greeklish.csv", "Deal");
        pdv.readFile("View/resources/mailCards_greeklish.csv", "Mail");

        /* To ftiaksa se netbook kai htan full mikrh h othonh opote den evala to logo*/
        main.add(logo);
        info_Box.setBackground(Color.cyan);
        main.add(player1_layer);
        main.add(player2_layer);
        main.add(days_bd);
        main.add(board);
        main.add(background);
        frame.add(main, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

    }

    /**
     * returns the controller of the game
     *
     * @return
     */
    public static controller getC() {
        return c;
    }

    public void initObjsPositionOnTheBoard() {
        background.setBounds(0, 0, 1800, 1000);

        days_bd.setSize(700, 20);
        days_bd.setBounds(0, 0, 700, 20);

        board.setSize(700, 600);
        board.setBounds(0, 20, 700, 600);

        logo.setSize(1000, 100);
        logo.setBounds(700,1000,1000,100);

        mailButton.setBounds(720, 360, 120, 200);
        dealButton.setBounds(720, 140, 120, 200);

        Name1.setBounds(870, 35, 165, 30);
        Money1.setBounds(870, 75, 165, 30);
        Loan1.setBounds(870, 100, 165, 30);
        Bills1.setBounds(870, 125, 165, 30);
        roll_dice1.setBounds(870, 165, 120, 25);
        Deal_cards1.setBounds(870, 195, 120, 25);
        Get_Loan1.setBounds(870, 225, 120, 25);
        End_turn1.setBounds(1000, 225, 120, 25);
        player1_layer.setBounds(850, 15, 300, 250);

        info_Box.setBounds(850, 290, 300, 109);

        Name2.setBounds(870, 450, 165, 30);
        Money2.setBounds(870, 490, 165, 30);
        Loan2.setBounds(870, 515, 165, 30);
        Bills2.setBounds(870, 540, 165, 30);
        roll_dice2.setBounds(870, 580, 120, 25);
        Deal_cards2.setBounds(870, 605, 120, 25);
        Get_Loan2.setBounds(870, 630, 120, 25);
        End_turn2.setBounds(1000, 630, 120, 25);

        player2_layer.setBounds(850, 430, 300, 250);


        info_name.setBounds(870, 290, 300, 30);
        info_months.setBounds(870, 315, 300, 30);
        info_turn.setBounds(870, 335, 300, 30);
        info_toDo.setBounds(870, 355, 300, 30);

        logo.setBounds(0, 680, 750, 130);
        Jackpot.setBounds(470, 630, 300, 30);

        frame.setSize(1200, 900);
        main.setPreferredSize(new Dimension(1200, 780));
    }

    /**
     * <b>Transformer</b>
     * <b>post  :</b>initialize the Days above the tiles
     */
    public void initDays() {
        JLabel text7 = new JLabel("START ");
        text7.setBounds(5, 0, 165, 25);
        main.add(text7);
        y_tmp = 123;
        day = 7;
        for (int i = 0; i < 4; i++) {
            JLabel text = new JLabel("SUNDAY " + day);
            text.setBounds(5, y_tmp, 165, 25);//122
            text.setBackground(Color.WHITE);
            main.add(text);
            y_tmp = y_tmp + 123;
            day += 7;
        }
        y_tmp = 0;
        day = 1;
        x_tmp = 110;
        for (int i = 0; i < 5; i++) {
            JLabel text = new JLabel("MONDAY " + day);
            text.setBounds(x_tmp, y_tmp, 165, 25);
            main.add(text);
            JLabel text2 = new JLabel("TUESDAY " + (day + 1));
            text2.setBounds(x_tmp + 100, y_tmp, 165, 25);
            main.add(text2);
            JLabel text3 = new JLabel("WEDNES. " + (day + 2));
            text3.setBounds(x_tmp + 200, y_tmp, 165, 25);
            main.add(text3);
            if (i < 4) {
                JLabel text4 = new JLabel("THURSDAY " + (day + 3));
                text4.setBounds(x_tmp + 300, y_tmp, 165, 25);
                main.add(text4);
                JLabel text5 = new JLabel("FRIDAY " + (day + 4));
                text5.setBounds(x_tmp + 400, y_tmp, 165, 25);
                main.add(text5);
                JLabel text6 = new JLabel("SATURDAY " + (day + 5));
                text6.setBounds(x_tmp + 500, y_tmp, 165, 25);
                main.add(text6);
            }
            y_tmp = y_tmp + 123;
            day += 7;
        }
    }

    /**
     * <b>Transformer</b>
     * <b>post  :</b>initialize the board and some components
     */
    public void initBoard(controller c) {
        ImageIcon lotIcon = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/lottery.png").getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));
        ImageIcon dealIcon = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/deal.png").getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));
        ImageIcon casinoIcon = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/casino.png").getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));
        ImageIcon buyerIcon = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/buyer.png").getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));
        ImageIcon radioIcon = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/radio.png").getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));
        ImageIcon sweeplIcon = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/sweep.png").getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));
        ImageIcon mc1Icon = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/mc1.png").getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));
        ImageIcon mc2Icon = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/mc2.png").getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));
        ImageIcon yardIcon = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/yard.png").getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));
        ImageIcon startIcon = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/start.png").getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));

        JLabel start = new JLabel(startIcon);
        board.add(start);

        for (int i = 0; i < 30; i++) {

            JLabel lottery = new JLabel(lotIcon);
            lottery.setName("lottery");
            JLabel deal = new JLabel(dealIcon);
            deal.setName("deal");
            JLabel buyer = new JLabel(buyerIcon);
            buyer.setName("buyer");
            JLabel radio = new JLabel(radioIcon);
            radio.setName("radio");
            JLabel sweep = new JLabel(sweeplIcon);
            sweep.setName("sweep");
            JLabel mc1 = new JLabel(mc1Icon);
            mc1.setName("mc1");
            JLabel mc2 = new JLabel(mc2Icon);
            mc2.setName("mc2");
            JLabel casino = new JLabel(casinoIcon);
            casino.setName("casino");
            JLabel yard = new JLabel(yardIcon);
            yard.setName("yard");

            if (c.getBoard().get(i).getName().equals("mc1")) {
                tiles.add(mc1);
            } else if (c.getBoard().get(i).getName().equals("mc2")) {
                tiles.add(mc2);
            } else if (c.getBoard().get(i).getName().equals("yard")) {
                tiles.add(yard);
            } else if (c.getBoard().get(i).getName().equals("casino")) {
                tiles.add(casino);
            } else if (c.getBoard().get(i).getName().equals("buyer")) {
                tiles.add(buyer);
            } else if (c.getBoard().get(i).getName().equals("sweep")) {
                tiles.add(sweep);
            } else if (c.getBoard().get(i).getName().equals("deal")) {
                tiles.add(deal);
            } else if (c.getBoard().get(i).getName().equals("radio")) {
                tiles.add(radio);
            } else if (c.getBoard().get(i).getName().equals("lottery")) {
                tiles.add(lottery);
            }

        }

        ImageIcon payIcon = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/pay.png").getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));

        JLabel payday = new JLabel(payIcon);
        tiles.add(payday);

        for (int i = 0; i < 31; i++) {
            board.add(tiles.get(i));
        }
        ImageIcon jackpotIcon = new ImageIcon("./src/View/resources/Board_images/jackpot.png");
        JLabel jackpot = new JLabel(jackpotIcon);
        ImageIcon logoIcon = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/logo.png").
                getImage().getScaledInstance(750, 130, Image.SCALE_DEFAULT));
        logo = new JLabel(logoIcon);
        jackpot.setBounds(380, 383, 400, 350);
        Jackpot.setFont(new Font("jackpot", Font.PLAIN, 25));
        main.add(Jackpot);
        main.add(jackpot);
        ImageIcon mailIcon = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/mailCard.png").
                getImage().getScaledInstance(120, 200, Image.SCALE_DEFAULT));
        ImageIcon deacon = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/dealCard.png").
                getImage().getScaledInstance(120, 200, Image.SCALE_DEFAULT));
        dealButton = new JButton(deacon);
        mailButton = new JButton(mailIcon);
    }

    /**
     * <b>Transformer</b>
     * <b>post : init some buttons</b>
     */
    public void initButtons() {
        roll_dice1.addActionListener(this);
        roll_dice2.addActionListener(this);

        Get_Loan1.addActionListener(this);
        Get_Loan2.addActionListener(this);

        Deal_cards1.addActionListener(this);
        Deal_cards2.addActionListener(this);

        End_turn1.addActionListener(this);
        End_turn2.addActionListener(this);
    }

    /**
     * <b>Transformer</b>
     * <b>init the pawns</b>
     */
    public void initPawns() {
        ImageIcon pawn1Icon = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/pawn_blue.png").getImage().getScaledInstance(40, 60, Image.SCALE_DEFAULT));
        ImageIcon pawn2Icon = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/pawn_yellow.png").getImage().getScaledInstance(40, 60, Image.SCALE_DEFAULT));


        Pawn1 = new JLabel(pawn1Icon);
        Pawn2 = new JLabel(pawn2Icon);
        Pawn1.setBounds(20, 50, 40, 60);
        Pawn2.setBounds(40, 50, 40, 60);
        main.add(Pawn1);
        main.add(Pawn2);
    }

    /**
     * <b>Transformer</b>
     * <b>init the dices</b>
     */
    public void initDices() {

        ImageIcon dices[] = new ImageIcon[6];
        dices[0] = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/dice1.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        dices[1] = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/dice2.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        dices[2] = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/dice3.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        dices[3] = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/dice4.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        dices[4] = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/dice5.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        dices[5] = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/dice6.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));

        for (int i = 0; i < 6; i++) {

            Dice1[i] = new JLabel(dices[i]);
            Dice1[i].setBounds(1030, 120, 100, 100);
            Dice2[i] = new JLabel(dices[i]);
            Dice2[i].setBounds(1030, 520, 100, 100);
            Dice1[i].setVisible(false);
            Dice2[i].setVisible(false);
            main.add(Dice1[i]);
            main.add(Dice2[i]);

        }
        Dice1[0].setVisible(true);
        Dice2[0].setVisible(true);
    }

    /**
     * <b>Transformer</b>
     * <b>post  :</b>change dice1 value
     */
    public void Dice1move(int dice_value) {
        for (int i = 0; i < 6; i++)
            Dice1[i].setVisible(false);
        Dice1[dice_value - 1].setVisible(true);
    }

    /**
     * <b>Transformer</b>
     * <b>post  :</b>change dice2 value
     */
    public void Dice2move(int dice_value) {
        for (int i = 0; i < 6; i++)
            Dice2[i].setVisible(false);
        Dice2[dice_value - 1].setVisible(true);
    }

    /**
     * <b>Transformer</b>
     * <b>post  :</b>change jackpot/money/loan/bills labels of the players
     */

    public void change_values_of_money() {
        Money1.setText("Money : " + c.getP1().getMoney());
        Money2.setText("Money : " + c.getP2().getMoney());
        Loan1.setText("Loan : " + c.getP1().getLoan());
        Loan2.setText("Loan : " + c.getP2().getLoan());
        Bills1.setText("Bills : " + c.getP1().getBills());
        Bills2.setText("Bills : " + c.getP2().getBills());
        Jackpot.setText("<html><font color='white'>Jackpot : " + c.getJackpot().getJackpot_value() + " euros</font></html>");
    }

    /**
     * <b>Transformer</b>
     * <b>sets the info of the months (at info box)</b>
     */
    public void setInfo_months() {
        info_months.setText("Months left for P1 : " + c.getCurr_month1() + " , Months left for P2 : " + c.getCurr_month2());
    }

    /**
     * <b>Transformer</b>
     * <b>sets the info of the turn (at info box)</b>
     */
    public void setInfo_turn(String s) {
        info_turn.setText(s);
    }
    /**
     * <b>Transformer</b>
     * <b>sets the info of the to_Do(wat to do) (at info box)</b>
     */
    public void setInfo_toDo(String info_toDo) {
        this.info_toDo.setText(info_toDo);
    }
    /**
     * <b>Transformer</b>
     * <b>sets the position of the pawns
     * depending on the position of the player on the board</b>
     */
    public void PawnsMove(int position, int playerId) {
        int y = position;
        while (y > 7)
            y = y - 7;
        if (y == 7)
            y = 0;
        int x = 100 * y;
        if (playerId == 1) {

            if (position < 7) {
                Pawn1.setBounds(20 + x, 50, 40, 60);
            } else if (position < 14) {
                Pawn1.setBounds(20 + x, 170, 40, 60);
            } else if (position < 21) {
                Pawn1.setBounds(20 + x, 290, 40, 60);
            } else if (position < 28) {
                Pawn1.setBounds(20 + x, 410, 40, 60);
            } else if (position < 32)
                Pawn1.setBounds(20 + x, 530, 40, 60);

        } else if (playerId == 2) {
            if (position < 7) {
                Pawn2.setBounds(40 + x, 50, 40, 60);
            } else if (position < 14) {
                Pawn2.setBounds(40 + x, 170, 40, 60);
            } else if (position < 21) {
                Pawn2.setBounds(40 + x, 290, 40, 60);
            } else if (position < 28) {
                Pawn2.setBounds(40 + x, 410, 40, 60);
            } else if (position < 32) {
                Pawn2.setBounds(40 + x, 530, 40, 60);
            }
        }
    }

    /**
     * <b>Transformer</b>
     * <b>post  :</b>updates player pawn to start
     */
    public void PawnStarterPos(player p1) {
        if (p1.getId() == 1) {
            Pawn1.setBounds(20, 50, 40, 60);
        } else
            Pawn2.setBounds(40, 50, 40, 60);
    }




    /**
     * <b>Transformer</b>
     * <b>post  :</b>updated info box label
     */
    public void infoBox() {
        if (c.getP1().getHas_finished()) {
            info_turn.setText("Turn : Player2");
        } else if (c.getP2().getHas_finished())
            info_turn.setText("Turn : Player1");
        info_months.setText("Months left : " + c.getCurr_month1());
        info_toDo.setText("-->Roll the dice!");

    }

    /**<b>Transformer</b>
     * buttons actions
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        /*if its roll_dice1*/
        if (e.getSource() == roll_dice1) {
            if (c.getCurr_month1() == 0)
                JOptionPane.showMessageDialog(null, "No more months left!","Months",JOptionPane.ERROR_MESSAGE);
            else if (c.isMaildraw() || c.isDealdraw()) {
                JOptionPane.showMessageDialog(null, "Draw a card first!","Draw",JOptionPane.ERROR_MESSAGE);
            } else {
                if (c.getP1().getHas_finished())
                    JOptionPane.showMessageDialog(null, "Its not your turn!","Turn",JOptionPane.ERROR_MESSAGE);
                else {
                    c.rollDiceAndMove(c.getP1());
                    if (!c.isDealdraw() && !c.isMaildraw() && !c.isMaildraw2()) {
                        if (c.getCurr_month2() == 0) {
                            c.getP1().setHas_finished(false);
                            c.getP2().setHas_finished(true);
                        } else {
                            c.getP1().setHas_finished(true);
                            c.getP2().setHas_finished(false);
                        }
                    }
                }
            }
        }
        /*if its roll_dice2*/
        if (e.getSource() == roll_dice2) {
            if (c.getCurr_month2() == 0)
                JOptionPane.showMessageDialog(null, "No more months left!","Months",JOptionPane.ERROR_MESSAGE);

            else {
                if (c.getP2().getHas_finished())
                    JOptionPane.showMessageDialog(null, "Its not your turn!","Turn",JOptionPane.ERROR_MESSAGE);
                else if (c.isMaildraw() || c.isDealdraw()) {
                    JOptionPane.showMessageDialog(null, "Draw a card first!","Draw",JOptionPane.ERROR_MESSAGE);
                } else {
                    c.rollDiceAndMove(c.getP2());
                    if (!c.isDealdraw() && !c.isMaildraw() && !c.isMaildraw2()) {
                        if (c.getCurr_month1() == 0) {
                            c.getP2().setHas_finished(false);
                            c.getP1().setHas_finished(false);
                        } else {
                            c.getP2().setHas_finished(true);
                            c.getP1().setHas_finished(false);
                        }
                    }
                }
            }
        }
        /*if its Loan1*/
        if (e.getSource() == Get_Loan1) {
            if (c.getCurr_month1() == 0)
                JOptionPane.showMessageDialog(null, "No more months left!","Months",JOptionPane.ERROR_MESSAGE);
            else {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to get a Loan?", "Get Loan", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_NO_OPTION) {
                    if (c.getP1().getHas_finished())
                        JOptionPane.showMessageDialog(null, "Its not your turn!","Turn",JOptionPane.ERROR_MESSAGE);
                    else {
                        c.getP1().Loan();
                        change_values_of_money();
                        info_toDo.setText("Player 1 got Loan! Now roll the dice!");
                    }
                }
            }
        }
        /*if its Loan2*/
        if (e.getSource() == Get_Loan2) {
            if (c.getCurr_month2() == 0)
                JOptionPane.showMessageDialog(null, "No more months left!","Months",JOptionPane.ERROR_MESSAGE);
            else {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to get a Loan?", "Get Loan", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_NO_OPTION) {
                    if (c.getP2().getHas_finished())
                        JOptionPane.showMessageDialog(null, "Its not your turn!","Turn",JOptionPane.ERROR_MESSAGE);
                    else {
                        c.getP2().Loan();
                        change_values_of_money();
                        info_toDo.setText("Player 2 got Loan! Now roll the dice!");
                    }
                }
            }
        }
        /*if its End_turn1*/
        if (e.getSource() == End_turn1) {
            if (c.getCurr_month1() == 0)
                JOptionPane.showMessageDialog(null, "No more months left!","Months",JOptionPane.ERROR_MESSAGE);
            else if (c.isMaildraw() || c.isDealdraw()) {
                JOptionPane.showMessageDialog(null, "Draw a card first!","Draw",JOptionPane.ERROR_MESSAGE);
            } else {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to end your turn?", "Get Loan", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_NO_OPTION) {
                    c.getP1().setHas_finished(true);
                    c.getP2().setHas_finished(false);
                }
            }
        }
        /*if its End_turn2*/
        if (e.getSource() == End_turn2) {
            if (c.getCurr_month2() == 0)
                JOptionPane.showMessageDialog(null, "No more months left!","Months",JOptionPane.ERROR_MESSAGE);
            else if (c.isMaildraw() || c.isDealdraw()) {
                JOptionPane.showMessageDialog(null, "Draw a card first!","Draw",JOptionPane.ERROR_MESSAGE);
            } else {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to end your turn?", "Get Loan", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_NO_OPTION) {
                    c.getP2().setHas_finished(true);
                    c.getP1().setHas_finished(false);
                }
            }
        }
        /*if its Deal_cards1*/
        if (e.getSource() == Deal_cards1) {
            if (c.getCurr_month1() == 0)
                JOptionPane.showMessageDialog(null, "No more months left!","Months",JOptionPane.ERROR_MESSAGE);
            else {
                String cards = "";
                for (int i = 0; i < c.getP1().getDeal_Cards().getDeck().size(); i++)
                    cards += i + " : " + c.getP1().getDeal_Cards().getCard(i).toString() + "\n";
                JOptionPane.showMessageDialog(null, cards);
            }

        }
        /*if its Deal_cards2*/
        if (e.getSource() == Deal_cards2) {
            if (c.getCurr_month2() == 0)
                JOptionPane.showMessageDialog(null, "No more months left!","Months",JOptionPane.ERROR_MESSAGE);
            else {
                String cards = "";
                for (int i = 0; i < c.getP2().getDeal_Cards().getDeck().size(); i++)
                    cards += i + " : " + c.getP2().getDeal_Cards().getCard(i).toString() + "\n";
                JOptionPane.showMessageDialog(null, cards);
            }

        }
    }
}

