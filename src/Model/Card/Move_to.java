package Model.Card;

import Model.Board.Dice;
import Model.Board.Tile.Jackpot;
import Model.Deck.Deck;
import Model.Player.player;
import Model.Position.position;
import Controller.controller;
import View.view;

import javax.swing.*;

public class Move_to extends Mail_Card {

    public Move_to(String action, String toDo, int price
    ) {
        super("Move_to", action, toDo, price);
    }

    /**
     * <b>Transformer</b>
     *
     *
     * <b>post : player position =Tile_Mail or Tile_Deal</b>
     *
     * @param player2
     */
    public void card_action2(player p1, player player2, Jackpot j,controller c) {
        boolean flag = true;
        int position = player2.getPositionNumber() + 1;
        while (flag) {
            player2.getCurr_Position().setCurr_tile(position);
            if (player2.getCurr_Position().getCurr_tile().getName().equals("buyer")) {
                player2.getCurr_Position().getCurr_tile().tile_action(player2, p1, new Deck(),
                        new Deck(), new Jackpot());
                flag = false;
                c.swapturns();
            } else if (player2.getCurr_Position().getCurr_tile().getName().equals("deal")) {
                c.setDealdraw(true);
                c.getView().setInfo_toDo("-->Player " + player2.getId() + " draw a deal card!");
                flag = false;
                if(c.isMaildraw2_moveto()){
                    c.swapturns();
                }
            }
            else if (position == 30)
                player2.getCurr_Position().getCurr_tile().tile_action(player2, p1, new Deck(), new Deck(), new Jackpot());
            position++;
        }
        c.getView().PawnsMove(player2.getPositionNumber() + 1, player2.getId());
    }

    @Override
    public void card_action(player p1, player p2, Jackpot j) {

    }
}


