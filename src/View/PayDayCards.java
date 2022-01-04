/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Board.Dice;
import Model.Board.Tile.Jackpot;
import Model.Board.Tile.Tile_Deal;
import Model.Card.*;
import Model.Deck.Deck;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * @author Mike
 */
public class PayDayCards extends JFrame {

    public ClassLoader cldr;
    int[] doublesMail = new int[48], doublesDeal = new int[20];
    int mailCardCount = 0, dealCardCount = 0;
    int deal_choice = 0;
    String[][] mailCards = new String[48][4];
    String[][] dealCards = new String[20][8];

    public PayDayCards() {
        cldr = this.getClass().getClassLoader();

        ImageIcon mailIcon = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/mailCard.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        ImageIcon dealIcon = new ImageIcon(new ImageIcon("./src/View/resources/Board_images/dealCard.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));

        //set Mail Button
        view.mailButton.setName("Mail");
        view.main.add(view.mailButton);
        view.mailButton.addActionListener(new CardListener());

        //set Deal Button

        view.dealButton.setName("Deal");
        view.dealButton.addActionListener(new CardListener());
        view.main.add(view.dealButton);


    }
/**<b>Transformer</b>
 * read the csv file with the deal/mail cards and
 * save them at mailCards/dealCards
 */
    public void readFile(String path, String type) {
        BufferedReader br = null;
        String sCurrentLine;
        try {
            String fullPath = cldr.getResource(path).getPath();
            br = new BufferedReader(new FileReader(fullPath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PayDayCards.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;
        int splitCount = 0;
        HashMap<Integer, String> domainsMap = new HashMap<>();
        try {
            br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                if (type.equals("Mail")) {
                    mailCards[count++] = sCurrentLine.split(",");
                } else {
                    dealCards[count++] = sCurrentLine.split(",");
                }
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(PayDayCards.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**<b>Transformer</b>
     * show on a JoptionPane the mail card with 1 option
     * @param i (which card)
     */
    public void showMailCard(int i) {
        Object[] options = {mailCards[i][3]};
        URL imageURL = cldr.getResource("View/resources/images/" + mailCards[i][5]); //image
        Image image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        System.out.println("Type: " + mailCards[i][0] + "\nTypeEn: " + mailCards[i][1]
                + "\nMessage:" + mailCards[i][2] + "\nChoice: " + mailCards[i][3] + "\nEuro:" + Integer.parseInt(mailCards[i][4]));
        JOptionPane p = new JOptionPane();
        int n = p.showOptionDialog(this,
                mailCards[i][2],
                mailCards[i][0],
                JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image),
                options,
                options[0]);
    }
    /**<b>Transformer</b>
     * show on a JoptionPane the deal card with 2 options
     * @param i (which card)
     */
    public void showDealCard(int i) {
        Object[] options = {dealCards[i][6], dealCards[i][7]};
        URL imageURL = cldr.getResource("View/resources/images/" + dealCards[i][5]); //image
        System.out.println("Type: " + dealCards[i][0] + "\nTypeEn: " + dealCards[i][1]
                + "\nMessage: " + dealCards[i][2] + "\nCost:" + Integer.parseInt(dealCards[i][3])
                + "\nValue:" + Integer.parseInt(dealCards[i][4]) + "\nChoice1: " + dealCards[i][6] + "\nChoice2: " + dealCards[i][7]);
        Image image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        JOptionPane p = new JOptionPane();


        //p.setLocation(50, 50);
        deal_choice = p.showOptionDialog(this,
                dealCards[i][2] + "\nΤιμή Αγοράς: " + dealCards[i][3] + " Ευρώ \nΤιμή Πώλησης: " + dealCards[i][4] + " Ευρώ \n",
                dealCards[i][0],
                JOptionPane.OK_OPTION,
                0,
                new ImageIcon(image),
                options,
                options[0]);


    }

    /**<b>Transformer</b>
     * <b>ActionListener for mail/deal button</b>
     */
    private class CardListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            /*------------------FOR MAIL---------------------------*/
            if (button.getName().equals("Mail")) {
                if (view.getC().isMaildraw()) {
                    /*Ean exw traviksei olh thn trapoula kane pali shuffle*/
                    if(mailCardCount==48){
                        for(int i=0;i<48;i++)
                            doublesMail[i]=0;
                    }
                    mailCardCount++;
                    int x = new Random().nextInt(48);
                    while (doublesMail[x] != 0) {
                        x = new Random().nextInt(48);
                    }
                    /*Check if you already drew that card*/
                    doublesMail[x] = x;
                    boolean move_to = false, move_to_mc2 = false;
                    Card c = new Move_to("", "", 0);
                    switch (mailCards[x][1]) {
                        /*Check whitch card you drew and create an instance of that card then add it to the deck*/
                        case "PayTheNeighbor" -> {
                            c = new Pay_the_neighbour(mailCards[x][2], mailCards[x][3],
                                    Integer.parseInt(mailCards[x][4]));
                            if (view.getC().getturn() == 1)
                                c.card_action(view.getC().getP1(), view.getC().getP2(), view.getC().getJackpot());
                            else
                                c.card_action(view.getC().getP2(), view.getC().getP1(), view.getC().getJackpot());
                        }
                        case "MadMoney" -> {
                            c = new Get_paid_from_neighbour(mailCards[x][2], mailCards[x][3],
                                    Integer.parseInt(mailCards[x][4]));
                            if (view.getC().getturn() == 1)
                                c.card_action(view.getC().getP1(), view.getC().getP2(), view.getC().getJackpot());
                            else
                                c.card_action(view.getC().getP2(), view.getC().getP1(), view.getC().getJackpot());
                        }
                        case "Αdvertisement" -> {
                            c = new Advertisement(mailCards[x][2], mailCards[x][3],
                                    Integer.parseInt(mailCards[x][4]));
                            if (view.getC().getturn() == 1)
                                c.card_action(view.getC().getP1(), view.getC().getP2(), view.getC().getJackpot());
                            else
                                c.card_action(view.getC().getP2(), view.getC().getP1(), view.getC().getJackpot());
                        }
                        case "Bill" -> {
                            c = new Bill(mailCards[x][2], mailCards[x][3],
                                    Integer.parseInt(mailCards[x][4]));
                            if (view.getC().getturn() == 1)
                                c.card_action(view.getC().getP1(), view.getC().getP2(), view.getC().getJackpot());
                            else
                                c.card_action(view.getC().getP2(), view.getC().getP1(), view.getC().getJackpot());
                        }
                        case "Charity" -> {
                            c = new Charity(mailCards[x][2], mailCards[x][3],
                                    Integer.parseInt(mailCards[x][4]));
                            if (view.getC().getturn() == 1)
                                c.card_action(view.getC().getP1(), view.getC().getP2(), view.getC().getJackpot());
                            else
                                c.card_action(view.getC().getP2(), view.getC().getP1(), view.getC().getJackpot());
                        }
                        /*if its move to and the player needs to draw 2 cards set
                         * move_to_mc2=true and draw the next card then cast an instace of move to
                         */
                        case "MoveToDealBuyer" -> {
                            if (view.getC().isMaildraw2()) {
                                c = new Move_to(mailCards[x][2], mailCards[x][3],
                                        Integer.parseInt(mailCards[x][4]));
                                JOptionPane.showMessageDialog(null, "Since it was move_to lets draw the next one first!");
                                move_to_mc2 = true;
                            } else {
                                view.getC().swapturns();
                                move_to = true;
                                c = new Move_to(mailCards[x][2], mailCards[x][3],
                                        Integer.parseInt(mailCards[x][4]));
                                showMailCard(x);
                                if (view.getC().getturn() == 1)
                                    ((Move_to) c).card_action2(view.getC().getP1(), view.getC().getP2(), view.getC().getJackpot(), view.getC());
                                else
                                    ((Move_to) c).card_action2(view.getC().getP2(), view.getC().getP1(), view.getC().getJackpot(), view.getC());
                                view.getC().swapturns();
                            }
                        }
                    }
                    view.getC().getView().change_values_of_money();
                    if (!move_to)
                        showMailCard(x);
                    if (!view.getC().isMaildraw2()) {
                        view.getC().setMaildraw(false);
                        if (!move_to && !view.getC().isMaildraw2_moveto()) {
                            view.getC().getView().setInfo_toDo("-->Roll the dice!");
                            if (view.getC().getCurr_month1() != 0 && view.getC().getCurr_month2() != 0)
                                view.getC().swapturns();
                            view.getC().getView().setInfo_turn("Turn : Player" + view.getC().getturn());
                        }
                    } else {
                        view.getC().setMaildraw2(false);
                        view.getC().getView().setInfo_toDo("-->Draw one more card!");
                    }
                    if (view.getC().isMaildraw2_moveto()) {
                        view.getC().swapturns();
                        view.getC().setMaildraw2_moveto(false);
                        if (view.getC().getturn() == 1) {
                            ((Move_to) view.getC().getMail_Deck().Draw()).card_action2(view.getC().getP1(),
                                    view.getC().getP2(), view.getC().getJackpot(), view.getC());
                            view.getC().getView().setInfo_toDo("-->Draw a deal card!");
                        } else {
                            ((Move_to) view.getC().getMail_Deck().Draw()).card_action2(view.getC().getP2(),
                                    view.getC().getP1(), view.getC().getJackpot(), view.getC());
                            view.getC().getView().setInfo_toDo("-->Draw a deal card!");
                        }
                    }
                    view.getC().getMail_Deck().add_card(c);
                    if (move_to_mc2) {
                        view.getC().setMaildraw2_moveto(true);
                        move_to_mc2 = false;
                    }
                    move_to = false;
                    /*If you draw when its not a draw moment error*/
                } else JOptionPane.showMessageDialog(null, "You cant draw whenever you want!","MailDraw",JOptionPane.ERROR_MESSAGE);
                view.getC().getView().change_values_of_money();
                /*epeidh voleue anti na kanw init to deck kathe fora p pernw tyxaia karta thn vazw sto deck kai ama gemisei apo 48 to 0 nizw*/

                /*-----------------------------FOR DEAL-----------------------------------------------------*/
            } else if (button.getName().equals("Deal")) {
                if (view.getC().isDealdraw()) {
                    /*Ean exw traviksei olh thn trapoula kane pali shuffle*/
                    if(dealCardCount==20){
                        for(int i=0;i<20;i++)
                            doublesDeal[i]=0;
                        view.getC().getDeal_Deck().init_Deal_Deck();
                    }
                    if (dealCardCount == 20) {
                        dealCardCount = 0;
                    }
                    int x = new Random().nextInt(20); //for Random
                    /*Check if you already drew that card*/
                    while (doublesDeal[x] != 0) {
                        x = new Random().nextInt(20);
                    }
                    doublesDeal[x] = x;
                    Card c = new Deal_Card(Integer.parseInt(dealCards[x][3]), Integer.parseInt(dealCards[x][4]), "Deal", dealCards[x][2]
                            , "Buy or ignore");
                    view.getC().getDeal_Deck().add_card(c);
                    showDealCard(x);
                    view.getC().getView().setInfo_toDo("-->Roll the dice!");
                    if (deal_choice == 0) {
                        if (view.getC().getturn() == 1) {
                            new Tile_Deal().tile_action(view.getC().getP1(), view.getC().getP2(), new Deck(), view.getC().getDeal_Deck(),
                                    new Jackpot());
                        } else if (view.getC().getturn() == 2) {
                            new Tile_Deal().tile_action(view.getC().getP2(), view.getC().getP1(), new Deck(), view.getC().getDeal_Deck(),
                                    new Jackpot());
                        }
                    }
                    if (view.getC().getCurr_month1() != 0 && view.getC().getCurr_month2() != 0)
                        view.getC().swapturns();
                    view.getC().getView().setInfo_turn("Turn : Player" + view.getC().getturn());
                    view.getC().getView().change_values_of_money();
                    view.getC().setDealdraw(false);
                } else JOptionPane.showMessageDialog(null, "You cant draw whenever you want!","DealDraw",JOptionPane.ERROR_MESSAGE);
            }
        }
        /*Anti na kanw add sto deck opws to mail exw hdh initialiazed ena deck me deal kai apo kei afairw thn tyxaia p pernw apo dw*/

    }
}
